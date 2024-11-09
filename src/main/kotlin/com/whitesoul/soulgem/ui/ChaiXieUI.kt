package com.whitesoul.soulgem.ui

import com.whitesoul.soulgem.file.GemTypeConf
import com.whitesoul.soulgem.file.GemsFile
import com.whitesoul.soulgem.service.ChaiXie
import com.whitesoul.soulgem.util.ItemUtil.replaceFirstLore
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.function.submit
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.module.nms.getI18nName
import taboolib.module.nms.getItemTag
import taboolib.module.ui.ClickEvent
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Chest
import taboolib.platform.util.buildItem
import taboolib.platform.util.hasLore
import taboolib.platform.util.modifyLore

object ChaiXieUI {
    // 打开UI
    fun Player.openChaiXieUI() {
        val gemList = ArrayList<ItemStack>()
        openMenu<Chest> {
            handLocked(false)
            title = "拆卸界面".colored()
            map(
                "####X####",
                "#########",
                "#@@@@@@@#",
                "#@@@@@@@#",
                "#########"
            )
            set('#') {
                buildItem(XMaterial.BLACK_STAINED_GLASS_PANE)
            }
            onClick('X') { event: ClickEvent ->
                event.isCancelled = false
                submit(delay = 2) {
                    val item = event.getItem('X')
                    player.sendMessage("你放入了 ${item?.getI18nName()}")
                    if (item != null) {
                        val gemSlots = ArrayList<Int>()
                        val gemItems: ArrayList<ItemStack?> = ChaiXie.getGemsList(item)
                        // 先清除
                        getSlots('@').forEach { slot ->
                            event.inventory.setItem(slot, null)
                            gemSlots.add(slot)
                        }
                        // 再放入
                        gemSlots.forEach {
                            event.inventory.setItem(it, gemItems.removeFirst())
                        }
                    } else {
                        getSlots('@').forEach {
                            event.inventory.setItem(it, null)
                        }
                    }
                }
            }
            // 拆卸宝石
            onClick('@') { event: ClickEvent ->
                event.isCancelled = true
                if (event.slot == 'X') return@onClick
                val slot = event.rawSlot
                val item = event.getItem('X')
                val gemItem = event.inventory.getItem(slot)
                player.sendMessage("你拆去了 ${event.inventory.getItem(slot).itemMeta?.displayName}")
                // 获取宝石信息
                val gemID = gemItem?.getItemTag()?.getDeep("soulgem.id")?.asString()
                val gemType = gemItem?.getItemTag()?.getDeep("soulgem.type")?.asString()
                val gemData = GemsFile.gems[gemID]
                val inlayLore = gemData?.inlayLore?.colored()
                val typeLore = GemTypeConf.gemType[gemType]?.checkLore?.colored()
                // 拆卸
                item?.replaceFirstLore(inlayLore, typeLore)
                player.inventory.addItem(gemData?.item)
                event.inventory.setItem(slot, null)
            }
            onClose { event: InventoryCloseEvent ->
                player.inventory.addItem(event.inventory.getItem(getFirstSlot('X')))
            }
        }
    }
}