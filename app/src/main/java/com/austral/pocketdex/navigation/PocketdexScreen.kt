package com.austral.pocketdex.navigation

import androidx.annotation.StringRes
import com.austral.pocketdex.R

enum class PocketdexScreen(@StringRes val labelId: Int) {
    Home(R.string.navigation_bottom_bar_home),
    Guess(R.string.navigation_bottom_bar_guess),
    Dex(R.string.navigation_bottom_bar_dex)
}