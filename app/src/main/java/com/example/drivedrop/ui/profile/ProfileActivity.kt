package com.example.drivedrop.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.drivedrop.databinding.ActivityUserscreenBinding
import com.example.drivedrop.R
import com.example.drivedrop.ui.homepage.HomepageActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserscreenBinding
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        submitButton = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }
    }
}