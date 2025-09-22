package ru.feodorkek.dev.mcremapper.service;

import java.util.Map;
import java.util.stream.Stream;

public interface MappingsLoaderService {

    Map<String, String> loadMappingsFromResourcePath(String resourcePath);

    Map<String, String> loadMappingsFromLines(Stream<String> lines);

}
