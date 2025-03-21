package ru.feodorkek.dev.mcremapper.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.regex.Pattern;

@Data
@Configuration
@ConfigurationProperties("mc-remapper")
public class McRemapperProperties {

    private Map<String, McRemapperPropertyProvider> providers;
    private int maybeRemapSourceMinLen;
    private int maybeRemapSourceMaxLen;

    @Data
    public static class McRemapperPropertyProvider {

        private int order;
        private String name;
        private Pattern remapPattern;
        private String methodsResourcePath;
        private String fieldsResourcePath;

    }

}