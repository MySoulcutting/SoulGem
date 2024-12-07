package com.whitesoul.soulgem.file.gem

import com.whitesoul.soulgem.data.GemsData
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.chat.colored
import taboolib.module.nms.itemTagReader
import taboolib.platform.BukkitPlugin
import taboolib.platform.util.buildItem
import java.io.File

object GemsFile {
    val gems = HashMap<String, GemsData>()
    val inlayGemsLore = HashMap<String, String>()
    // 加载宝石文件
    @Awake(LifeCycle.LOAD)
    fun loadFiles() {
        gems.clear()
        val gemsDirectory = File(BukkitPlugin.getInstance().dataFolder, "gems")
        if (!gemsDirectory.exists()) {
            gemsDirectory.mkdirs()
            BukkitPlugin.getInstance().saveResource("gems/示例宝石.yml", false)
        }
        gemsDirectory.listFiles()?.forEach { file ->
            if (file.isFile && file.extension == "yml") {
                val gemsConfig = YamlConfiguration.loadConfiguration(file)
                val sec = gemsConfig.getConfigurationSection("Gems").getKeys(false)
                sec.forEach { key ->
                    val id = gemsConfig.getString("Gems.$key.id")
                    val type = gemsConfig.getString("Gems.$key.type")
                    val chance = gemsConfig.getDouble("Gems.$key.chance")
                    val inlayLore = gemsConfig.getString("Gems.$key.inlayLore")
                    val itemType = gemsConfig.getString("Gems.$key.item.type")
                    val itemName = gemsConfig.getString("Gems.$key.item.name")
                    val itemLore = gemsConfig.getStringList("Gems.$key.item.lore")
                    val attributes = gemsConfig.getStringList("Gems.$key.attributes")
                    val item = buildItem(Material.matchMaterial(itemType)) {
                        name = itemName
                        lore.addAll(itemLore)
                        colored()
                        hideAll()
                    }
                     item.itemTagReader {
                        set("soulgem.id", id)
                        set("soulgem.type", type)
                        saveToItem(item)
                    }
                    gems[id] = GemsData(id, type, chance, inlayLore, attributes, item)
                    inlayGemsLore[inlayLore.colored()] = id
                }
            }
        }
    }
}