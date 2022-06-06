package com.project.flogging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.project.flogging.databinding.ActivityVolunteerDetailBinding

class VolunteerDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding:ActivityVolunteerDetailBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVolunteerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // 참여하기 버튼 클릭
        binding.button.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            this?.startActivity(intent)
        }
        // 뒤로가기 버튼 클릭
        binding.backBtn.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            this?.startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map=googleMap
        val locTitle="화성시자원봉사센타"
        val locLat=37.1255842
        val locLong=126.9180049

        val location = LatLng(locLat, locLong) // 화성시자원봉사센타: 37.1255842,126.9180049
        googleMap.addMarker(MarkerOptions().position(location).title(locTitle))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}