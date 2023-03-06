package com.example.a18_01_2023_notificationsdemo

import android.app.Activity
import android.os.Bundle
import com.example.a18_01_2023_notificationsdemo.databinding.ActivitySecondBinding

class SecondActivity : Activity(){
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}