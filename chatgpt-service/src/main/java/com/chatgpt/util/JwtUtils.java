package com.chatgpt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatgpt.constant.Constant;
import com.chatgpt.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * jwt工具类
 */
public class JwtUtils {
    private final static Logger log = LoggerFactory.getLogger(JwtUtils.class);
    private final static String SECRET = "secret";

    /**
     * 校验token是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 从token中获取用户名
     *
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            log.error("jwt获取用户名失败：{}", e);
            throw new ServiceException("jwt获取用户失败～", e);
        }
    }

    /**
     * 生成 token
     *
     * @param username
     * @return token
     */
    public static String sign(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + Constant.SESSION_EXPIRATION * 1000))
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("jwt生成用户token失败", e);
            throw new ServiceException("jwt获取用户名失败～", e);
        }
    }

}
