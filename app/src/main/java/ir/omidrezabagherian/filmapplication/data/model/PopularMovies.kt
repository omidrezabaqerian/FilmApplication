package ir.omidrezabagherian.filmapplication.data.model

data class PopularMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)