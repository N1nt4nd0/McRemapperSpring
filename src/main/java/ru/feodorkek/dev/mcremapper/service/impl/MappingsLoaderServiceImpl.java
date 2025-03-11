package ru.feodorkek.dev.mcremapper.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.exception.McRemapperException;
import ru.feodorkek.dev.mcremapper.service.MappingsLoaderService;
import ru.feodorkek.dev.mcremapper.util.StringUnit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MappingsLoaderServiceImpl implements MappingsLoaderService {

    private final ResourceLoader resourceLoader;

    @Override
    public Map<String, String> loadMappingsFromResourcePath(final String resourcePath) {
        if (StringUnit.isBlank(resourcePath)) {
            throw new McRemapperException("Mapping resource path cannot be blank");
        }
        try {
            final var resource = resourceLoader.getResource("classpath:" + resourcePath);
            try (final var reader = new BufferedReader(new InputStreamReader(resource.getInputStream(),
                    StandardCharsets.UTF_8))) {
                return reader.lines()
                        .map(line -> line.split(","))
                        .filter(split -> split.length >= 2)
                        .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1],
                                (existing, duplicate) -> existing));
            }
        } catch (final Exception exception) {
            throw new McRemapperException("Exception on loading mappings from resource path: " + resourcePath);
        }
    }

}
