package ru.feodorkek.dev.mcremapper.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RemapEntry {
    
    private final EntryPosition startPosition;
    private final EntryPosition endPosition;
    
}