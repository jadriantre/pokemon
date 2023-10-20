package com.example.pokemon.presentation.util

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.example.pokemon.R
import com.squareup.picasso.Callback
import java.util.Locale

class CircularImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val textView: TextView

    private var placeholderResAvatar: Int = R.drawable.placeholder
    private var placeholderResBackground: Int = R.drawable.placeholder_background
    private var initialsBackground: Int = Color.GRAY
    private var initialsTextColor: Int = Color.GRAY

    init {
        LayoutInflater.from(context).inflate(R.layout.circular_image, this)
        imageView = findViewById(R.id.ivCircular)
        textView = findViewById(R.id.tvCircular)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularImage)
        placeholderResAvatar =
            typedArray.getResourceId(R.styleable.CircularImage_placeholder, R.drawable.placeholder)
        initialsBackground =
            typedArray.getColor(R.styleable.CircularImage_initialsBackground, Color.GRAY)
        initialsTextColor =
            typedArray.getColor(R.styleable.CircularImage_initialsTextColor, Color.GRAY)
        typedArray.recycle()
    }

    fun setImageOrName(imageUrl: String, name: String) {
        if (imageUrl.isNotEmpty()) {
            Picasso.with(context).load(imageUrl)
                .placeholder(placeholderResAvatar)
                .error(placeholderResAvatar)
                .into(imageView,
                    object: Callback {
                        override fun onSuccess() {}
                        override fun onError() {
                            updateInitialsView(name)
                        }
                    })
        } else {
            updateInitialsView(name)
        }
    }

    private fun updateInitialsView(name: String) {
        val initials = getInitials(name)
        if(initials != ""){
            textView.text = initials
            textView.setBackgroundColor(initialsBackground)
            textView.setTextColor(initialsTextColor)
            imageView.setImageResource(placeholderResBackground)
        }else
            imageView.setImageResource(placeholderResAvatar)
    }

    private fun getInitials(name: String): String {
        val words = name.split(" ")
        return when {
            words.size >= 2 -> {
                val firstInitial = words[0].take(1).uppercase(Locale.ROOT)
                val secondInitial = words[1].take(1).uppercase(Locale.ROOT)
                "$firstInitial$secondInitial"
            }
            words.isNotEmpty() -> {
                words[0].take(1).uppercase(Locale.ROOT)
            }
            else -> {
                ""
            }
        }
    }
}