package com.wht.zuul.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wht.zuul.bean.User;
import com.wht.zuul.config.Constant;

public class JwtUtil {

	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	/**
	 * <p>
	 * Description: TODO(由字符串生成加密key)</br>
	 * 
	 * @author liyd
	 * @return
	 */
	public static SecretKey generalKey() {
		String stringKey = getSalt() + Constant.JWT_SECRET;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length,
				"AES");
		return key;
	}

	/**
	 * <p>
	 * Description: TODO(创建jwt)</br>
	 * 
	 * @author liyd
	 * @param id
	 * @param subject
	 * @param ttlMillis
	 * @return
	 * @throws Exception
	 */
	public static String createJWT(String id, String subject, long ttlMillis)
			throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now)
				.setSubject(subject).signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	/**
	 * <p>
	 * Description: TODO(解密jwt)</br>
	 * 
	 * @author liyd
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt) throws Exception {
		SecretKey key = generalKey();
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt)
				.getBody();
		logger.info(">>>>>ID: " + claims.getId());
		logger.info(">>>>>Subject: " + claims.getSubject());
		logger.info(">>>>>Issuer: " + claims.getIssuer());
		logger.info(">>>>>Expiration: " + claims.getExpiration());
		return claims;
	}

	/**
	 * <p>
	 * Description: TODO(生成subject信息)</br>
	 * 
	 * @author liyd
	 * @param user
	 * @return
	 * @throws JSONException
	 */
	public static String generalSubject(User user) throws JSONException {
		JSONObject jo = new JSONObject();
		jo.put("mobile", user.getMobile());
		jo.put("role", user.getRole());
		return jo.toString();
	}

	private static String getSalt() {
		char[] chars = "0123456789abcdefghijklmnopqrwtuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] saltchars = new char[16];
		Random RANDOM = new SecureRandom();
		for (int i = 0; i < 16; i++) {
			int n = RANDOM.nextInt(62);
			saltchars[i] = chars[n];
		}
		String salt = new String(saltchars);
		return salt;
	}

}
