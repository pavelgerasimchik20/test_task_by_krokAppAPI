package com.geras.krok.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.geras.krok.KrokApp
import com.geras.krok.databinding.FragmentCityBinding
import com.geras.krok.ui.CityAdapter
import com.geras.krok.ui.CityViewModel
import com.geras.krok.ui.MainActivity
import com.geras.krok.ui.ViewModelFactory

class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CityViewModel by viewModels {
        ViewModelFactory((requireActivity().application as KrokApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CityAdapter {
            // TODO
            val activity = activity as? MainActivity
            activity?.openPointsFragment(it)
        }
        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.adapter = adapter

        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            adapter.updateCities(cities)
        }
    }

    companion object {

        fun newInstance(): CityFragment {
            val fragment = CityFragment()
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
