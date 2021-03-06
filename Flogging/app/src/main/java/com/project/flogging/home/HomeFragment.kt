package com.project.flogging.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.flogging.SpringApplication
import com.project.flogging.databinding.FragmentHomeBinding
import com.project.flogging.map.MapResultActivity
import com.project.flogging.model.MessageModel
import com.project.flogging.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var user:User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater, container, false)

        // 환전하기 클릭
        binding.exchangeBtn.setOnClickListener {
            val intent= Intent(context, ExchangeActivity::class.java)
            context?.startActivity(intent)
        }
        // 적립내역 클릭
        binding.pointListBtn.setOnClickListener {
            val intent= Intent(context, PointListActivity::class.java)
            context?.startActivity(intent)
        }

        // 활동기록 클릭
        binding.myRecord.setOnClickListener {
            val intent= Intent(context, HomeHistoryActivity::class.java)
            context?.startActivity(intent)
        }
        // 모집활동 클릭
        binding.activityList.setOnClickListener {
            val intent= Intent(context, HomeRecruitmentActivity::class.java)
            context?.startActivity(intent)
        }
        val networkService = (activity?.applicationContext as SpringApplication).networkService
        val testCall = networkService.findUser("test")

        testCall.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                user= response.body()!!
                binding.point.text= user.point.toString()
                Log.d("pj", "success::::${response.body()?.toString()!!}")
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("pj","failed:::${call}")
            }


        })

        return binding.root
    }
}