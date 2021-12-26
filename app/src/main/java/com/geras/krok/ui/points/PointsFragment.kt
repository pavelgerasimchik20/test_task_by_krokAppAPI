package com.geras.krok.ui.points

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geras.krok.KrokApp
import com.geras.krok.databinding.FragmentPointBinding
import com.geras.krok.domain.model.City
import com.geras.krok.ui.MainActivity
import com.geras.krok.ui.PointAdapter
import com.geras.krok.ui.ViewModelFactory

class PointsFragment : Fragment() {

    private var _binding: FragmentPointBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PointsViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).application as KrokApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(CITY_LOGO) ?: ""
        val name = arguments?.getString(CITY_NAME) ?: ""
        val id = arguments?.getInt(CITY_ID) ?: 0

        val adapter = PointAdapter {
            val activity = activity as? MainActivity
            activity?.openDetailsFragment(it,url,name)
        }
        binding.recyclerview.adapter = adapter
        binding.ivCityFromToolbar.load(url)
        binding.tvCityFromToolbar.text = name
        binding.backArrow.setOnClickListener {
            val activity = activity as? MainActivity
            activity?.onBackPressed()
        }
        viewModel.initWithId(id)
        viewModel.pointListDto.observe(viewLifecycleOwner){
            adapter.updatePoints(it)
        }
    }

    companion object {

        private const val CITY_NAME = "CITY_NAME"
        private const val CITY_LOGO = "CITY_LOGO"
        private const val CITY_ID = "CITY_ID"

        fun newInstance(city: City): PointsFragment {
            val fragment = PointsFragment()
            val args = Bundle()
            args.putString(CITY_LOGO, city.logo)
            args.putString(CITY_NAME, city.name)
            args.putInt(CITY_ID, city.id!!)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
