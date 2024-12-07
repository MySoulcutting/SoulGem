package com.whitesoul.soulgem.command.sub

import com.whitesoul.soulgem.file.gem.GemsFile
import com.whitesoul.soulgem.file.gem.LuckyGemsFile
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.giveItem

object GiveLuckyGemCommand {
    val command = subCommand {
        player("玩家名") {
            dynamic("宝石id") {
                suggestion<CommandSender> { _, _ ->
                    LuckyGemsFile.gems.keys.toList()
                }
                execute<CommandSender> { sender, context, args ->
                    val player = Bukkit.getPlayer(context["玩家名"])
                    val gemItem: ItemStack? = LuckyGemsFile.gems[context["宝石id"]]?.item
                    player.giveItem(gemItem)
                    sender.sendMessage("已给予玩家 ${player.name} 宝石 ${gemItem?.itemMeta?.displayName}")
                }
            }
        }
    }
}