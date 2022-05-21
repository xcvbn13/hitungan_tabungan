package org.d3if4094.hitungantabungan.ui.histori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if4094.hitungantabungan.databinding.FragmentHistoriBinding
import org.d3if4094.hitungantabungan.db.HistoriDb

class HistoriFragment: Fragment() {
    private val viewModel: HistoriViewModel by lazy {
        val db = HistoriDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoriBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.data.observe(viewLifecycleOwner) {
            Log.d("HistoriFragment", "Jumlah data: ${it.size}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoriBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }


}