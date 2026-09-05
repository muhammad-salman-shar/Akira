package com.salman.akira

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val red = Color.parseColor("#DC2626")
    private val black = Color.parseColor("#090A0F")
    private var isRed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById<FrameLayout>(R.id.root)
        val button = findViewById<Button>(R.id.toggleButton)

        root.setBackgroundColor(red)
        window.statusBarColor = red
        button.text = getString(R.string.change_to_black)

        button.setOnClickListener {
            val from = if (isRed) red else black
            val to = if (isRed) black else red

            ValueAnimator.ofObject(ArgbEvaluator(), from, to).apply {
                duration = 500
                addUpdateListener { anim ->
                    val color = anim.animatedValue as Int
                    root.setBackgroundColor(color)
                    window.statusBarColor = color
                }
                start()
            }

            isRed = !isRed
            button.text = getString(
                if (isRed) R.string.change_to_black else R.string.change_to_red
            )
        }
    }
}
