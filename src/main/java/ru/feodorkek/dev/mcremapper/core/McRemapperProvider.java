package ru.feodorkek.dev.mcremapper.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.feodorkek.dev.mcremapper.exception.McRemapperException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor
public class McRemapperProvider {

    private final Map<String, String> mappings = new HashMap<>();
    private final String name;
    private final Pattern remapPattern;

    public Map<String, String> getMappings() {
        return Collections.unmodifiableMap(mappings);
    }

    public void loadMappings(final Map<String, String> mappings) {
        if (mappings == null || mappings.isEmpty()) {
            throw new McRemapperException("No input mappings found");
        }
        this.mappings.putAll(mappings);
    }

}
