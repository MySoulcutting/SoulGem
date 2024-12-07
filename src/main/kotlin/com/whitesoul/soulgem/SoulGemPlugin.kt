package com.whitesoul.soulgem

import com.whitesoul.soulgem.file.gem.GemTypeConf
import com.whitesoul.soulgem.file.gem.GemsFile
import com.whitesoul.soulgem.file.gem.LuckyGemsFile
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.chat.colored
import taboolib.platform.BukkitPlugin

object SoulGemPlugin : Plugin() {

    override fun onEnable() {
        info("成功加载魂之宝石插件!")
        info("&6正在加载宝石配置文件...".colored())
        info("&a成功加载 &f${GemsFile.gems.size} &a个宝石".colored())
        info("&6正在加载宝石类型配置文件...".colored())
        info("&a成功加载 &f${GemTypeConf.gemType.size} &a个宝石类型".colored())
        info("&6正在加载幸运石配置文件...".colored())
        info("&a成功加载 &f${LuckyGemsFile.gems.size} &a个幸运石".colored())
        info("&e当前版本 ${BukkitPlugin.getInstance().description.version}".colored())
        info("&bBy: 白魂".colored())
    }
}