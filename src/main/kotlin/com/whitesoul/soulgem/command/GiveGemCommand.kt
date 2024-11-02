package com.whitesoul.soulgem.command

import com.whitesoul.soulgem.file.GemsFile
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.giveItem

object GiveGemCommand {
    val command = subCommand {
        player("玩家名") {
            dynamic("宝石id") {
                suggestion<CommandSender> { _, _ ->
                    GemsFile.gems.keys.toList()
                }
                execute<CommandSender> {sender, context, args ->
                    val player = Bukkit.getPlayer(context["玩家名"])
                    val gemItem: ItemStack? = GemsFile.gems[context["宝石id"]]?.item
                    player.giveItem(gemItem)
                    sender.sendMessage("已给予玩家 ${player.name} 宝石 ${gemItem?.itemMeta?.displayName}")
                }
            }
        }
    }
}