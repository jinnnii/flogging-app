package com.project.flogging.map

import android.content.Intent
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.project.flogging.R
import com.project.flogging.databinding.FragmentHomeBinding
import com.project.flogging.databinding.FragmentMapsBinding

class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapsBinding.inflate(inflater, container, false)

        binding.startBtn.setOnClickListener {
            binding.startBtn.isVisible=false
            binding.controlBox.isVisible=true
        }

        binding.parseBtn.setOnClickListener {
            binding.parseBtn.visibility=View.GONE
            binding.parseTxt.isVisible=true
            binding.playBtn.isVisible=true
            binding.stopBtn.isVisible=true
        }
        binding.playBtn.setOnClickListener {
            binding.parseBtn.isVisible=true
            binding.parseTxt.isVisible=false
            binding.playBtn.visibility=View.GONE
            binding.stopBtn.visibility=View.GONE
        }
        binding.stopBtn.setOnClickListener {
            val intent= Intent(context, MapResultActivity::class.java)
            context?.startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}