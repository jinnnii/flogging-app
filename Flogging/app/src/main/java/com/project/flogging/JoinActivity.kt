package com.project.flogging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.flogging.databinding.ActivityJoinBinding
import com.project.flogging.map.MapResultActivity

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            this?.startActivity(intent)
        }
    }
}