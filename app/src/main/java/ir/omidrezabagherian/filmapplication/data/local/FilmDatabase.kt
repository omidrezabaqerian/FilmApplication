package ir.omidrezabagherian.filmapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.omidrezabagherian.filmapplication.domain.model.Result
import ir.omidrezabagherian.filmapplication.domain.model.ResultX

@Database(entities = [ResultX::class, Result::class], version = 2, exportSchema = true)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}