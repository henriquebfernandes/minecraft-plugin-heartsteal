package com.github.henriquebfernandes.heartSteal.data;

import com.github.henriquebfernandes.heartSteal.HeartStealPlugin;
import com.github.henriquebfernandes.heartSteal.config.HeartStealConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public class PlayerData {
    private final HeartStealConfig config;
    public final Map<String, NamespacedKey> keys;
    
    public PlayerData(HeartStealPlugin plugin, HeartStealConfig config) {
        this.config = config;
        this.keys = Map.of(
                "kill_reward", new NamespacedKey(plugin, "kill_reward"),
                "death_punishment", new NamespacedKey(plugin, "death_punishment"),
                "max_health_points", new NamespacedKey(plugin, "max_health_points"),
                "min_health_points", new NamespacedKey(plugin, "min_health_points"),
                "initial_health_points", new NamespacedKey(plugin, "initial_health_points")
        );
    }

    public Object get(Player player, String keyName) {
        return player.getPersistentDataContainer()
                .get(keys.get(keyName), PersistentDataType.INTEGER);
    }

    public int getOrDefault(Player player, String keyName) {
        return player.getPersistentDataContainer()
                .getOrDefault(keys.get(keyName), PersistentDataType.INTEGER, config.get(keyName));
    }

    public int set(Player player, String keyName, int amount) {
        player.getPersistentDataContainer()
                .set(keys.get(keyName), PersistentDataType.INTEGER, amount);
        return amount;
    }

    public Object reset(Player player, String keyName) {
        player.getPersistentDataContainer()
                .remove(keys.get(keyName));
        return null;
    }
}