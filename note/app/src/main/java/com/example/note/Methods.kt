package com.example.note

class Methods {
    fun setColorTheme() {
        when (Constant.color) {
            -0xbbcca -> Constant.theme = R.style.AppTheme_red
            -0x16e19d -> Constant.theme = R.style.AppTheme_pink
            -0x63d850 -> Constant.theme = R.style.AppTheme_darpink
            -0x98c549 -> Constant.theme = R.style.AppTheme_violet
            -0xc0ae4b -> Constant.theme = R.style.AppTheme_blue
            -0xfc560c -> Constant.theme = R.style.AppTheme_skyblue
            -0xb350b0 -> Constant.theme = R.style.AppTheme_green
            -0x6800 -> Constant.theme = R.style.AppTheme
            -0x616162 -> Constant.theme = R.style.AppTheme_grey
            -0x86aab8 -> Constant.theme = R.style.AppTheme_brown
            else -> Constant.theme = R.style.AppTheme
        }
    }
}
