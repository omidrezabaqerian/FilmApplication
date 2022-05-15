package ir.omidrezabagherian.filmapplication.data.model

data class FilmModel(
    val movie_id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Float,
    val movie_overview: String
)