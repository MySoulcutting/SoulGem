package com.whitesoul.soulgem.ui

import com.whitesoul.soulgem.file.XiangQianConf
import com.whitesoul.soulgem.service.XiangQian
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Chest
import taboolib.platform.util.buildItem
import taboolib.platform.util.giveItem

object XiangQianUI {
    // 打开UI
    fun Player.openXiangQianUI() {
        openMenu<Chest> {
            handLocked(false)
            title = XiangQianConf.config.getString("Gui.Title")!!.colored()
            map(
                *XiangQianConf.config.getStringList("Gui.Slots").toTypedArray()
            )
            val sec = XiangQianConf.config.getConfigurationSection("Gui.Items")?.getKeys(false)
            if (sec != null) {
                for (key in sec) {
                    // 设置格子物品
                    when (key[0]) {
                        'A' -> {}
                        'B' -> {}
                        'C' -> {}
                        'D' -> {
                            set(
                                key[0],
                                buildItem(
                                    XMaterial.matchXMaterial(XiangQianConf.config.getString("Gui.Items.D.Type")!!).get()
                                ) {
                                    name = XiangQianConf.config.getString("Gui.Items.D.Name")
                                    lore.addAll(XiangQianConf.config.getStringList("Gui.Items.D.Lore"))
                                    hideAll()
                                    colored()
                                }) {
                                clickEvent().isCancelled = true
                                val item = getItem('A')
                                val gem = getItem('B')
                                XiangQian.xiangQianButton(player, item, gem)
                            }
                        }
                        else -> {
                            // 设置格子物品
                            set(
                                key[0],
                                buildItem(
                                    XMaterial.matchXMaterial(XiangQianConf.config.getString("Gui.Items.$key.Type")!!)
                                        .get()
                                ) {
                                    name = XiangQianConf.config.getString("Gui.Items.$key.Name")
                                    lore.addAll(XiangQianConf.config.getStringList("Gui.Items.$key.Lore"))
                                    hideAll()
                                    colored()
                                }) {
                                clickEvent().isCancelled =
                                    XiangQianConf.config.getBoolean("Gui.Items.$key.DisableClick")
                            }
                        }
                    }
                }
                // 关闭时处理
                onClose { event ->
                    val itemA: ItemStack? = event.inventory.getItem(getFirstSlot('A'))
                    val itemB: ItemStack? = event.inventory.getItem(getFirstSlot('B'))
                    val itemC: ItemStack? = event.inventory.getItem(getFirstSlot('C'))
                    player.giveItem(itemA)
                    player.giveItem(itemB)
                    player.giveItem(itemC)
                }
            }
        }
    }
}