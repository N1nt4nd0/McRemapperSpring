package ru.feodorkek.dev.mcremapper.core.objects;

import lombok.Value;

@Value
public class RemapEntry {

    EntryPosition startPosition;
    EntryPosition endPosition;

}
