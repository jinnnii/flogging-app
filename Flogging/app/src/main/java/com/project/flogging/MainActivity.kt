package com.project.flogging

import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.PolylineOptions
import com.project.flogging.databinding.ActivityMainBinding
import com.project.flogging.home.HomeFragment
import com.project.flogging.map.MapsFragment
import com.project.flogging.mypage.MypageFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    fun changeFragment(frag: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, frag)
            .commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        /**
         * 위치 권한 확인
         */
        if ( ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED){
        }else{
            permissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }


        binding.bottomNav.run {


            setOnItemSelectedListener { item->
                when(item.itemId){
                    R.id.action_flogging->{
                        changeFragment(MapsFragment())
                    }
                    R.id.action_home->{
                        changeFragment(HomeFragment())
                    }
                    R.id.action_my_page->{
                        changeFragment(MypageFragment())

                    }


                }
                true
            }
            selectedItemId=R.id.action_home
        }
    }

    /**
     *  위치 권한 승락/거부 시 동작
     */
    val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if(it){
            Log.d("pet","access...")
        }else{
            Toast.makeText(this,
                "권한 설정이 거부되었습니다.\n앱을 사용하시려면 다시 실행해주세요.",
                Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}