package com.whitesoul.soulgem.service

import com.whitesoul.soulgem.file.GemTypeConf
import com.whitesoul.soulgem.file.GemsFile
import com.whitesoul.soulgem.util.ItemUtil.replaceFirstLore
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.common.util.random
import taboolib.common.util.randomDouble
import taboolib.module.chat.colored
import taboolib.module.nms.getItemTag
import taboolib.platform.util.hasLore
import taboolib.platform.util.hasName

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
                player.sendMessage("成功将 &6${gemItem.itemMeta?.displayName}&f 镶嵌到 &6${item.itemMeta?.displayName}&f 上".colored())
            } else {
                gemItem?.amount = gemItem?.amount?.minus(1)!! // 减少宝石数量
                player.sendMessage("镶嵌失败，宝石已经破碎".colored())
            }
        } else {
            player.sendMessage("该物品无法镶嵌此宝石".colored())
        }
    }
    // 幸运值计算

}