package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding


class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var cooked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "onCreate()")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cooked = intent.getBooleanExtra(KEY_COOKED, false)
        val ingredientKeys = resources.getStringArray(R.array.recipe_ingredients_keys)
        val ingredientValues = resources.getStringArray(R.array.recipe_ingredients_values)
        val contentTexts = resources.getStringArray(R.array.recipe_text)
        val contentImages = resources.getStringArray(R.array.recipe_images)

        fillTable(binding.recipeTable, ingredientKeys, ingredientValues)
        fillContent(binding.contentGroup, contentTexts, contentImages)

        binding.cookedSwitch.isChecked = cooked
        binding.cookedSwitch.setOnCheckedChangeListener { _, isChecked -> cooked = isChecked }
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_COOKED, cooked) })
            finish()
        }
    }

    private fun fillTable(tableLayout: TableLayout, keys: Array<String>, values: Array<String>) {
        if (keys.size != values.size) throw Exception()
        for (i in keys.indices) {
            val key = TextView(this).apply {
                text = keys[i]
            }
            val value = TextView(this).apply {
                text = values[i]
                layoutParams = TableRow.LayoutParams().apply {
                    setMargins(0, 0, 0, 16)
                    gravity = Gravity.END
                }
            }
            val row = TableRow(this).apply {
                addView(key)
                addView(value)
            }
            tableLayout.addView(row)
        }
    }

    private fun fillContent(
        contentGroup: LinearLayout,
        texts: Array<String>,
        images: Array<String>
    ) {
        if (texts.size != images.size) throw Exception()
        for (i in texts.indices) {
            val textView = TextView(this).apply {
                text = texts[i]
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 16, 0, 64) }
            }
            val imageView = ImageView(this)
            Glide.with(imageView).asBitmap().load(images[i]).into(imageView)
            contentGroup.addView(imageView)
            contentGroup.addView(textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_COOKED, cooked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cooked = savedInstanceState.getBoolean(KEY_COOKED)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy()")
    }

    companion object {
        const val KEY_COOKED = "cooked"
    }
}