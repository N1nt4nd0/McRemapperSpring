package ru.feodorkek.dev.mcremapper.service;

import ru.feodorkek.dev.mcremapper.core.McRemapperProvider;
import ru.feodorkek.dev.mcremapper.core.objects.MaybeRemapResult;

import java.util.List;

public interface McRemapperService {

    List<String> getRegisteredProviderNames();

    void setCurrentProvider(String providerName);

    void registerProvider(McRemapperProvider mcRemapperProvider);

    int getMaybeRemapSourceMinLen();

    int getMaybeRemapSourceMaxLen();

    void validateMappedSource(String mappedSource);

    MaybeRemapResult maybeRemap(String mappedSource);

}
