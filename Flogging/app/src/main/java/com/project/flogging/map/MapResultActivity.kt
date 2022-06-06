package com.project.flogging.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.flogging.R
import com.project.flogging.databinding.ActivityMapResultBinding

class MapResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityMapResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMapResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener {
            val intent= Intent(this, MapFeedbackActivity::class.java)
            this?.startActivity(intent)
        }
    }
}