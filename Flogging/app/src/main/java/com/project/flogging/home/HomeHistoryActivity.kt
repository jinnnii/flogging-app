package com.project.flogging.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.project.flogging.databinding.ActivityHomeHistoryBinding

class HomeHistoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //툴바 조작부분
        var myToolbar = binding.historyToolbar
        //툴바생성
        setSupportActionBar(myToolbar)
        //툴바 기존 제목 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //툴바 뒤로가기버튼 생성
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        // error--------------------------------------------------------------
        fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                android.R.id.home -> {
                    //toolbar의 back키 눌렀을 때 동작
                    finish()
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
            // 툴바의 뒤로가기버튼이 작동하기위한 코드인거같은데 구글링상에 java로 나와있어서
            // kotlin으로 변환시 이렇게 나오나 여전히 작동하지않음...
            // https://dreamaz.tistory.com/109
        }

//        binding.historyToolbar.setOnClickListener {
//            onOptionsItemSelected()
//        }
    }
}

