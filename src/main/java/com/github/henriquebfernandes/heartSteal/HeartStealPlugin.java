package com.github.henriquebfernandes.heartSteal;

import com.github.henriquebfernandes.heartSteal.commands.HeartStealCommand;
import com.github.henriquebfernandes.heartSteal.config.HeartStealConfig;
import com.github.henriquebfernandes.heartSteal.data.PlayerData;
import com.github.henriquebfernandes.heartSteal.managers.HealthManager;
import com.github.henriquebfernandes.heartSteal.listeners.PlayerCombatListener;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeartStealPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getLogger().info("[HeartSteal] Config loaded");

        //HeartStealConfig
        HeartStealConfig config = new HeartStealConfig(this);

        //PlayerData
        PlayerData playerData = new PlayerData(this, config);

        //HealthManager
        HealthManager healthManager = new HealthManager(playerData);

        //PlayerCombatListener
        getServer().getPluginManager().registerEvents(new PlayerCombatListener(healthManager),this);

        //HeartStealCommand
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(HeartStealCommand.create(playerData, healthManager));
        });

        getLogger().info("[HeartSteal] Enabling HeartSteal");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[HeartSteal] Disabling HeartSteal");
    }
}
