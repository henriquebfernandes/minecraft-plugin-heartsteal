package com.github.henriquebfernandes.heartSteal.commands;

import com.github.henriquebfernandes.heartSteal.commands.subcommands.HealthPointsSubcommand;
import com.github.henriquebfernandes.heartSteal.commands.subcommands.PlayerDataSubcommand;
import com.github.henriquebfernandes.heartSteal.data.PlayerData;
import com.github.henriquebfernandes.heartSteal.managers.HealthManager;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class HeartStealCommand {
    public static LiteralCommandNode<CommandSourceStack> create(PlayerData playerData, HealthManager healthManager){
        return Commands.literal("heartsteal")
                .requires(source -> source.getSender()
                        .hasPermission("heartsteal.admin"))
                .then(HealthPointsSubcommand.create(healthManager))
                .then(PlayerDataSubcommand.create(playerData))
                .build();
    }
}
