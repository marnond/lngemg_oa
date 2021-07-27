package org.jeecg.modules.fr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FineConfig {
    @Value("${fine.report.token.validTime:#{null}}")
    private Long validTime;
    @Value("${fine.report.token.key:#{null}}")
    private String key;
}
