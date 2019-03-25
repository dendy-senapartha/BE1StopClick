package com.Be1StopClick.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

/**
 * Created by dendy-prtha on 20/03/19.
 * This class creates token for your application and also have a method to validate the token coming from the client
 * We are using JWT to create the token for our application
 */

@Component
public class AppTokenProvider {

    private static final long EXPIRATION_TIME_SECONDS = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsASecretOf1StopClick";
    private static final String TOKEN_PREFIX = "1StopClick";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) {
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + "-" + getToken(username));
        String headerString = res.getHeader(HEADER_STRING);
        System.out.println("Header String "+ headerString);
    }

    public static String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.nanoTime() + EXPIRATION_TIME_SECONDS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Optional<String> getUserFromToken(HttpServletRequest request) {
        final String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            try {
                Claims body = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                final Instant expiration = body.getExpiration().toInstant();

                if (expiration.isBefore(Instant.now())) {
                    return Optional.empty();
                }
                return Optional.of(body.getSubject());

            } catch (SignatureException e) {
                // invalid signature
            }
        }
        return Optional.empty();
    }
}
