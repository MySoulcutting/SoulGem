package com.whitesoul.soulgem.service

import com.whitesoul.soulgem.file.GemTypeConf
import com.whitesoul.soulgem.file.GemsFile
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
    fun xiangQianButton(player: Player, item: ItemStack?, gemItem: ItemStack?) {
        // 获取宝石类型
        val gemType = GemTypeConf.gemType[gemItem?.getItemTag()?.getDeep("soulgem.type")?.asString()]
        val gem = GemsFile.gems[gemItem?.getItemTag()?.getDeep("soulgem.id")?.asString()]
        val gemLore = gemType?.checkLore
        val randomChance = random(0.0,100.0)
        // 替换Lore
        if (item?.hasLore(gemLore) == true && gem !=null) {
            // 概率计算
            if (randomChance < gem.chance) {
                item.replaceFirstLore(gemLore, gem.inlayLore.colored())
                gemItem?.amount = gemItem?.amount?.minus(1)!! // 减少宝石数量
                player.sendInfo("XiangQian-Success", gemItem.itemMeta?.displayName?: "未知", item.itemMeta?.displayName?: "未知")
            } else {
                gemItem?.amount = gemItem?.amount?.minus(1)!! // 减少宝石数量
                player.sendInfo("XiangQian-Fail")
            }
        } else {
            player.sendInfo("XiangQian-Fail_2")
        }
    }
    // 幸运值计算

}