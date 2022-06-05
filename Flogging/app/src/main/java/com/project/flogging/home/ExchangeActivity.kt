package com.project.flogging.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.project.flogging.MainActivity
import com.project.flogging.R
import com.project.flogging.databinding.ActivityExchangeBinding
import com.project.flogging.databinding.ActivityPointListBinding
import com.project.flogging.map.MapResultActivity
import java.text.DecimalFormat

class ExchangeActivity : AppCompatActivity() {
    lateinit var binding: ActivityExchangeBinding
    lateinit var btnArray:Array<Button> //버튼 배열 생성
    var resultStr=""
    val decimal = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myPoint= 1500
        val exPoint=decimal.format(myPoint)
        binding.myPoint.text=getString(R.string.my_point,exPoint)

        btnArray= arrayOf(binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5,
            binding.btn6, binding.btn7, binding.btn8, binding.btn9, binding.btn10, binding.btn11)

        for(i in 0..10) {
            btnArray[i].setOnClickListener {
                resultStr+=btnArray[i].text
                binding.resultPoint.text = resultStr
            }
        }
        // 숫자 지우기
        binding.btnDel.setOnClickListener {
            val lastIdx=resultStr.length-1
            if(resultStr.length>1) {
                Log.d("lastIdx", "$lastIdx")
                resultStr=resultStr.dropLast(1)
                Log.d("resultStr", "$resultStr")
                binding.resultPoint.text = resultStr
            }else{
                binding.resultPoint.text = "0"
            }
        }
        // 뒤로가기 버튼 클릭
        binding.backBtn.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            this?.startActivity(intent)
        }
        // 확인 버튼 클릭
        binding.button.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            this?.startActivity(intent)
        }

    }
}