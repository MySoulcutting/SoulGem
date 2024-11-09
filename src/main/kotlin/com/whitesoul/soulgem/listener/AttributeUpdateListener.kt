package com.whitesoul.soulgem.listener

import com.whitesoul.soulgem.file.ConfigFile
import com.whitesoul.soulgem.file.GemsFile
import eos.moe.dragoncore.api.SlotAPI
import org.bukkit.inventory.ItemStack
import org.serverct.ersha.api.AttributeAPI
import org.serverct.ersha.api.event.AttrUpdateAttributeEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored

object AttributeUpdateListener {
    val attributeList = ArrayList<String>()
    @SubscribeEvent
    fun onAttributeUpdate(e: AttrUpdateAttributeEvent.Before) {
        val player = e.attributeData.getPlayer()
        val attributeData = e.attributeData
        val itemList = ArrayList<ItemStack?>()
        // 获取玩家装备
        val itemMainHand: ItemStack? = player?.inventory?.itemInMainHand
        val itemOffHand: ItemStack? = player?.inventory?.itemInOffHand
        val armorHead: ItemStack? = player?.inventory?.helmet
        val armorChest: ItemStack? = player?.inventory?.chestplate
        val armorLegs: ItemStack? = player?.inventory?.leggings
        val armorFeet: ItemStack? = player?.inventory?.boots
        ConfigFile.config.getStringList("DragonCoreSlot").forEach { slot ->
            itemList.add(SlotAPI.getCacheSlotItem(player, slot))
        }
        itemList.apply { add(itemMainHand); add(itemOffHand); add(armorHead); add(armorChest); add(armorLegs); add(armorFeet) }
        // 更新宝石属性
        attributeList.clear()
        updateGemItemList(itemList)
        AttributeAPI.addSourceAttribute(attributeData,"SoulGem", attributeList)
    }
    // 更新宝石属性
    fun updateGemItemList(itemList: ArrayList<ItemStack?>) {
        itemList.forEach { itemStack ->
            updateGemAttribute(itemStack)
        }
    }
    fun updateGemAttribute(itemStack: ItemStack?) {
        val itemLore = itemStack?.itemMeta?.lore
        itemLore?.forEach { key ->
            val gemID = GemsFile.inlayGemsLore[key]
            val gemData = GemsFile.gems[gemID]
            val inlayLore = gemData?.inlayLore?.colored()
            if (key == inlayLore) {
                gemData?.attributes?.toMutableList()?.let { attributeList.addAll(it) }
            }
        }
    }
}