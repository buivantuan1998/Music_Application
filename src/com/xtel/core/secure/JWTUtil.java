package com.xtel.core.secure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class JWTUtil {
    static final String SECRET = "d24fe5af070ee79754bd17bdac84ca8e";
    static final String WEBSITE = "";
    static final String COMPANY_NAME = "XTEL.,JSC";

    static final String SECRET_FOR_NCC = "X DEVELOPER";
    static final String WEBSITE_FOR_NCC = "sdlncc.xtel.vn";
    static final String COMPANY_NAME_FOR_NCC = "X CORPORATION";

    private static Algorithm algorithm = null;
    private static Algorithm algorithm_for_ncc = null;

    private static JWTVerifier verifier = null;
    private static JWTVerifier verifier_for_ncc = null;


    static {
        try {
            algorithm = Algorithm.HMAC512(SECRET);
            algorithm_for_ncc = Algorithm.HMAC512(SECRET_FOR_NCC);

            verifier = JWT.require(algorithm).withIssuer(COMPANY_NAME).build();
            verifier_for_ncc = JWT.require(algorithm_for_ncc).withIssuer(COMPANY_NAME_FOR_NCC).build();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static Date getExpired(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, time);
        return calendar.getTime();
    }

    public static String encode(String subject, String userData, int time) {
        return JWT.create().withExpiresAt(getExpired(time))
                .withIssuer(COMPANY_NAME)
                .withAudience(WEBSITE)
                .withSubject(subject)
                .withClaim("user_data", userData)
                .sign(algorithm);
    }

    public static String encodeForNcc(String subject, String userData, int time) {
        return JWT.create().withExpiresAt(getExpired(time))
                .withIssuer(COMPANY_NAME_FOR_NCC)
                .withAudience(WEBSITE_FOR_NCC)
                .withSubject(subject)
                .withClaim("user_data", userData)
                .sign(algorithm_for_ncc);
    }

    public static String decodeJWT2(String token) throws Exception{
        DecodedJWT decodedJWT = decodeJWT(token);
        if(decodedJWT == null){
            return null;
        }
        return decodedJWT.getClaim("user_data").asString();
    }

    public static String decodeJWT2ForNcc(String token) throws Exception{
        DecodedJWT decodedJWT = decodeJWTForNcc(token);
        if(decodedJWT == null){
            return null;
        }
        return decodedJWT.getClaim("user_data").asString();
    }

    public static DecodedJWT decodeJWTForNcc(String token) throws Exception{
        if (token == null || token.trim().length() == 0) {
            return null;
        }
        return verifier_for_ncc.verify(token.trim());
    }

    public static DecodedJWT decodeJWT(String token) throws Exception{
        if (token == null || token.trim().length() == 0) {
            return null;
        }
        return verifier.verify(token.trim());
    }


    public static void main(String[] args) throws Exception {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiIiLCJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpc3MiOiJYVEVMLixKU0MiLCJ1c2VyX2RhdGEiOiJ7XCJ1c2VySW5mb1wiOntcImlkXCI6MSxcIm5hbWVcIjpcIkFkbWluaXN0cmF0b3JcIixcImVtYWlsXCI6XCJhZG1pbkBnbWFpbC5jb21cIixcInBob25lXCI6XCIwOTA5MDQ5MDc4XCIsXCJwb3NpdGlvblwiOlwiQWRtaW5cIixcInN0YXR1c1wiOjEsXCJhY2NvdW50X3R5cGVcIjoxfSxcInBhZ2VJbmZvXCI6W3tcImlkXCI6MTEsXCJwYWdlX25hbWVcIjpcIlPDoG4gZHUgbOG7i2NoXCIsXCJwYWdlX3VybFwiOlwiL3Nhbi1kdS1saWNoXCIsXCJwYWdlX2ljb25cIjpcInNhbi1kdS1saWNoXCIsXCJkZXNjcmlwdGlvblwiOlwiXCIsXCJwYXJlbnRfaWRcIjowLFwiZGVmaW5lX2tleVwiOlwiXCIsXCJtZW51X2luZGV4XCI6M30se1wiaWRcIjoyMyxcInBhZ2VfbmFtZVwiOlwiS2lvc2hcIixcInBhZ2VfdXJsXCI6XCIva2lvc2hcIixcInBhZ2VfaWNvblwiOlwia2lvc2hcIixcInBhcmVudF9pZFwiOjAsXCJtZW51X2luZGV4XCI6NH0se1wiaWRcIjoyNCxcInBhZ2VfbmFtZVwiOlwiQ-G6pXUgaMOsbmggY2h1bmdcIixcInBhZ2VfdXJsXCI6XCJcIixcInBhZ2VfaWNvblwiOlwiY2F1LWhpbmgtY2h1bmdcIixcInBhcmVudF9pZFwiOjAsXCJtZW51X2luZGV4XCI6NX0se1wiaWRcIjoyLFwicGFnZV9uYW1lXCI6XCJTaXRlXCIsXCJwYWdlX3VybFwiOlwiXCIsXCJwYWdlX2ljb25cIjpcIlwiLFwiZGVzY3JpcHRpb25cIjpcIlwiLFwicGFyZW50X2lkXCI6MSxcImRlZmluZV9rZXlcIjpcIlwiLFwibWVudV9pbmRleFwiOjJ9LHtcImlkXCI6MyxcInBhZ2VfbmFtZVwiOlwiTmfDtG4gbmfhu69cIixcInBhZ2VfdXJsXCI6XCIvbmdvbi1uZ3VcIixcInBhZ2VfaWNvblwiOlwiXCIsXCJkZXNjcmlwdGlvblwiOlwiXCIsXCJwYXJlbnRfaWRcIjoyLFwiZGVmaW5lX2tleVwiOlwiXCIsXCJtZW51X2luZGV4XCI6MX0se1wiaWRcIjo3LFwicGFnZV9uYW1lXCI6XCJO4buZaSBkdW5nXCIsXCJwYWdlX3VybFwiOlwiXCIsXCJwYWdlX2ljb25cIjpcIlwiLFwiZGVzY3JpcHRpb25cIjpcIlwiLFwicGFyZW50X2lkXCI6NSxcImRlZmluZV9rZXlcIjpcIlwiLFwibWVudV9pbmRleFwiOjJ9LHtcImlkXCI6OCxcInBhZ2VfbmFtZVwiOlwiVHJhbmdcIixcInBhZ2VfdXJsXCI6XCIvdHJhbmdcIixcInBhZ2VfaWNvblwiOlwiXCIsXCJkZXNjcmlwdGlvblwiOlwiXCIsXCJwYXJlbnRfaWRcIjo3LFwiZGVmaW5lX2tleVwiOlwiXCIsXCJtZW51X2luZGV4XCI6MX0se1wiaWRcIjo5LFwicGFnZV9uYW1lXCI6XCJEYW5oIG3hu6VjIGLDoGkgdmnhur90XCIsXCJwYWdlX3VybFwiOlwiL2RhbmgtbXVjLWJhaS12aWV0XCIsXCJwYWdlX2ljb25cIjpcIlwiLFwiZGVzY3JpcHRpb25cIjpcIlwiLFwicGFyZW50X2lkXCI6NyxcImRlZmluZV9rZXlcIjpcIlwiLFwibWVudV9pbmRleFwiOjJ9LHtcImlkXCI6MTMsXCJwYWdlX25hbWVcIjpcIlPhuqNuIHBo4bqpbVwiLFwicGFnZV91cmxcIjpcIlwiLFwicGFnZV9pY29uXCI6XCJcIixcImRlc2NyaXB0aW9uXCI6XCJcIixcInBhcmVudF9pZFwiOjExLFwiZGVmaW5lX2tleVwiOlwiXCIsXCJtZW51X2luZGV4XCI6Mn0se1wiaWRcIjoxOCxcInBhZ2VfbmFtZVwiOlwiTmjDoCBjdW5nIGPhuqVwXCIsXCJwYWdlX3VybFwiOlwiL25oYS1jdW5nLWNhcFwiLFwicGFyZW50X2lkXCI6MTEsXCJtZW51X2luZGV4XCI6M30se1wiaWRcIjoxOSxcInBhZ2VfbmFtZVwiOlwiS2jDoWNoIGjDoG5nXCIsXCJwYWdlX3VybFwiOlwiL2toYWNoLWhhbmdcIixcInBhcmVudF9pZFwiOjExLFwibWVudV9pbmRleFwiOjR9LHtcImlkXCI6MjAsXCJwYWdlX25hbWVcIjpcIkdpYW8gZOG7i2NoXCIsXCJwYWdlX3VybFwiOlwiXCIsXCJwYXJlbnRfaWRcIjoxMSxcIm1lbnVfaW5kZXhcIjo1fSx7XCJpZFwiOjIxLFwicGFnZV9uYW1lXCI6XCJQaMawxqFuZyB0aOG7qWMgdGhhbmggdG_DoW5cIixcInBhZ2VfdXJsXCI6XCIvcGh1b25nLXRodWMtdGhhbmgtdG9hblwiLFwicGFyZW50X2lkXCI6MTEsXCJtZW51X2luZGV4XCI6Nn0se1wiaWRcIjoyMixcInBhZ2VfbmFtZVwiOlwiS2h1eeG6v24gbeG6oWlcIixcInBhZ2VfdXJsXCI6XCIva2h1eWVuLW1haVwiLFwicGFyZW50X2lkXCI6MTEsXCJtZW51X2luZGV4XCI6N30se1wiaWRcIjoxNCxcInBhZ2VfbmFtZVwiOlwiRGFuaCBt4bulYyBz4bqjbiBwaOG6qW1cIixcInBhZ2VfdXJsXCI6XCIvZGFuaC1tdWMtc2FuLXBoYW1cIixcInBhZ2VfaWNvblwiOlwiXCIsXCJkZXNjcmlwdGlvblwiOlwiXCIsXCJwYXJlbnRfaWRcIjoxMyxcImRlZmluZV9rZXlcIjpcIlwiLFwibWVudV9pbmRleFwiOjF9LHtcImlkXCI6MTUsXCJwYWdlX25hbWVcIjpcIlPhuqNuIHBo4bqpbVwiLFwicGFnZV91cmxcIjpcIi9zYW4tcGhhbVwiLFwicGFnZV9pY29uXCI6XCJcIixcImRlc2NyaXB0aW9uXCI6XCJcIixcInBhcmVudF9pZFwiOjEzLFwiZGVmaW5lX2tleVwiOlwiXCIsXCJtZW51X2luZGV4XCI6Mn0se1wiaWRcIjoxNixcInBhZ2VfbmFtZVwiOlwiQuG7mSB0aHXhu5ljIHTDrW5oIHPhuqNuIHBo4bqpbVwiLFwicGFnZV91cmxcIjpcIi9iby10aHVvYy10aW5oLXNhbi1waGFtXCIsXCJwYXJlbnRfaWRcIjoxMyxcIm1lbnVfaW5kZXhcIjozfSx7XCJpZFwiOjE3LFwicGFnZV9uYW1lXCI6XCJUaHXhu5ljIHTDrW5oIHPhuqNuIHBo4bqpbVwiLFwicGFnZV91cmxcIjpcIi90aHVvYy10aW5oLXNhbi1waGFtXCIsXCJwYXJlbnRfaWRcIjoxMyxcIm1lbnVfaW5kZXhcIjo0fSx7XCJpZFwiOjMxLFwicGFnZV9uYW1lXCI6XCJZw6p1IGPhuqd1IG11YSBz4buJXCIsXCJwYWdlX3VybFwiOlwiL3lldS1jYXUtbXVhLXNpXCIsXCJwYXJlbnRfaWRcIjoyMCxcIm1lbnVfaW5kZXhcIjoyfSx7XCJpZFwiOjMwLFwicGFnZV9uYW1lXCI6XCJEYW5oIHPDoWNoIGdpYW8gZOG7i2NoXFxyXFxuXCIsXCJwYWdlX3VybFwiOlwiL2Rhbmgtc2FjaC1naWFvLWRpY2hcIixcInBhcmVudF9pZFwiOjIwLFwibWVudV9pbmRleFwiOjR9LHtcImlkXCI6MjUsXCJwYWdlX25hbWVcIjpcIsSQw6FuaCBnacOhXCIsXCJwYWdlX3VybFwiOlwiL2RhbmgtZ2lhXCIsXCJwYXJlbnRfaWRcIjoyNCxcIm1lbnVfaW5kZXhcIjoxfSx7XCJpZFwiOjI2LFwicGFnZV9uYW1lXCI6XCJUaMO0bmcgYsOhbyBraMOhY2ggaMOgbmdcIixcInBhZ2VfdXJsXCI6XCJcIixcInBhcmVudF9pZFwiOjI0LFwibWVudV9pbmRleFwiOjJ9LHtcImlkXCI6MjcsXCJwYWdlX25hbWVcIjpcIkNoYXRcIixcInBhZ2VfdXJsXCI6XCJcIixcInBhcmVudF9pZFwiOjI0LFwiZGVmaW5lX2tleVwiOlwiXCIsXCJtZW51X2luZGV4XCI6M30se1wiaWRcIjoyOCxcInBhZ2VfbmFtZVwiOlwiUGjDom4gdMOtY2ggd2Vic2l0ZVwiLFwicGFnZV91cmxcIjpcIi9waGFuLXRpY2gtd2VidGlzZVwiLFwicGFyZW50X2lkXCI6MjQsXCJtZW51X2luZGV4XCI6NH0se1wiaWRcIjoyOSxcInBhZ2VfbmFtZVwiOlwiUGjDom4gcXV54buBbiBuaMOgIGN1bmcgY-G6pXBcIixcInBhZ2VfdXJsXCI6XCIvcGhhbi1xdXllbi1uaGEtY3VuZy1jYXBcIixcInBhcmVudF9pZFwiOjI0LFwibWVudV9pbmRleFwiOjV9LHtcImlkXCI6MzMsXCJwYWdlX25hbWVcIjpcIlRow7RuZyBiw6FvIHThu7EgxJHhu5luZ1wiLFwicGFnZV91cmxcIjpcIi90aG9uZy1iYW8tdHUtZG9uZ1wiLFwicGFyZW50X2lkXCI6MjYsXCJtZW51X2luZGV4XCI6MX0se1wiaWRcIjozNCxcInBhZ2VfbmFtZVwiOlwiVGjDtG5nIGLDoW8gdGjhu6cgY8O0bmdcIixcInBhZ2VfdXJsXCI6XCIvdGhvbmctYmFvLXRodS1jb25nXCIsXCJwYXJlbnRfaWRcIjoyNixcIm1lbnVfaW5kZXhcIjoyfSx7XCJpZFwiOjM2LFwicGFnZV9uYW1lXCI6XCJD4bqldSBow6xuaCBjaHVuZ1wiLFwicGFnZV91cmxcIjpcIi9jYXUtaGluaC1jaHVuZ1wiLFwicGFyZW50X2lkXCI6MjcsXCJtZW51X2luZGV4XCI6MX0se1wiaWRcIjozNyxcInBhZ2VfbmFtZVwiOlwiVMOtY2ggaOG7o3AgT21uaSBjaGFubmVsXCIsXCJwYWdlX3VybFwiOlwiL3RpY2gtaG9wLW9tbmktY2hhbm5lbFwiLFwicGFyZW50X2lkXCI6MjcsXCJtZW51X2luZGV4XCI6Mn0se1wiaWRcIjozOCxcInBhZ2VfbmFtZVwiOlwiQ2hhdCBib3RcIixcInBhZ2VfdXJsXCI6XCIvY2hhdC1ib3RcIixcInBhcmVudF9pZFwiOjI3LFwibWVudV9pbmRleFwiOjN9LHtcImlkXCI6MzUsXCJwYWdlX25hbWVcIjpcIkzhu4tjaCBz4butIGNoYXRcIixcInBhZ2VfdXJsXCI6XCIvbGljaC1zdS1jaGF0XCIsXCJwYXJlbnRfaWRcIjoyNyxcIm1lbnVfaW5kZXhcIjo0fV19IiwiZXhwIjoxNjA2ODgyNjQzfQ.lgvdlLOL7nYPzY3fcgA33fHGTb80AMDHN0XRFCLFl1jH3Hm7-l9V8RI5mZIhnaL8UH2GW_mYmZKiABHeuSsXFA";
        System.out.println(decodeJWT2(token));
    }


}