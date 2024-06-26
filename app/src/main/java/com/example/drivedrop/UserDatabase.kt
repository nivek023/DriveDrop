package com.example.drivedrop

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drivedrop.entities.Car
import com.example.drivedrop.entities.Chat
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Route
import com.example.drivedrop.entities.Tour
import com.example.drivedrop.entities.User

@Database(
    entities = [
        User::class,
        Car::class,
        Chat::class,
        Driver::class,
        Owner::class,
        Route::class,
        Tour::class,
               ],
    version = 1
)
@TypeConverters(Converter::class)
abstract class UserDatabase: RoomDatabase() {

    abstract val dao : UserDAO

    //prevent race conditions
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}