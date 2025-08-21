package ru.feodorkek.dev.mcremapper.core.objects;

import java.util.List;

public record MaybeRemapResult(int remappedEntries, List<RemapEntry> entryList, String remappedSource) {
}
