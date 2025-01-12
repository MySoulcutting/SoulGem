package com.whitesoul.soulgem.service

import com.whitesoul.soulgem.data.GemsData
import com.whitesoul.soulgem.file.gem.GemsFile
import org.bukkit.inventory.ItemStack

object ChaiXie {
    /**
     * 获取物品上的宝石
     */
    fun getGemsList(item: ItemStack?): ArrayList<ItemStack?> {
        val gemsList = ArrayList<ItemStack?>()
        // 遍历所有lore
        item?.itemMeta?.lore?.forEach {
            val gemID: String? = GemsFile.inlayGemsLore[it]
            val gemData: GemsData? = GemsFile.gems[gemID]
            val gem: ItemStack? = gemData?.item
            if (gem != null) {
                gemsList.add(gem)
            }
        }
        return gemsList
    }
    /**
     * 获取物品上是否有宝石
     */
    fun hasGems(item: ItemStack?): Boolean {
        val list = getGemsList(item)
        return list.isNotEmpty()
    }
}