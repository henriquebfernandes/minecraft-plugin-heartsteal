package com.github.henriquebfernandes.heartSteal.config;

import com.github.henriquebfernandes.heartSteal.HeartStealPlugin;

public final class HeartStealConfig {
    public final int killReward;
    public final int deathPunishment;
    public final int maxHP;
    public final int minHP;
    public final int initialHP;


    public HeartStealConfig(HeartStealPlugin plugin) {
        var config = plugin.getConfig();

        killReward = config.getInt("kill_reward", 2);
        deathPunishment = config.getInt("death_punishment", 2);
        maxHP = config.getInt("max_health_points", 20);
        minHP = config.getInt("min_health_points", 2);
        initialHP = config.getInt("initial_health_points", 20);

        validateConfig();
    }

    private void validateConfig() {
        if (minHP <= 0) {
            throw new IllegalArgumentException("'min_health_points' must be greater than 0.");
        }

        if (maxHP <= 0) {
            throw new IllegalArgumentException("'max_health_points' must be greater than 0.");
        }

        if (maxHP < minHP) {
            throw new IllegalArgumentException("'max_health_points' must be greater than or equal to 'min_health_points'.");
        }

        if (initialHP < minHP || initialHP > maxHP) {
            throw new IllegalArgumentException("'initial_health_points' must be between 'min_health_points' and 'max_health_points'.");
        }
    }
}