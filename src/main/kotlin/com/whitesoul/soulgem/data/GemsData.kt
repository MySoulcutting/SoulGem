package com.whitesoul.soulgem.data

import org.bukkit.inventory.ItemStack

data class GemsData(
    val id: String,
    val type: String,
    val chance: Double,
    val inlayLore: String,
    val attributes: List<String>,
    val item: ItemStack
)
