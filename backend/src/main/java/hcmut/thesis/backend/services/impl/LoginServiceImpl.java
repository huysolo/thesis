/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.services.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

/**
 *
 * @author MinBui
 */
@Component
public class LoginServiceImpl implements LoginService  {
    static final Key key = MacProvider.generateKey();
    public String createJWT() {       
        String s = Jwts.builder()
                .setSubject("MinBui")
                .signWith(SignatureAlgorithm.HS256, key).compact();
        System.out.println(s);
        String claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJwt(s)
                .getBody().getSubject();
       // System.out.println(claims);
        return s;
    }

    public void parseJWT(String jwt){
        try{
        String claims = Jwts.parser().setSigningKey(key).parseClaimsJwt(jwt).getBody().getSubject();
        System.out.println(claims);
        }
        catch (SignatureException e){
            System.out.println("false rồi");
        }
    }         
}
