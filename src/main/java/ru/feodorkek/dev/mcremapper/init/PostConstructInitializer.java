package ru.feodorkek.dev.mcremapper.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostConstructInitializer {

    private final List<PostConstructProvider> postConstructProviders;

    @PostConstruct
    protected void postConstructInitialization() {
        for (final var provider : postConstructProviders) {
            provider.postConstruct();
            log.info("{} successfully initialized", provider.postConstructProviderName());
        }
    }

}
