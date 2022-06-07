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


//        binding.historyToolbar.setOnClickListener {
//            onOptionsItemSelected()
//        }
    }
    // error--------------------------------------------------------------
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}

