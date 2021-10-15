package com.example.shemajamebeli5.model


import com.squareup.moshi.Json

data class UserSubListItem(
    @Json(name = "field_id")
    val field_id: Int,
    @Json(name = "field_type")
    val field_type: String,
    @Json(name = "hint")
    val hint: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "is_active")
    val isActive: Boolean,
    @Json(name = "keyboard")
    val keyboard: String,
    @Json(name = "required")
    val required: String
)