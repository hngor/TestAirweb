package fr.airweb.news.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.airweb.news.data.model.ArticleEntity
import fr.airweb.news.util.Constants

@Database(entities = [ArticleEntity::class], version = Constants.DB_VERSION, exportSchema = true)
abstract class TestAirwebDB: RoomDatabase() {

    abstract fun newsDao(): NewsDao
}