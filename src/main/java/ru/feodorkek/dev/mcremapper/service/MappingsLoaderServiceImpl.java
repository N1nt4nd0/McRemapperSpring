package ru.feodorkek.dev.mcremapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.exception.McRemapperException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MappingsLoaderServiceImpl implements MappingsLoaderService {

    private final ResourceLoader resourceLoader;

    @Override
    public Map<String, String> loadMappingsFromResourcePath(final String resourcePath) {
        if (resourcePath == null || resourcePath.isBlank()) {
            throw new McRemapperException("Mapping resource path cannot be blank");
        }
        try {
            final var resource = resourceLoader.getResource("classpath:" + resourcePath);
            try (final var reader = new BufferedReader(new InputStreamReader(resource.getInputStream(),
                    StandardCharsets.UTF_8))) {
                return loadMappingsFromLines(reader.lines());

            }
        } catch (final Exception exception) {
            throw new McRemapperException("Exception on loading mappings from resource path: " + resourcePath,
                    exception);
        }
    }

    @Override
    public Map<String, String> loadMappingsFromLines(final Stream<String> lines) {
        return lines.map(line -> line.split(","))
                .filter(split -> split.length >= 2)
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1],
                        (existing, duplicate) -> existing));
    }

}
