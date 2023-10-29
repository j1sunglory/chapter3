package fastcampus.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.chapter3.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val outputTextView = binding.outputTextView
    val outputUnitTextView = binding.outputUnitTextView
    val inputEditText = binding.inputEditText
    val inputUnitTextView = binding.inputUnitTextView
    val swapImageButton = binding.swapImageButton

    var inputNumber : Int = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val outputTextView = findViewById<TextView>(R.id.outputTextView)
//        뷰 바인딩을 하는 이유: findViewById로 가져오면 크래쉬가 나거나 다른 리소스 파일에 중복으로 id가 기재되거나, 리소스 파일에 없는 값에 할당할 경우 nullPointException 오류가 남

        inputEditText.addTextChangedListener{ text ->
//            inputNumber = text.toString().toInt()
            inputNumber = if(text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }
//            Log.d("inputNumber", inputNumber.toString())
//
//            var outputNumber = inputNumber.times(0.01)
//            outputTextView.text = outputNumber.toString()

            if(cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }

        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if(cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }

}

