package com.whitesoul.soulgem

import com.whitesoul.soulgem.file.GemTypeConf
import com.whitesoul.soulgem.file.GemsFile
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.chat.colored

object SoulGemPlugin : Plugin() {

    override fun onEnable() {
        info("成功加载魂之宝石插件!")
        info("&a成功加载 &f${GemsFile.gems.size} &a个宝石".colored())
        info("&a成功加载 &f${GemTypeConf.gemType.size} &a个宝石类型".colored())
    }
}