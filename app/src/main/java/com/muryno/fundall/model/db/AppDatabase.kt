package com.muryno.fintech.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muryno.fundall.model.db.entity.User
import com.muryno.fundall.utils.MainApplication

@Database(entities = [User::class], version = 0, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {



    companion object{
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(MainApplication.getInstance().applicationContext, AppDatabase::class.java, "fundall").fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}