package ru.feodorkek.dev.mcremapper.core.objects;

import lombok.Data;

@Data
public class RemapEntry {

    private final EntryPosition startPosition;
    private final EntryPosition endPosition;

}
