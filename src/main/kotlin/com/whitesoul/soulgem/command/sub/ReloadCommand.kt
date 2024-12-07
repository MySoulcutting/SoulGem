package com.whitesoul.soulgem.command.sub

import com.whitesoul.soulgem.file.ConfigFile
import com.whitesoul.soulgem.file.gem.GemTypeConf
import com.whitesoul.soulgem.file.gem.GemsFile
import com.whitesoul.soulgem.file.gem.LuckyGemsFile
import com.whitesoul.soulgem.file.ui.ChaiXieConf
import com.whitesoul.soulgem.file.ui.XiangQianConf
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand

object ReloadCommand {
    val command = subCommand {
        execute<CommandSender> {sender, _, _ ->
            // 宝石
            GemsFile.loadFiles()
            // 宝石类型
            GemTypeConf.config.reload()
            GemTypeConf.load()
            // UI
            XiangQianConf.config.reload()
            ChaiXieConf.config.reload()
            // 主配置
            ConfigFile.config.reload()
            // 幸运石
            LuckyGemsFile.config.reload()
            LuckyGemsFile.load()

            sender.sendMessage("§a配置文件重载完成")
        }
    }
}