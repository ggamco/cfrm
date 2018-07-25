package com.gmbdesign.cfrm.utils;

import com.gmbdesign.cfrm.dto.UsuarioDTO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author ggamboa
 */
public class AuthTokenGenerator {
    
    private static final Key SECRET_KEY;
    
    static {
        SECRET_KEY = MacProvider.generateKey();
    }
    
    public static String create(UsuarioDTO usuario) {
        
        long nowMillis = System.currentTimeMillis();
        long ttlMillis = 30 * 60 * 1000; //Expira a los 30 minutos
        long expiredMillis = nowMillis + ttlMillis;
        
        Date now = new Date(nowMillis);
        Date expiredAt = new Date(expiredMillis);

        JwtBuilder jwt = Jwts.builder().setSubject("User_Authorized").signWith(SignatureAlgorithm.HS512, SECRET_KEY);
        
        jwt.setIssuer(usuario.getUser());
        jwt.claim("role", usuario.getRole());
        jwt.setIssuedAt(now);
        jwt.setExpiration(expiredAt);
        
        return jwt.compact();
    }
    
    public static boolean checkToken(String token) {
        
        boolean esValido = false;
        
        Date now = new Date(System.currentTimeMillis());
        Date expire = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        
        System.out.println("now: " + now);
        System.out.println("expire: " + expire);
        
        if(now.before(expire)){
            System.out.println("Now es anterior a Expire -> Valido");
            esValido = true;
        } else {
            System.out.println("Now es posterior a Expire -> Caducado");
        }
        
        return esValido;
    }
    
}
