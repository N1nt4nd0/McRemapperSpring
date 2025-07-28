package ru.feodorkek.dev.mcremapper.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.feodorkek.dev.mcremapper.core.McRemapperProvider;
import ru.feodorkek.dev.mcremapper.core.objects.EntryPosition;
import ru.feodorkek.dev.mcremapper.core.objects.MaybeRemapResult;
import ru.feodorkek.dev.mcremapper.core.objects.RemapEntry;
import ru.feodorkek.dev.mcremapper.exception.McRemapperException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Getter
@RequiredArgsConstructor
public class McRemapperServiceImpl implements McRemapperService {

    private final Map<String, McRemapperProvider> providers = new HashMap<>();
    private McRemapperProvider currentProvider;
    private final int maybeRemapSourceMinLen;
    private final int maybeRemapSourceMaxLen;

    @Override
    public List<String> getRegisteredProviderNames() {
        return providers.values().stream()
                .sorted()
                .map(McRemapperProvider::getName)
                .toList();
    }

    @Override
    public void setCurrentProvider(final String providerName) {
        if (providerName == null || providerName.isBlank()) {
            throw new McRemapperException("Minecraft version name cannot be blank");
        }
        if (!providers.containsKey(providerName)) {
            throw new McRemapperException(String.format("Minecraft remapper provider '%s' not registered",
                    providerName));
        }
        currentProvider = providers.get(providerName);
    }

    @Override
    public void registerProvider(final McRemapperProvider mcRemapperProvider) {
        providers.put(mcRemapperProvider.getName(), mcRemapperProvider);
    }

    @Override
    public MaybeRemapResult maybeRemap(final String mappedSource) {
        if (currentProvider == null) {
            throw new McRemapperException("Current provider not set");
        }
        if (mappedSource == null || mappedSource.isBlank()) {
            throw new McRemapperException("Mapped source cannot be blank");
        }
        if (mappedSource.length() <= maybeRemapSourceMinLen) {
            throw new McRemapperException(String.format("Mapped source length should be greater than %s chars",
                    maybeRemapSourceMinLen));
        } else if (mappedSource.length() >= maybeRemapSourceMaxLen) {
            throw new McRemapperException(String.format("Mapped source length should be less than %s chars",
                    maybeRemapSourceMaxLen));
        }

        final var remapEntries = new ArrayList<RemapEntry>();
        final var result = new StringBuilder();
        final var lines = mappedSource.split("\n");

        try {
            for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
                final var line = lineNumber < lines.length - 1 ? lines[lineNumber] + "\n" : lines[lineNumber];
                final var matcher = currentProvider.getRemapPattern().matcher(line);
                int offset = 0;

                while (matcher.find()) {
                    var mappedValue = matcher.group();
                    var remappedValue = currentProvider.getMappings().getOrDefault(mappedValue, mappedValue);
                    matcher.appendReplacement(result, Matcher.quoteReplacement(remappedValue));
                    if (!mappedValue.equals(remappedValue)) {
                        int newStart = matcher.start() + offset;
                        int newEnd = newStart + remappedValue.length();
                        offset += remappedValue.length() - mappedValue.length();
                        final var startPosition = new EntryPosition(lineNumber, newStart);
                        final var endPosition = new EntryPosition(lineNumber, newEnd);
                        remapEntries.add(new RemapEntry(startPosition, endPosition));
                    }
                }
                matcher.appendTail(result);
            }
            return new MaybeRemapResult(remapEntries.size(), remapEntries, result.toString());
        } catch (final Exception exception) {
            throw new McRemapperException("Exception on remapping source", exception);
        }
    }

}
