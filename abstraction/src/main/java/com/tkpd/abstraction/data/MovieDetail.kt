package com.tkpd.abstraction.data


import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any = Any(),
    @SerializedName("budget")
    val budget: Float = 0F,
    @SerializedName("genres")
    val genres: List<Genre> = listOf(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("revenue")
    val revenue: Long = 0L,
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0F,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)

data class Genre(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)

data class ProductionCompany(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: Any = Any(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = ""
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String = "",
    @SerializedName("name")
    val name: String = ""
)

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String = "",
    @SerializedName("name")
    val name: String = ""
)
