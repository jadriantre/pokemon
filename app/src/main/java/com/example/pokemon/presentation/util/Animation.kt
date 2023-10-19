package com.example.pokemon.presentation.util

import androidx.navigation.NavOptions
import com.example.pokemon.R

class Animation {
    companion object {
        val navOptions =  NavOptions.Builder()
            .setEnterAnim(R.anim.from_right)
            .setExitAnim(R.anim.to_left)
            .setPopEnterAnim(R.anim.from_left)
            .setPopExitAnim(com.google.android.material.R.anim.abc_fade_out)
            .build()
    }
}