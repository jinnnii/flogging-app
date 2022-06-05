package com.project.flogging.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.flogging.R
import com.project.flogging.databinding.ActivityExchangeBinding
import com.project.flogging.databinding.ActivityPointListBinding
import com.project.flogging.databinding.ItemPointListBinding

class PointListActivity : AppCompatActivity() {
    lateinit var binding: ActivityPointListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPointListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this@PointListActivity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerviewPL.layoutManager=layoutManager
        binding.recyclerviewPL.adapter= RecyclerViewAdapter()
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<PointListActivity.RecyclerViewAdapter.ProductImgViewHolder>() {

        inner class ProductImgViewHolder(val binding: ItemPointListBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImgViewHolder =
            ProductImgViewHolder(ItemPointListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        override fun onBindViewHolder(holder: PointListActivity.RecyclerViewAdapter.ProductImgViewHolder, position: Int) {
            holder.binding.point.text="1000"
            holder.binding.date.text="2022.06.01"
        }

        override fun getItemCount(): Int {
            return 0
        }
    }
}