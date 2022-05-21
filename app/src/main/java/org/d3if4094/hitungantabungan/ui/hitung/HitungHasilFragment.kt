package org.d3if4094.hitungantabungan.ui.hitung

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if4094.hitungantabungan.R
import org.d3if4094.hitungantabungan.databinding.FragmentHitungBinding
import org.d3if4094.hitungantabungan.db.HistoriDb
import org.d3if4094.hitungantabungan.model.HasilHitung

class HitungHasilFragment : Fragment() {
    private lateinit var binding : FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = HistoriDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)

        ViewModelProvider(this,factory)[HitungViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hasilButton.setOnClickListener { hasilUang() }
        binding.resetButton.setOnClickListener { reset() }

        viewModel.getHasilHitung().observe(requireActivity()) { showResult(it) }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungHasilFragment_to_historiFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hasilUang(){
        requireActivity().hideKeyboard()
        val jumlahUang = binding.outlinedTextFieldJumlahUangInput.text.toString()
        val targetUang = binding.outlinedTextFieldTargetUangInput.text.toString()

        if(!TextUtils.isEmpty(jumlahUang) && !TextUtils.isEmpty(targetUang)){
            if(jumlahUang.toInt() >= targetUang.toInt()){
                Toast.makeText(context, R.string.invalid_nabung, Toast.LENGTH_LONG).show()
                binding.textViewHasil.text= ""
                return
            }else {
                viewModel.hitungTabungan(targetUang.toDouble(),jumlahUang.toDouble())
            }

        }else{
            Toast.makeText(context, R.string.invalid_input, Toast.LENGTH_SHORT).show()
            return
        }
    }


    private fun showResult(result:HasilHitung?){
        if (result == null) return
        binding.textViewHasil.text = getString(R.string.hasil, result.hasil)
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