package com.example.feelings

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =arrayOf(Feeling::class), version=1)
public abstract class FeelingDatabase : RoomDatabase() {

    //create an instance of DAO
    abstract fun feelingDAO(): FeelingDAO

    companion object{

        //make sure only one instance of the database is created
        @Volatile
        private var INSTANCE : FeelingDatabase? = null


        //function to obtain/create the database
        fun getDatabase(context: Context):FeelingDatabase{
            var tempDB = INSTANCE
            if(tempDB != null){
                return tempDB
            }
            
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeelingDatabase::class.java,
                    "feeling_db"
                ).build()

                INSTANCE = instance
                return instance
            }


        }
    }
}