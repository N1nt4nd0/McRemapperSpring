package ru.feodorkek.dev.mcremapper.core;

import lombok.Data;

import java.util.List;

@Data
public class MaybeRemapResult {

    private final int remappedEntries;
    private final List<RemapEntry> entryList;
    private final String remappedSource;

}
