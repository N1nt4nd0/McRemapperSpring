package ru.feodorkek.dev.mcremapper.domain;

import java.util.List;

public record MaybeRemapResult(int remappedEntries, List<RemapEntry> entryList, String remappedSource) {
}
