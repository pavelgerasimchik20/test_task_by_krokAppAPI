package com.geras.krok.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.geras.krok.data.model.CityDto
import com.geras.krok.databinding.FragmentDetailsBinding
import com.geras.krok.domain.model.PointOfInterest
import com.geras.krok.ui.MainActivity

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photo = arguments?.getString(POINT_PHOTO) ?: ""
        val name = arguments?.getString(POINT_NAME) ?: ""
        val text = arguments?.getString(POINT_TEXT) ?: ""
        val cityName = arguments?.getString(CITY_NAME) ?: ""
        val cityPhoto = arguments?.getString(CITY_PHOTO) ?: ""

        binding.description.text = text
        binding.photoDetails.load(photo)
        binding.titleDetails.text = name
        binding.ivCityFromToolbar.load(cityPhoto)
        binding.tvCityFromToolbar.text = cityName
        binding.backArrow.setOnClickListener {
            val activity = activity as? MainActivity
            activity?.onBackPressed()
        }
    }

    companion object {

        private const val POINT_NAME = "POINT_NAME"
        private const val POINT_PHOTO = "POINT_PHOTO"
        private const val POINT_TEXT = "POINT_TEXT"
        private const val CITY_PHOTO = "CITY_PHOTO"
        private const val CITY_NAME = "CITY_NAME"

        fun newInstance(point: PointOfInterest, url: String, name: String): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putString(POINT_NAME, point.name)
            args.putString(POINT_PHOTO, point.photo)
            args.putString(POINT_TEXT, point.text)
            args.putString(CITY_PHOTO, url)
            args.putString(CITY_NAME, name)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
