package com.project.flogging.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.project.flogging.R
import com.project.flogging.databinding.ActivityHomeRecruitmentBinding

class HomeRecruitmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeRecruitmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeRecruitmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스피너 기능
        val items = resources.getStringArray(R.array.region_array)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        binding.regionSpinner.adapter = myAdapter

        binding.regionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when (position) {
                    0 -> {}
                    1 -> {}
                    2 -> {}
                    else -> {}
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //툴바 조작부분
        var myToolbar = binding.recruitToolbar
        //툴바생성
        setSupportActionBar(myToolbar)
        //툴바 기존 제목 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //툴바 뒤로가기버튼 생성
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        //툴바 뒤로가기버튼 작동코드 필요

    }
