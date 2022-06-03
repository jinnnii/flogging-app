package com.project.flogging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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



}