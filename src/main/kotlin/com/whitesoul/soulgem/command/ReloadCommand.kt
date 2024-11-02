package com.whitesoul.soulgem.command

import com.whitesoul.soulgem.file.ConfigFile
import com.whitesoul.soulgem.file.GemTypeConf
import com.whitesoul.soulgem.file.GemsFile
import com.whitesoul.soulgem.file.XiangQianConf
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand

object ReloadCommand {
    val command = subCommand {
        execute<CommandSender> {sender, context, argument ->
            GemsFile.loadFiles()
            GemTypeConf.config.reload()
            GemTypeConf.load()
            XiangQianConf.config.reload()
            ConfigFile.config.reload()
            sender.sendMessage("§a配置文件重载完成")
        }
    }
}