package com.whitesoul.soulgem.file.gem

import com.whitesoul.soulgem.data.GemTypeData
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object GemTypeConf {
    val gemType = HashMap<String, GemTypeData>()
    @Config("GemType.yml")
    lateinit var config: ConfigFile

    @Awake(LifeCycle.LOAD)
    fun load() {
        gemType.clear()
        val sec = config.getConfigurationSection("GemType")?.getKeys(false)
        sec?.forEach { key ->
            val lore = config.getString("GemType.$key.lore")
            gemType[key] = GemTypeData(key, lore)
        }
    }
}