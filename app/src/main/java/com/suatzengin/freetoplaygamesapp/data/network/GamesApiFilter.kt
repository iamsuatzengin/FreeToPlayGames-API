package com.suatzengin.freetoplaygamesapp.data.network


// filter for platform
enum class GamesApiFilter(val value: String) {
    SHOW_ALL("all"),
    SHOW_PC("pc"),
    SHOW_BROWSER("browser")
}