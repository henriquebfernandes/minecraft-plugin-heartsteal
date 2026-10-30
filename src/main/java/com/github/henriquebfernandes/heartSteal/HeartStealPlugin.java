package com.github.henriquebfernandes.heartSteal;

import com.github.henriquebfernandes.heartSteal.commands.HeartStealCommand;
import com.github.henriquebfernandes.heartSteal.config.HeartStealConfig;
import com.github.henriquebfernandes.heartSteal.managers.HealthManager;
import com.github.henriquebfernandes.heartSteal.listeners.PlayerListener;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeartStealPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getLogger().info("[HeartSteal] Config loaded");

        //HeartStealConfig
        HeartStealConfig heartStealConfig = new HeartStealConfig(this);

        //HealthManager
        HealthManager healthManager = new HealthManager(heartStealConfig);

        //PlayerListener
        getServer().getPluginManager().registerEvents(new PlayerListener(healthManager),this);

        //HeartStealCommand
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(HeartStealCommand.create(healthManager));
        });

        getLogger().info("[HeartSteal] Enabling HeartSteal");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[HeartSteal] Disabling HeartSteal");
    }
}
