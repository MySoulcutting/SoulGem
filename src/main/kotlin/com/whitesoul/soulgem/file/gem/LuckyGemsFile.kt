package com.whitesoul.soulgem.file.gem

import com.whitesoul.soulgem.data.LuckyGemsData
import org.bukkit.Material
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.nms.itemTagReader
import taboolib.platform.util.buildItem

object LuckyGemsFile {
    @Config("LuckyGems.yml")
    lateinit var config: ConfigFile

    val gems = HashMap<String, LuckyGemsData>()
    @Awake(LifeCycle.LOAD)
    fun load() {
        gems.clear()
        config.getConfigurationSection("Gems")?.getKeys(false)?.forEach { key ->
            val addChance = config.getDouble("Gems.$key.addChance")
            val itemType = config.getString("Gems.$key.item.type")
            val itemName = config.getString("Gems.$key.item.name")
            val itemLore = config.getStringList("Gems.$key.item.lore")
            val item = buildItem(Material.matchMaterial(itemType)) {
                name = itemName
                lore.addAll(itemLore)
                colored()
                hideAll()
            }
            item.itemTagReader {
                set("soulgem.luckyid", key)
                saveToItem(item)
            }
            gems[key] = LuckyGemsData(key, addChance, item)
        }
    }
}