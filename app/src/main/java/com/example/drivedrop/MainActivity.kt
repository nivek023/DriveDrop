package com.example.drivedrop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.drivedrop.databinding.ActivityLoginBinding
import com.example.drivedrop.databinding.ActivityRegisterBinding
import com.example.drivedrop.entities.*
import com.example.drivedrop.login.LoginActivity
import com.example.drivedrop.ui.profile.ProfileActivity
import com.example.drivedrop.ui.theme.DriveDropAndroidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//Mainactivity solves the start of the App and defines entry page. Builds up database
@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var SignupButton: Button
    private lateinit var emailInput: EditText

    //builds database
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user_db"
        ).build()
    }
    //reset database - for demo reasons the database fills at the start some data.
    //to prevent it to get bigger with each test, cleaning
    private suspend fun resetDb() = withContext(Dispatchers.IO){
        db.runInTransaction{
            runBlocking {
                db.clearAllTables()
                db.dao.clearPrimaryKeyIndex()
            }
        }
    }

    //no use of dagger -> so need for factory
    private val viewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(db.dao) as T
                }
            }
        }
    )
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_btn)


        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {

                if (username == "admin" && password == "admin") {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //creating data for database
        val dao = UserDatabase.getInstance(this).dao
        val users = listOf(
            User(
                firstName = "Alice",
                lastName = "Johnson",
                email = "alice.johnson@example.com",
                password = "password123",
                userName = "alicej",
                paymentInfo = "VISA **** 1234",
                bio = "Avid reader and hiker."
            ),
            User(
                firstName = "Bob",
                lastName = "Smith",
                email = "bob.smith@example.com",
                password = "password123",
                userName = "smithbob",
                paymentInfo = "MasterCard **** 5678",
                bio = "Chef and cycling enthusiast."
            ),
            User(
                firstName = "Charlie",
                lastName = "Brown",
                email = "charlie.brown@example.com",
                password = "password123",
                userName = "whatisauser",
                paymentInfo = "AMEX **** 9012",
                bio = "Loves traveling and photography."
            ),
            User(
                firstName = "Diana",
                lastName = "Prince",
                email = "diana.prince@example.com",
                password = "password123",
                userName = "dianap",
                paymentInfo = "VISA **** 3456",
                bio = "Writer and yoga practitioner."
            ),
            User(
                firstName = "Evan",
                lastName = "Davis",
                email = "evan.davis@example.com",
                password = "password123",
                userName = "evand",
                paymentInfo = "PayPal evan.davis@example.com",
                bio = "Musician and runner."
            )
        )
        val drivers = listOf(
            Driver(
                id = 1,
                currentTour = "Tour A",
                rating = "4.5",
                favorites = listOf("Favorite 1", "Favorite 2"),
                driverLicence = "DL123456"
            ),
            Driver(
                id = 2,
                currentTour = "Tour B",
                rating = "4.8",
                favorites = listOf("Favorite 3", "Favorite 4"),
                driverLicence = "DL654321"
            ),
            Driver(
                id = 3,
                currentTour = "Tour C",
                rating = "4.7",
                favorites = listOf("Favorite 5", "Favorite 6"),
                driverLicence = "DL112233"
            ),
            Driver(
                id = 4,
                currentTour = "Tour D",
                rating = "4.9",
                favorites = listOf("Favorite 7", "Favorite 8"),
                driverLicence = "DL334455"
            ),
            Driver(
                id = 5,
                currentTour = "Tour E",
                rating = "4.6",
                favorites = listOf("Favorite 9", "Favorite 10"),
                driverLicence = "DL556677"
            )
        )
        val owners = listOf(
            Owner(id = 1, costCenter = 1001),
            Owner(id = 2, costCenter = 1002),
            Owner(id = 3, costCenter = 1003),
            Owner(id = 4, costCenter = 1004),
            Owner(id = 5, costCenter = 1005)
        )
        val routes = listOf(
            Route(
                driverId = 1,
                ownerId = 1,
                checkpointsLocation = listOf("Location A1", "Location A2"),
                checkpointsTime = listOf("08:00 AM", "09:00 AM"),
                checkpointsPicture = listOf("pic1.jpg", "pic2.jpg")
            ),
            Route(
                driverId = 2,
                ownerId = 2,
                checkpointsLocation = listOf("Location B1", "Location B2"),
                checkpointsTime = listOf("09:00 AM", "10:00 AM"),
                checkpointsPicture = listOf("pic3.jpg", "pic4.jpg")
            ),
            Route(
                driverId = 3,
                ownerId = 3,
                checkpointsLocation = listOf("Location C1", "Location C2"),
                checkpointsTime = listOf("10:00 AM", "11:00 AM"),
                checkpointsPicture = listOf("pic5.jpg", "pic6.jpg")
            ),
            Route(
                driverId = 4,
                ownerId = 4,
                checkpointsLocation = listOf("Location D1", "Location D2"),
                checkpointsTime = listOf("11:00 AM", "12:00 PM"),
                checkpointsPicture = listOf("pic7.jpg", "pic8.jpg")
            ),
            Route(
                driverId = 5,
                ownerId = 5,
                checkpointsLocation = listOf("Location E1", "Location E2"),
                checkpointsTime = listOf("12:00 PM", "01:00 PM"),
                checkpointsPicture = listOf("pic9.jpg", "pic10.jpg")
            )
        )
        val tours = listOf(
            Tour(
                carId = 1,
                driverId = 1,
                routeId = 1,
                tip = 20,
                fuelType = "Petrol"
            ),
            Tour(
                carId = 2,
                driverId = 2,
                routeId = 2,
                tip = 25,
                fuelType = "Diesel"
            ),
            Tour(
                carId = 3,
                driverId = 3,
                routeId = 3,
                tip = 30,
                fuelType = "Electric"
            ),
            Tour(
                carId = 4,
                driverId = 4,
                routeId = 4,
                tip = 15,
                fuelType = "Petrol"
            ),
            Tour(
                carId = 5,
                driverId = 5,
                routeId = 5,
                tip = 10,
                fuelType = "Diesel"
            )
        )
        val chats = listOf(
            Chat(
                ownerId = 1,
                driverId = 1,
                tourId = 1,
                messages = listOf("Hello", "How are you?", "Let's start the tour.")
            ),
            Chat(
                ownerId = 2,
                driverId = 2,
                tourId = 2,
                messages = listOf("Hi", "Ready for the tour?", "Let's go!")
            ),
            Chat(
                ownerId = 3,
                driverId = 3,
                tourId = 3,
                messages = listOf("Good morning", "All set?", "On our way.")
            ),
            Chat(
                ownerId = 4,
                driverId = 4,
                tourId = 4,
                messages = listOf("Hey", "Let's move", "On the road now.")
            ),
            Chat(
                ownerId = 5,
                driverId = 5,
                tourId = 5,
                messages = listOf("Hello", "Tour time!", "See you soon.")
            )
        )
        val cars = listOf(
            Car(
                carId = "CAR123",
                ownerId = 1,
                time = "08:00 AM",
                startLocation = "Location A",
                destination = "Location B",
                carDetails = "Sedan, 2018, Red",
                pictures = "car1.jpg",
                ratings = "4.7"
            ),
            Car(
                carId = "CAR456",
                ownerId = 2,
                time = "09:00 AM",
                startLocation = "Location C",
                destination = "Location D",
                carDetails = "SUV, 2020, Blue",
                pictures = "car2.jpg",
                ratings = "4.8"
            ),
            Car(
                carId = "CAR789",
                ownerId = 3,
                time = "10:00 AM",
                startLocation = "Location E",
                destination = "Location F",
                carDetails = "Hatchback, 2019, Green",
                pictures = "car3.jpg",
                ratings = "4.6"
            ),
            Car(
                carId = "CAR101",
                ownerId = 4,
                time = "11:00 AM",
                startLocation = "Location G",
                destination = "Location H",
                carDetails = "Truck, 2017, Black",
                pictures = "car4.jpg",
                ratings = "4.5"
            ),
            Car(
                carId = "CAR112",
                ownerId = 5,
                time = "12:00 PM",
                startLocation = "Location I",
                destination = "Location J",
                carDetails = "Convertible, 2021, Yellow",
                pictures = "car5.jpg",
                ratings = "4.7"
            )
        )

        //prefill database after clean up
        lifecycleScope.launch{
            resetDb()
            users.forEach{dao.upsertUser(it)}
            drivers.forEach{dao.upsertDriver(it)}
            owners.forEach{dao.upsertOwner(it)}
            cars.forEach{dao.upsertCar(it)}
            routes.forEach { dao.upsertRoute(it) }
            tours.forEach {dao.upsertTour(it)}
            chats.forEach { dao.upsertChat(it) }
        }

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SignupButton = findViewById(R.id.signup_button)
        emailInput = findViewById(R.id.email_input)
        SignupButton.setOnClickListener {
            val email = emailInput.text.toString()
            if (email.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.apply()

                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show()
            }
        }
    }
}