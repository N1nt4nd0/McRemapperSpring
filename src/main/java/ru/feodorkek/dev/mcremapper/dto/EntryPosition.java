package ru.feodorkek.dev.mcremapper.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class EntryPosition {
    
    private final int line;
    private final int ch;
    
}