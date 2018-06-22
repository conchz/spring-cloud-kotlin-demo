package com.github.lavenderx.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    /**
     * 使用传入的值获得JWT token,通常情况是登录的时候调用。
     *
     * @param map 例如 传入的map.put("userId",4481973312226302L)
     * @return 获得的token
     */
    public static String produceJWTToken(Map<String, Object> map) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> headerClaims = new HashMap<>();
            headerClaims.put("owner", "changeit");
            JWTCreator.Builder builder = JWT.create().withIssuer("changeit").withHeader(headerClaims);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object val = entry.getValue();
                if (val instanceof Long) {
                    builder.withClaim(entry.getKey(), (Long) val);
                } else if (val instanceof Integer) {
                    builder.withClaim(entry.getKey(), (Integer) val);
                } else if (val instanceof Boolean) {
                    builder.withClaim(entry.getKey(), (Boolean) val);
                } else if (val instanceof Double) {
                    builder.withClaim(entry.getKey(), (Double) val);
                } else if (val instanceof String) {
                    builder.withClaim(entry.getKey(), (String) val);
                } else {
                    builder.withClaim(entry.getKey(), val.toString());
                }
            }
            builder.withExpiresAt(buildExpirationDate());

            return builder.sign(algorithm);

        } catch (Exception ignored) {
        }
        return null;
    }

    private static Date buildExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(7, ChronoUnit.DAYS);
        return Date.from(plus.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据传入的key集合，从token中解析，获取到对应的key,val对，请集中调用，不要多次调用，会没效率。
     *
     * @param coll 例如 传入的coll.add("userId")
     * @return key, value对
     */
    public static Map<String, Object> getJWTValue(Collection<String> coll, String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        try {

            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("changeit")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            //DecodedJWT jwt2 = JWT.decode(token);

            //String algorithmstr = jwt.getAlgorithm();
            //String type = jwt.getValue();
            //Claim owner = jwt.getHeaderClaim("owner");
            String issuer = jwt.getIssuer();
            Date expiresAt = jwt.getExpiresAt();

            Map<String, Object> map = new HashMap<>();
            for (String key : coll) {
                map.put(key, jwt.getClaim(key).as(Object.class));
            }

            return map;
        } catch (Exception ignored) {
        }

        return null;
    }

    /**
     * Get user id
     *
     * @param token JWT token
     * @return
     */
    public static Long getUserId(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("changeit")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userId").asLong();
        } catch (Exception ignored) {
        }

        return null;
    }
}
