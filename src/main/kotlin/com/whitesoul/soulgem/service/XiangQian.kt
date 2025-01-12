package com.whitesoul.soulgem.service

import com.whitesoul.soulgem.file.gem.GemTypeConf
import com.whitesoul.soulgem.file.gem.GemsFile
import com.whitesoul.soulgem.file.gem.LuckyGemsFile
import com.whitesoul.soulgem.util.ItemUtil.replaceFirstLore
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.common.util.random
import taboolib.module.chat.colored
import taboolib.module.nms.getItemTag
import taboolib.platform.util.hasLore
import taboolib.platform.util.sendInfo

object XiangQian {
    /**
     * 镶嵌按钮
     */
    fun xiangQianButton(player: Player, item: ItemStack?, gemItem: ItemStack?, luckyGemItem: ItemStack?) {
        // 获取宝石类型
        val gemType = GemTypeConf.gemType[gemItem?.getItemTag()?.getDeep("soulgem.type")?.asString()]
        val gem = GemsFile.gems[gemItem?.getItemTag()?.getDeep("soulgem.id")?.asString()]
        val gemLore = gemType?.checkLore
        // 替换Lore
        if (item?.hasLore(gemLore) == true && gem !=null) {
            // 减少宝石数量
            gemItem?.amount = gemItem?.amount?.minus(1)!!
            luckyGemItem?.amount = gemItem.amount.minus(1)
            val chance = chance(luckyGemItem)
            // 概率计算
            if (chance < gem.chance) {
                item.replaceFirstLore(gemLore, gem.inlayLore.colored())
                player.sendInfo("XiangQian-Success", gemItem.itemMeta?.displayName?: "未知", item.itemMeta?.displayName?: "未知")
            } else {
                player.sendInfo("XiangQian-Fail")
            }
        } else {
            player.sendInfo("XiangQian-Fail_2")
        }
    }
    // 幸运值计算
    fun chance(luckyGemItem: ItemStack?): Double {
        val randomChance = random(0.0,100.0)
        if (luckyGemItem !=null && luckyGemItem.getItemTag().getDeep("soulgem.luckyid")?.asString() != null) {
            val luckyGem = LuckyGemsFile.gems[luckyGemItem.getItemTag().getDeep("soulgem.luckyid")?.asString()]
            val addChance = luckyGem?.addChance
            return randomChance.minus(addChance!!)
        }
        return randomChance
    }
    // 装备上是否有孔
    fun hasInlayKey(item: ItemStack?): Boolean {
        val inlayLore = GemTypeConf.gemType.values
        inlayLore.forEach {
            if (item?.hasLore(it.checkLore) == true) {
                return true
            }
        }
        return false
    }
    // 是否为宝石
    fun hasGem(item: ItemStack?): Boolean {
        return item?.getItemTag()?.getDeep("soulgem.id")?.asString() != null
    }
    // 是否为幸运石
    fun hasLuckyGem(item: ItemStack?): Boolean {
        return item?.getItemTag()?.getDeep("soulgem.luckyid")?.asString() != null
    }
}