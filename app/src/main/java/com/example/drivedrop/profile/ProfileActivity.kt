package com.example.drivedrop.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drivedrop.databinding.ActivityUserscreenBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}