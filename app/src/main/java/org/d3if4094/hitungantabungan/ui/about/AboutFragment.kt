package org.d3if4094.hitungantabungan.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if4094.hitungantabungan.R
import org.d3if4094.hitungantabungan.databinding.FragmentAboutBinding
import org.d3if4094.hitungantabungan.model.TentangAplikasi
import org.d3if4094.hitungantabungan.network.ApiStatus

class AboutFragment : Fragment(){
    private val viewModel: AboutViewModel by lazy {
        val factory = AboutViewModelFactory(requireActivity().application)
        ViewModelProvider(this,factory)[AboutViewModel::class.java]
    }

    private lateinit var binding : FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStatus().observe(viewLifecycleOwner){
            updateProgress(it)
        }
        viewModel.getTentangAplikasi().observe(viewLifecycleOwner){
            tentangAplikasi(it)
        }
        viewModel.getImage(binding.imageView,R.drawable.broken_image)
    }

    private fun tentangAplikasi(tentangAplikasi: TentangAplikasi?) {
        if (tentangAplikasi != null) {
            binding.aboutAplikasi.text = tentangAplikasi.tentang
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}