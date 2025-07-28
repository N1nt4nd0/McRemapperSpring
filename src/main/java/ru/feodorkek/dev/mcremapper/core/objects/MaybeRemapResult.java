package ru.feodorkek.dev.mcremapper.core.objects;

import lombok.Value;

import java.util.List;

@Value
public class MaybeRemapResult {

    int remappedEntries;
    List<RemapEntry> entryList;
    String remappedSource;

}
