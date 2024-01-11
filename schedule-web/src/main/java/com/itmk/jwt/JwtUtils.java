package com.itmk.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {
    //颁发者
    private String issuer;
    //秘钥
    private String secret;
    //过期时间
    private int expiration;

    /**
     * 生成token
     *
     * @param map 自定义参数
     * @return
     */
    public String generateToken(Map<String, String> map) {
        //设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, expiration);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //设置自定义参数
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        //生成token
        String token = builder.withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    /**
     * 验证otken
     *
     * @param token
     * @return
     */
    public boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

     /**
     * 解析token
     *
     * @param token
     * @return
     */
    public DecodedJWT jwtDecode(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("token签名错误!");
        } catch (AlgorithmMismatchException e) {
            throw new RuntimeException("token算法不匹配!");
        } catch (TokenExpiredException e) {
            throw new RuntimeException("token过期!");
        } catch (Exception e) {
            throw new RuntimeException("token解析失败!");
        }
    }
}
