package org.d3if4094.hitungantabungan.ui.histori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if4094.hitungantabungan.databinding.FragmentHistoriBinding
import org.d3if4094.hitungantabungan.db.HistoriDb

class HistoriFragment: Fragment() {
    private val viewModel: HistoriViewModel by lazy {
        val db = HistoriDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }
    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()

        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.data.observe(viewLifecycleOwner) {
            binding.emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            myAdapter.submitList(it)
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