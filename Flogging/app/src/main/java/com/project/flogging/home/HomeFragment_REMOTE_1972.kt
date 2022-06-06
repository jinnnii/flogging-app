package com.project.flogging.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.flogging.SpringApplication
import com.project.flogging.databinding.FragmentHomeBinding
import com.project.flogging.model.MessageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        val networkService = (activity?.applicationContext as SpringApplication).networkService
        val testCall = networkService.doTest()

        testCall.enqueue(object : Callback<MessageModel> {
            override fun onResponse(call: Call<MessageModel>, response: Response<MessageModel>) {
                binding.testTV.setText(response.body()?.test)
            }

            override fun onFailure(call: Call<MessageModel>, t: Throwable) {
                call.cancel()
            }


        })

        return binding.root
    }
}