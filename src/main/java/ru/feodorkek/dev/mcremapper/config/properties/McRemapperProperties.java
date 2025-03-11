package ru.feodorkek.dev.mcremapper.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Data
@Configuration
@ConfigurationProperties("mc-remapper")
public class McRemapperProperties {

    private ProvidersProperties providers;
    private int maybeRemapSourceMinLen;
    private int maybeRemapSourceMaxLen;

    @Data
    public static class ProvidersProperties {

        private Mc1710Properties mc1710;
        private Mc1201Properties mc1201;

        @Data
        public static class Mc1710Properties {

            private String name;
            private Pattern remapPattern;
            private String methodsResourcePath;
            private String fieldsResourcePath;

        }

        @Data
        public static class Mc1201Properties {

            private String name;
            private Pattern remapPattern;
            private String methodsResourcePath;
            private String fieldsResourcePath;

        }

    }

}
