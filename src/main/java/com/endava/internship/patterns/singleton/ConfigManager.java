package com.endava.internship.patterns.singleton;

import java.util.Map;

/**
 * Testing-oriented singleton example mentioned by the presentation.
 * It exposes shared configuration through one global instance.
 */
public final class ConfigManager {

    private static ConfigManager instance;

    private final Map<String, String> values = Map.of(
            "baseUrl", "https://test.endava.com",
            "browser", "chrome",
            "timeoutSeconds", "10"
    );

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String get(String key) {
        return values.get(key);
    }
}

