package com.project.flogging.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.project.flogging.MainActivity
import com.project.flogging.R
import com.project.flogging.databinding.ActivityMapFeedbackBinding

class MapFeedbackActivity : AppCompatActivity() {
    lateinit var binding: ActivityMapFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMapFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items=resources.getStringArray(R.array.feedback_array)
        val myAdapter=ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinner.adapter=myAdapter

        binding.spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                when(position) {
                    0   ->  { }
                    1   ->  { }
                    2   ->  { }
                    3   ->  { }
                    4   ->  { }
                    else -> { }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        binding.submitBtn.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            this?.startActivity(intent)
        }
    }
}