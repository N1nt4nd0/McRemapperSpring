package ru.feodorkek.dev.mcremapper.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RemapResult {
    
    private final int remappedEntries;
    private final List<RemapEntry> entryList;
    private final String remappedSource;
    
}