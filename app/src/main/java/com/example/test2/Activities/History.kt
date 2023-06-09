package com.example.test2.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.adapters.HistoryAdapter
import com.example.test2.ModelClasses.HistoryDataClass.history
import com.example.test2.databinding.ActivityHistoryBinding

class History : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var hisAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        hisAdapter = HistoryAdapter(history)
        binding.recyclerView.adapter = hisAdapter



    }

}