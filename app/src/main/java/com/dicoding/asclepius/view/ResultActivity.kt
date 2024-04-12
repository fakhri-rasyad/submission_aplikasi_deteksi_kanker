package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.R
import com.dicoding.asclepius.database.Analysis
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.helper.ImageClassifierHelper
import com.dicoding.asclepius.viewmodel.AnalysisViewModel
import com.dicoding.asclepius.viewmodel.ViewModelFactory
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.text.NumberFormat

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var analysisViewModel: AnalysisViewModel
    private var analysisResult : Analysis? = null

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.

        analysisViewModel = obtainViewModel(this@ResultActivity)

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        binding.progressBar.visibility = View.VISIBLE
        imageUri.let {
            binding.resultImage.setImageURI(it)
            analyzeImage(it)
        }

        binding.saveButton.setOnClickListener{
            if (analysisResult == null){
                showToast("Analysis insert Error")
            } else {
                analysisViewModel.insert(analysisResult!!)
                showToast("Analysis data inserted")
            }
        }


    }

    private fun obtainViewModel(activity: AppCompatActivity): AnalysisViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(AnalysisViewModel::class.java)
    }

    private fun analyzeImage(uri : Uri){
        binding.progressBar.visibility = View.GONE
        imageClassifierHelper = ImageClassifierHelper(
            context = this,
            classifierListener = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(error: String) {
                    runOnUiThread {
                        showToast("Error analyzing image")
                    }
                }

                override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                    runOnUiThread {
                        results?.let{
                            if(it.isNotEmpty() && it[0].categories.isNotEmpty()){
                                println(it)
                                val processResult = it[0].categories[0]
                                val resultLabel = processResult.label
                                val resultScore = processResult.score
                                binding.resultText.text = "Hasil analisis:\n Score: " + NumberFormat.getPercentInstance().format(resultScore).toString() + "\n Tipe : ${resultLabel} \n InferenceTime : $inferenceTime ms"

                                analysisResult = Analysis(
                                    uri = uri.toString(),
                                    type = resultLabel,
                                    confidence = resultScore)

                            } else {
                                binding.resultText.text = getString(R.string.analysis_error)
                            }
                        }
                    }
                }

            }
        )

        imageClassifierHelper.classifyStaticImage(uri)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}