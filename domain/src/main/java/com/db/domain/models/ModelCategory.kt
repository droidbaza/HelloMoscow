package com.db.domain.models

data class ModelCategory(
    val type: Type,
    val words: List<String>,
    val spheres: List<Int>
)

enum class Type {
    EDUCATION,
    NATURE,
    SHOWS,
    ACTIONS,
    HOLIDAY,
    PROMO,
    CHILD,
    REGION,
    FRIENDS,
    EAT,
    LOVE
}

