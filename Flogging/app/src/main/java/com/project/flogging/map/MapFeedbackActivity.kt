package com.project.flogging.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.project.flogging.MainActivity
import com.project.flogging.R
import com.project.flogging.SpringApplication
import com.project.flogging.databinding.ActivityMapFeedbackBinding
import com.project.flogging.model.Feedback
import com.project.flogging.model.MessageModel
import com.project.flogging.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapFeedbackActivity : AppCompatActivity() {
    lateinit var binding: ActivityMapFeedbackBinding
    lateinit var detail:String
    var feedbackLevel:Long=0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMapFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items=resources.getStringArray(R.array.feedback_array)
        val myAdapter=ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinner.adapter=myAdapter

        binding.spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                feedbackLevel=when(position) {
                    0   ->  { 0L}
                    1   ->  { 1L}
                    2   ->  { 2L}
                    3   ->  { 3L}
                    4   ->  { 4L}
                    else -> { 0L}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        binding.submitBtn.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            this?.startActivity(intent)
            val detail:String = binding.detail.text.toString()
            val feedback = Feedback(null,feedbackLevel,detail, null)
            val networkService = (applicationContext as SpringApplication).networkService
            val testCall = networkService.insertFeedback("test", feedback)

            testCall.enqueue(object : Callback<MessageModel> {
                override fun onResponse(
                    call: Call<MessageModel>,
                    response: Response<MessageModel>
                ) {
                    //
                }

                override fun onFailure(call: Call<MessageModel>, t: Throwable) {
                    //
                }


            })
        }
    }
}