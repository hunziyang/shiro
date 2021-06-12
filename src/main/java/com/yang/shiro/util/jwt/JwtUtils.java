package com.yang.shiro.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    // 私钥
    private static String SECRET = "SECRET_YANG";

    // 请求头
    public static final String AUTH_HEADER = "X-Authorization-With";

    /**
     * 验证token是否正确
     */
    public static boolean verify(String token, String phone) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("phone", phone).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    /**
     * 获得token中的自定义信息，无需secret解密也能获得
     */
    public static String getClaimFiled(String token, String filed) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(filed).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     */
    public static String sign(String phone) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username，nickname信息
            return JWT.create().withClaim("phone", phone).withExpiresAt(date).sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }
    }

    /**
     * 获取 token的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 验证 token是否过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 刷新 token的过期时间
     */
    public static String refreshTokenExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builer = JWT.create().withExpiresAt(date);
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                builer.withClaim(entry.getKey(), entry.getValue().asString());
            }
            return builer.sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }
    }
}
