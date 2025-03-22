package ru.feodorkek.dev.mcremapper.config.properties.converters;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PatternConverter implements Converter<String, Pattern> {

    @Override
    public Pattern convert(@NonNull final String regexp) {
        return Pattern.compile(regexp);
    }

}
