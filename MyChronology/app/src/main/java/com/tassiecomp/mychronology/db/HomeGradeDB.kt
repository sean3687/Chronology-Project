package com.tassiecomp.mychronology.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tassiecomp.mychronology.models.HomeGrade


@Database(
    entities = [HomeGrade::class], version = 1
)

abstract class HomeGradeDB: RoomDatabase() {
    abstract fun getGradeDao():GradeDao

    companion object{
        @Volatile
        private var INSTANCE: HomeGradeDB? = null

        fun getDatabase(context: Context): HomeGradeDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HomeGradeDB::class.java,
                    "HomeGrade_db.db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}
