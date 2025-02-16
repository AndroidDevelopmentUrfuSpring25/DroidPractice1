package ru.urfu.droidpractice1.demo

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.databinding.DemoSurveyLayoutBinding
import ru.urfu.droidpractice1.demo.model.IMAGE_URL
import ru.urfu.droidpractice1.utils.startOtherActivity


class DemoViewsActivity: ComponentActivity() {

    private lateinit var binding: DemoSurveyLayoutBinding

    private var isAnonymouslyChecked = false
    private var selectedModel: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedModel = savedInstanceState?.getString(SELECTED_MODEL_KEY)
        binding = DemoSurveyLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.photo)
            .asBitmap()
            .load(IMAGE_URL)
            .into(binding.photo)

        binding.anonymouslySwitch.setOnCheckedChangeListener { _, isChecked ->
            isAnonymouslyChecked = isChecked
            binding.inputName.isVisible = isChecked.not()
        }

        binding.surveyModels.setOnCheckedChangeListener { group, checkedId ->
            val selectedOption = view.findViewById<RadioButton>(checkedId).text.toString()
            selectedModel = selectedOption
            binding.done.text = getString(R.string.survey_button_done, selectedOption)
            binding.done.isVisible = true
        }

        val surveyModels = resources.getStringArray(R.array.survey_models)
        surveyModels.forEach { option ->
            val radio = RadioButton(this).apply { text = option }
            binding.surveyModels.addView(radio)
            if (option == selectedModel) binding.surveyModels.check(radio.id)
        }

        binding.done.setOnClickListener {
            val intent = Intent(this, DemoResultActivity::class.java).apply {
                putExtra(SELECTED_MODEL_KEY, selectedModel)
            }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SELECTED_MODEL_KEY, selectedModel)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val SELECTED_MODEL_KEY = "SELECTED_MODEL_KEY"
    }
}