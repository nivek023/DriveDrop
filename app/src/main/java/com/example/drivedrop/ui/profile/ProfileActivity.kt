package com.example.drivedrop.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.drivedrop.databinding.ActivityUserscreenBinding
import com.example.drivedrop.R
import com.example.drivedrop.ui.homepage.HomepageActivity
import com.example.drivedrop.UserDatabase
import com.example.drivedrop.entities.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserscreenBinding
    private lateinit var submitButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var driverLicenseInfoEditText: EditText
    private lateinit var bioEditText: EditText

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get references to EditText fields
        nameEditText = findViewById(R.id.name_edit_text)
        usernameEditText = findViewById(R.id.username_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        driverLicenseInfoEditText = findViewById(R.id.driver_license_info_edit_text)
        bioEditText = findViewById(R.id.bio_edit_text)

        submitButton = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            val nameParts = nameEditText.text.toString().split(" ")
            val firstName = nameParts.getOrNull(0) ?: ""
            val lastName = nameParts.getOrNull(1) ?: ""

            val user = User(
                firstName = firstName,
                lastName = lastName,
                userName = usernameEditText.text.toString(),
                email = emailEditText.text.toString(),
                password = passwordEditText.text.toString(),
                paymentInfo = driverLicenseInfoEditText.text.toString(),
                bio = bioEditText.text.toString()
            )

            // Insert user into database
            GlobalScope.launch(Dispatchers.IO) {
                UserDatabase.getInstance(this@ProfileActivity).dao.upsertUser(user)
            }

            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }
    }
}