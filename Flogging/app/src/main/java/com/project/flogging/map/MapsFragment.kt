package com.project.flogging.map

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.project.flogging.R
import com.project.flogging.databinding.FragmentMapsBinding
import com.project.flogging.model.FloggingUser
import com.project.flogging.model.Road
import kotlin.math.pow

class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    lateinit var fusedLocationClient:FusedLocationProviderClient
    lateinit var mapFragment: SupportMapFragment
    var pathPoly: PolylineOptions? = null
    var mMap: GoogleMap?=null
    var manager: LocationManager?= null

    // 위치 정보를 저장할 리스트
    lateinit var roadList:ArrayList<Road>

    // walk 객체에 저장할 데이터 초기화
    var initTime=0L
    var pauseTime = 0L
    var distance= 0.0
    var startTime=0L
    var endTime=0L

    val REQ_CODE:Int = 3000

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY,object:CancellationToken(){
            override fun onCanceledRequested(p0: OnTokenCanceledListener)=CancellationTokenSource().token
            override fun isCancellationRequested(): Boolean =false
        }).addOnSuccessListener {
            val lat = it.latitude
            val long = it.longitude
            val time = it.time
            roadList.add(Road(null, lat,long,time, null))
            pathPoly = PolylineOptions().add(LatLng(lat, long))
                .width(50F).color(Color.parseColor("#99B3B1")).geodesic(true)


            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(lat,long))
                .zoom(19F)
                .build()
            mMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            mMap!!.isMyLocationEnabled=true
        }
    }
    private val listener: LocationListener = object: LocationListener
    {
        override fun onLocationChanged(location: Location) {
            roadList.add(Road(null, location.latitude, location.longitude, location.time, null))
            // 경로 업데이트
            drawPath()

            // 이동거리 업데이트
            val size= roadList.size-1
            addDistance(
                roadList[size-1].latitude,
                roadList[size].latitude,
                roadList[size-1].longitude,
                roadList[size].longitude)


            Log.d("kej","${location.latitude},${location.longitude}, ${location.time}")
        }

    }


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapsBinding.inflate(inflater, container, false)
        roadList = ArrayList()

        binding.startBtn.setOnClickListener {
            binding.startBtn.isVisible=false
            binding.controlBox.isVisible=true

            //시간 측정
            initTime= SystemClock.elapsedRealtime()
            binding.time.base= SystemClock.elapsedRealtime()+pauseTime
            binding.time.start()

            //현재 시각 저장
            startTime = System.currentTimeMillis()

            // todo  최소 10초, 최소 10m 마다 위치 확인
            manager= activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            manager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 0f, listener)
        }

        binding.parseBtn.setOnClickListener {
            binding.parseBtn.visibility=View.GONE
            binding.parseTxt.isVisible=true
            binding.playBtn.isVisible=true
            binding.stopBtn.isVisible=true

            //중지
            pauseTime=binding.time.base-SystemClock.elapsedRealtime()
            binding.time.stop()
        }
        binding.playBtn.setOnClickListener {
            binding.time.base=SystemClock.elapsedRealtime()+pauseTime
            binding.time.start()

            binding.parseBtn.isVisible=true
            binding.parseTxt.isVisible=false
            binding.playBtn.visibility=View.GONE
            binding.stopBtn.visibility=View.GONE
        }
        binding.stopBtn.setOnClickListener {
            endTime=System.currentTimeMillis()
            val timeSec =  SystemClock.elapsedRealtime()-binding.time.base
            val userFlogging = FloggingUser(
                null, distance, endTime, startTime, timeSec, roadList
            )

            val intent= Intent(context, MapResultActivity::class.java)
            intent.putExtra("flogging", userFlogging)
            startActivityForResult(intent,3000)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(requireActivity())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

//    /**
//     * 지도에 마크 찍기
//     */
//    private fun drawMark(floggingList:List<FloggingUser>){
//        for (flogging in floggingList){
//            val lat = flogging.latitude
//            val lng = flogging.longitude
//            val pos = LatLng(lat,lng)
//            val markOptions=MarkerOptions()
//            markOptions.icon(bitmapDescriptorFromVector(context,R.drawable.ic_default_mark))
//            mMap?.addMarker(markOptions.position(pos))
//        }
//    }
    /**
     * 마커 아이콘 Bitmap으로 변경
     */
    private fun bitmapDescriptorFromVector(context: Context?, vectorResId: Int): BitmapDescriptor? {
        return context?.let {
            ContextCompat.getDrawable(it, vectorResId)?.run {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
                draw(Canvas(bitmap))
                BitmapDescriptorFactory.fromBitmap(bitmap)
            }
        }
    }
    /**
     *
     * 경로 그리기
     */
    private fun drawPath(){
        val size= roadList.size-1
        val lastLoc = LatLng(roadList[size].latitude, roadList[size].longitude)
        pathPoly?.add(lastLoc)
        mMap?.addPolyline(pathPoly!!)
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLoc, 19F))
    }

    /**
     *  두 위도 경도 사이의 거리 구하고 누적하기
     */
    private fun addDistance(lat1:Double, lat2:Double, lon1:Double, lon2:Double){
        val R = 6372.8*1000
        val dLat = Math.toRadians(lat2-lat1)
        val dLon = Math.toRadians(lon2-lon1)
        val a = Math.sin(dLat / 2)
            .pow(2.0)+ Math.sin(dLon / 2)
            .pow(2.0)* Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
        val c=2* Math.asin(Math.sqrt(a))
        val dist = String.format("%.2f",R*c*0.001).toDouble()

        distance+=dist
        binding.distance.text=(distance.toString())
    }

    fun init(){
        pauseTime=0L
        distance=0.0

        binding.time.base=SystemClock.elapsedRealtime()
        binding.time.stop()

        binding.startBtn.visibility=View.VISIBLE
        binding.controlBox.visibility=View.GONE
        binding.parseBtn.visibility=View.VISIBLE
//        binding.parseLayout.visibility=View.GONE
        binding.distance.text="0.0"

        manager?.removeUpdates(listener)
        if(mMap!=null) mMap?.clear()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        init()
    }

}