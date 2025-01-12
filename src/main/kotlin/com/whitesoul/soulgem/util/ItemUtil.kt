package com.whitesoul.soulgem.util

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import taboolib.module.chat.colored
import taboolib.platform.util.hasLore

object ItemUtil {
    // 替换Lore
    fun ItemStack.replaceFirstLore(oldValue: String?, newValue: String?): ItemStack {
        if (hasLore()) {
            val meta = itemMeta!!
            val lore = meta.lore!!
            val index = lore.indexOfFirst { it == oldValue?.colored() }
            if (index != -1) {
                lore[index] = newValue?.colored()
            }
            meta.lore = lore
            itemMeta = meta
        }
        return this
    }
}