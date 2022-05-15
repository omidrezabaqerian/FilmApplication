package ir.omidrezabagherian.filmapplication.data.local

import androidx.room.Database
import ir.omidrezabagherian.filmapplication.domain.model.Result
import ir.omidrezabagherian.filmapplication.domain.model.ResultX

@Database(entities = [ResultX::class, Result::class], version = 1, exportSchema = false)
abstract class FilmDatabase {
    abstract fun filmDao(): FilmDao
}