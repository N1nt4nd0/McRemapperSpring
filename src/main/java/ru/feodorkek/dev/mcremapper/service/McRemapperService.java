package ru.feodorkek.dev.mcremapper.service;

import ru.feodorkek.dev.mcremapper.domain.MaybeRemapResult;
import ru.feodorkek.dev.mcremapper.domain.McRemapperProvider;

import java.util.List;

public interface McRemapperService {

    List<String> getRegisteredProviderNames();

    void setCurrentProvider(String providerName);

    void registerProvider(McRemapperProvider mcRemapperProvider);

    int getMaybeRemapSourceMinLen();

    int getMaybeRemapSourceMaxLen();

    MaybeRemapResult maybeRemap(String mappedSource);

}
