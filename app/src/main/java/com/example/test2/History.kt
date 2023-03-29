package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.HistoryDataClass.history
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