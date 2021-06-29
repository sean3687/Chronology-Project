package com.tassiecomp.mychronology.db

import java.util.concurrent.locks.Lock

abstract class HomeGradeDB {
    abstract fun getGradeDao():GradeDao

    companion object{
        @Volatile
        private var instance: HomeGradeDB?= null
        private val LOCK = Any()

        open fun invoke(context:Context) = instance?: synchronized(LOCK){
            instance?: createDatabase
        }
    }
}