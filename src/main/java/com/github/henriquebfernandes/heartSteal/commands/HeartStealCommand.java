package com.github.henriquebfernandes.heartSteal.commands;

import com.github.henriquebfernandes.heartSteal.managers.HealthManager;
import com.github.henriquebfernandes.heartSteal.utils.CommandUtils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class HeartStealCommand {
    public static LiteralCommandNode<CommandSourceStack> create(HealthManager healthManager){
        return Commands.literal("heartsteal")
                .requires(source -> source.getSender()
                        .hasPermission("heartsteal.admin"))
                .then(Commands.literal("hp")
                        .then(Commands.literal("reset")
                                .executes(context ->
                                        CommandUtils.executeAndSendResult(context,
                                                healthManager::reset,
                                                "Health points reset.\nCurrent health points: ",
                                                "-"
                                        )
                                )
                        )

                        .then(Commands.literal("reward")
                                .executes(context ->
                                        CommandUtils.executeAndSendResult(context,
                                                healthManager::reward,
                                                "Rewarded player.\nCurrent health points: ",
                                                "-"
                                        )
                                )
                        )

                        .then(Commands.literal("punish")
                                .executes(context ->
                                        CommandUtils.executeAndSendResult(context,
                                                healthManager::punish,
                                                "Punished player.\nCurrent health points: ",
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
                        ))
                .build();
    }
}
