package com.ao2.domain.model

data class Inventory(
    val food_id: Int,
    val title: String,
    val description: String,
    val food_img_path: String,
    val food_count: Int,
)
