package com.github.henriquebfernandes.heartSteal.commands.subcommands;

import com.github.henriquebfernandes.heartSteal.managers.HealthManager;
import com.github.henriquebfernandes.heartSteal.utils.CommandUtils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class HealthPointsSubcommand {
    public static LiteralArgumentBuilder<CommandSourceStack> create(HealthManager healthManager) {
        return Commands.literal("hp")
                .then(Commands.literal("reset")
                        .executes(context ->
                                CommandUtils.executeAndSendResult(context,
                                        healthManager::reset,
                                        "Health points reset.\nCurrent health points: ",
                                        "-"
                                )
                        )
                )

                .then(Commands.literal("get")
                        .executes(context ->
                                CommandUtils.executeAndSendResult(context,
                                        healthManager::get,
                                        "Health points fetched successfully.\nCurrent health points: ",
                                        "-"
                                )
                        )
                )

                .then(Commands.literal("set")
                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                .executes(context -> {
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    return CommandUtils.executeAndSendResult(context,
                                            player -> healthManager.set(player, amount),
                                            "Health points set.\nCurrent health points: ",
                                            "-"
                                    );
                                })
                        )
                );
    }
}
