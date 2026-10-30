package com.github.henriquebfernandes.heartSteal.config;

import com.github.henriquebfernandes.heartSteal.HeartStealPlugin;

public final class HeartStealConfig {
    public final int reward;
    public final int punishment;
    public final int maxHP;
    public final int minHP;
    public final int initialHP;


    public HeartStealConfig(HeartStealPlugin plugin) {
        var config = plugin.getConfig();

        reward = config.getInt("reward", 2);
        punishment = config.getInt("punishment", 2);
        maxHP = config.getInt("max_hp", 20);
        minHP = config.getInt("min_hp", 2);
        initialHP = config.getInt("initial_hp", 10);

        validateConfig();
    }

    private void validateConfig() {
        if (reward <= 0) {
            throw new IllegalArgumentException("'reward' must be greater than 0.");
        }

        if (punishment <= 0) {
            throw new IllegalArgumentException("'punishment' must be greater than 0.");
        }

        if (minHP <= 0) {
            throw new IllegalArgumentException("'min_hp' must be greater than 0.");
        }

        if (maxHP <= 0) {
            throw new IllegalArgumentException("'max_hp' must be greater than 0.");
        }

        if (maxHP < minHP) {
            throw new IllegalArgumentException("'max_hp' must be greater than or equal to 'min_hp'.");
        }

        if (initialHP < minHP || initialHP > maxHP) {
            throw new IllegalArgumentException("'initial_hp' must be between 'min_hp' and 'max_hp'.");
        }
    }
}