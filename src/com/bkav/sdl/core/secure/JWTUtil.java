package com.bkav.sdl.core.secure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class JWTUtil {
    static final int EXPIRATION_TIME = 30;
    static final String SECRET = "d24fe5af070ee79754bd17bdac84ca8e";
    static final String WEBSITE = "";
    static final String EMAIL = "xdev@xtel.vn";
    static final String COMPANY_NAME = "XTEL.,JSC";

    private static Algorithm algorithm = null;
    private static JWTVerifier verifier = null;


    static {
        try {
            algorithm = Algorithm.HMAC512(SECRET);
            verifier = JWT.require(algorithm).withIssuer(COMPANY_NAME).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public String getSampleToken(String userData) {
        return JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer(COMPANY_NAME)
                .withAudience(WEBSITE)
                .withSubject(EMAIL)
                .withClaim("user_data", userData)
                .sign(algorithm);
    }

    private Date getExpired() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, EXPIRATION_TIME);
        return calendar.getTime();
    }



    public String encode(String channel, String userData) {
        return JWT.create().withExpiresAt(getExpired())
                .withIssuer(COMPANY_NAME)
                .withAudience(WEBSITE)
                .withSubject(channel)
                .withClaim("user_data", userData)
                .sign(algorithm);
    }

    public String decodeJWT2(String token) throws Exception{
    	DecodedJWT decodedJWT = decodeJWT(token);
    	if(decodedJWT == null){
    		return null;
    	}
    	String userData = decodedJWT.getClaim("user_data").asString();
    	return userData;
    }
    
    public String decodeJWT2S(String token) throws Exception{
        DecodedJWT decodedJWT = decodeJWT(token);
        if (decodedJWT == null) {
            return null;
        }
        Date expireDate = decodedJWT.getExpiresAt();
        if (new Date().after(expireDate)) {
            return null;
        }
        return decodedJWT.getClaim("user_data").asString();
    }

    public DecodedJWT decodeJWT(String token) throws Exception{
        if (token == null || token.trim().length() == 0) {
            return null;
        }
        return verifier.verify(token.trim());
    }

    public boolean validToken(DecodedJWT jwt) {
        if (jwt == null)
            return false;
        Date date = jwt.getExpiresAt();
        return new Date().before(date);
    }

    public boolean validToken(String token) throws Exception{
        return validToken(decodeJWT(token));
    }
}