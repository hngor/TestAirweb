package fr.airweb.news.data.dao

import androidx.room.RoomDatabase

abstract class TestAirwebDB: RoomDatabase() {

    abstract fun newsDao(): NewsDao
}