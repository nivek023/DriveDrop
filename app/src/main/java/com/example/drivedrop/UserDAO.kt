package com.example.drivedrop

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.drivedrop.entities.Car
import com.example.drivedrop.entities.Chat
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Route
import com.example.drivedrop.entities.Tour
import com.example.drivedrop.entities.User
import com.example.drivedrop.entities.relations.CarWithTours
import com.example.drivedrop.entities.relations.ChatWithOwnerDriverAndTour
import com.example.drivedrop.entities.relations.DriverWithChats
import com.example.drivedrop.entities.relations.DriverWithRoutes
import com.example.drivedrop.entities.relations.DriverWithTours
import com.example.drivedrop.entities.relations.OwnerHasCars
import com.example.drivedrop.entities.relations.OwnerWithChats
import com.example.drivedrop.entities.relations.OwnerWithRoutes
import com.example.drivedrop.entities.relations.RouteWithDriverAndOwner
import com.example.drivedrop.entities.relations.TourWithRouteAndDriverAndCar
import kotlinx.coroutines.flow.Flow
//Data Access Object of all defined Database accesses. Upsert and delete for any entity,
// and for any relation
@Dao
interface UserDAO {
    //reset primarykey sequence
    @Query("DELETE FROM sqlite_sequence")
    fun clearPrimaryKeyIndex()

    //using suspend to enable coroutines and async
    @Upsert //Updating and inserting
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    //Using Observable to ensure updates on changes
    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getUserOrderedByFirstName(): Flow<List<User>>

    @Query("SELECT * FROM user ORDER BY email ASC")
    fun getUserOrderedByEmail(): Flow<List<User>>

    @Query("SELECT * FROM user ORDER BY userName ASC")
    fun getUserOrderedByUsername(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: String): Flow<User>

    // Owner operations
    @Upsert
    suspend fun upsertOwner(owner: Owner)

    @Delete
    suspend fun deleteOwner(owner: Owner)

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getOwnersWithRoutes(): Flow<List<OwnerWithRoutes>>

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getOwnerHasCars(): Flow<List<OwnerHasCars>>

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getOwnerWithChats(): Flow<List<OwnerWithChats>>

    // Driver operations
    @Upsert
    suspend fun upsertDriver(driver: Driver)

    @Delete
    suspend fun deleteDriver(driver: Driver)

    @Transaction
    @Query("SELECT * FROM Driver")
    fun getDriversWithRoutes(): Flow<List<DriverWithRoutes>>

    @Transaction
    @Query("SELECT * FROM Driver")
    fun getDriversWithTours(): Flow<List<DriverWithTours>>

    @Transaction
    @Query("SELECT * FROM Driver")
    fun getDriversWithChats(): Flow<List<DriverWithChats>>

    // Car operations
    @Upsert
    suspend fun upsertCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Transaction
    @Query("SELECT * FROM Car")
    fun getCarsWithTours(): Flow<List<CarWithTours>>

    // Route operations
    @Upsert
    suspend fun upsertRoute(route: Route)

    @Delete
    suspend fun deleteRoute(route: Route)

    @Transaction
    @Query("SELECT * FROM Route")
    fun getRoutesWithDriverAndOwner(): Flow<List<RouteWithDriverAndOwner>>

    // Tour operations
    @Upsert
    suspend fun upsertTour(tour: Tour)

    @Delete
    suspend fun deleteTour(tour: Tour)

    @Transaction
    @Query("SELECT * FROM Tour")
    fun getToursWithRouteAndDriverAndCar(): Flow<List<TourWithRouteAndDriverAndCar>>

    // Chat operations
    @Upsert
    suspend fun upsertChat(chat: Chat)

    @Delete
    suspend fun deleteChat(chat: Chat)

    @Transaction
    @Query("SELECT * FROM Chat")
    fun getChatsWithOwnerDriverAndTour(): Flow<List<ChatWithOwnerDriverAndTour>>
}