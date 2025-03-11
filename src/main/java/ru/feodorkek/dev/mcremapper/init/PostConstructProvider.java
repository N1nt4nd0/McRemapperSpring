package ru.feodorkek.dev.mcremapper.init;

public interface PostConstructProvider {

    String postConstructProviderName();

    void postConstruct();

}
