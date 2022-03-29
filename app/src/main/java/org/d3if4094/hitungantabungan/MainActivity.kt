package org.d3if4094.hitungantabungan

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import org.d3if4094.hitungantabungan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hasilButton.setOnClickListener { hasilUang() }
        binding.resetButton.setOnClickListener { reset() }
    }

    private fun hasilUang(){
        hideKeyboard()
        val jumlahUang = binding.outlinedTextFieldJumlahUangInput.text.toString()
        val targetUang = binding.outlinedTextFieldTargetUangInput.text.toString()

        if(!TextUtils.isEmpty(jumlahUang) && !TextUtils.isEmpty(targetUang)){
            if(jumlahUang.toInt() >= targetUang.toInt()){
                Toast.makeText(this, R.string.invalid_nabung, Toast.LENGTH_LONG).show()
                binding.textViewHasil.text= ""
                return
            }else {
                val hasil = targetUang.toDouble() / jumlahUang.toDouble()
                val hasilAkhir = hasil.toInt().toString()

                binding.textViewHasil.text = getString(R.string.hasil, hasilAkhir)
            }

        }else{
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_LONG).show()
            return
        }
    }
    private fun reset(){
        binding.outlinedTextFieldJumlahUangInput.setText("")
        binding.outlinedTextFieldTargetUangInput.setText("")
        binding.textViewHasil.text= ""
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}