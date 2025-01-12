package com.whitesoul.soulgem.command.sub

import com.whitesoul.soulgem.ui.ChaiXieUI.openChaiXieUI
import com.whitesoul.soulgem.ui.XiangQianUI.openXiangQianUI
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand

object OpenCommand {
    val command = subCommand {
        player("玩家名") {
            literal("镶嵌界面") {
                execute<CommandSender>  {sender, context, argument ->
                    val player = Bukkit.getPlayer(context["玩家名"])
                    player.openXiangQianUI()
                }
            }
            literal("拆卸界面") {
                execute<CommandSender>  {sender, context, argument ->
                    val player = Bukkit.getPlayer(context["玩家名"])
                    player.openChaiXieUI()
                }
            }
        }
    }
}