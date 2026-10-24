package com.github.henriquebfernandes.heartSteal.config;

import com.github.henriquebfernandes.heartSteal.HeartStealPlugin;

import java.util.Map;

public final class HeartStealConfig {
    private final Map<String, Integer> values;

    public HeartStealConfig(HeartStealPlugin plugin) {
        var config = plugin.getConfig();

        this.values = Map.of(
                "kill_reward", config.getInt("kill_reward", 2),
                "death_punishment", config.getInt("death_punishment", 2),
                "max_health_points", config.getInt("max_health_points", 20),
                "min_health_points", config.getInt("min_health_points", 2),
                "initial_health_points", config.getInt("initial_health_points", 20)
        );

        validateConfig();
    }

    public int get(String key) {
        Integer value = values.get(key);

        if (value == null) {
            throw new IllegalArgumentException(
                    "Unknown configuration key: " + key
            );
        }

        return value;
    }

    private void validateConfig() {
        int min = get("min_health_points");
        int max = get("max_health_points");
        int initial = get("initial_health_points");

        if (min <= 0) {
            throw new IllegalArgumentException(
                    "'min_health_points' must be greater than 0."
            );
        }

        if (max <= min) {
            throw new IllegalArgumentException(
                    "'max_health_points' must be greater than 'min_health_points'."
            );
        }

        if (initial < min || initial > max) {
            throw new IllegalArgumentException(
                    "'initial_health_points' must be between " + min + " and " + max + "."
            );
        }
    }
}