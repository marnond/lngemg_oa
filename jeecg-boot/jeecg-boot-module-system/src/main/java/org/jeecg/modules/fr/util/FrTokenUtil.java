package org.jeecg.modules.fr.util;

import com.fr.cert.token.JwtBuilder;
import com.fr.cert.token.Jwts;
import com.fr.cert.token.SignatureAlgorithm;
import org.jeecg.modules.fr.config.FineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FrTokenUtil {
    @Autowired
    private FineConfig fineConfig;


    private static String createJwt(String issuer, String id, String subject, long validTime, String key) {
        //用于生成数字签名，即参数fine_digital_signature的值
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + validTime);
        JwtBuilder builder = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .setId(id)
                .signWith(signatureAlgorithm, key);
        return builder.compact();
    }

    /**
     * 获取模板token
     *
     * @param subjectPath 模板路径path，如：GettingStarted.cpt
     * @return
     */
    public String getToken(String subjectPath) {
        return createJwt("", "", subjectPath, fineConfig.getValidTime(), fineConfig.getKey());
    }
}
