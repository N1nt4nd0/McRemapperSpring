package ru.feodorkek.dev.mcremapper.core;

import lombok.Data;

@Data
public class RemapEntry {

    private final String value;
    private final EntryPosition startPosition;
    private final EntryPosition endPosition;

}
