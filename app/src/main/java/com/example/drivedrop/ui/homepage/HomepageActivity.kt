package com.example.drivedrop.ui.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drivedrop.R
import com.example.drivedrop.databinding.ActivityHomepageBinding
//builds up homepageactivity page. Currently only Design without any functionality.
//starting point for future implementations
class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}