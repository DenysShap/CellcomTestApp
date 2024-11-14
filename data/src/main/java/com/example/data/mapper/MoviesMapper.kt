package com.example.data.mapper

import com.example.data.converter.DateTimeConverter
import com.example.data.source.remote.movies.model.details.BelongsToCollectionResponse
import com.example.data.source.remote.movies.model.details.CastResponse
import com.example.data.source.remote.movies.model.details.CreditsResponse
import com.example.data.source.remote.movies.model.details.GenresResponse
import com.example.data.source.remote.movies.model.details.MovieDetailsResponse
import com.example.data.source.remote.movies.model.details.ProductionCompaniesResponse
import com.example.data.source.remote.movies.model.details.ProductionCountriesResponse
import com.example.data.source.remote.movies.model.details.SpokenLanguagesResponse
import com.example.data.source.remote.movies.model.movies.MovieResponse
import com.example.data.source.remote.movies.model.movies.NowPlayingMovieResponse
import com.example.data.source.remote.movies.model.movies.PopularMovieResponse
import com.example.data.source.remote.movies.model.movies.TopRatedMoviesResponse
import com.example.data.source.remote.movies.model.movies.UpcomingMovieResponse
import com.example.data.source.remote.movies.model.trailer.ResultsItemResponse
import com.example.data.source.remote.movies.model.trailer.TrailerVideoResponse
import com.example.data.util.Constants.EMPTY_STRING
import com.example.domain.model.details.BelongsToCollection
import com.example.domain.model.details.Cast
import com.example.domain.model.details.Credits
import com.example.domain.model.details.Genres
import com.example.domain.model.details.MovieDetails
import com.example.domain.model.details.ProductionCompanies
import com.example.domain.model.details.ProductionCountries
import com.example.domain.model.details.SpokenLanguages
import com.example.domain.model.movies.MovieItem
import com.example.domain.model.trailer.ResultsItem
import com.example.domain.model.trailer.TrailerVideo
import java.util.Locale

object MoviesMapper {

    internal fun NowPlayingMovieResponse.toNowPlayingMovieList(): List<MovieItem> =
        results.map { it.toMovieItem() }

    internal fun UpcomingMovieResponse.toUpcomingMovieList(): List<MovieItem> =
        results.map { it.toMovieItem() }

    internal fun PopularMovieResponse.toPopularMovieList(): List<MovieItem> =
        results.map { it.toMovieItem() }

    internal fun TopRatedMoviesResponse.toTopRatedMovieList(): List<MovieItem> =
        results.map { it.toMovieItem() }

    private fun MovieResponse.toMovieItem(): MovieItem =
        MovieItem(
            backdropPath = backdropPath,
            id = id,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage
        )

    internal fun MovieDetailsResponse.toMovieDetails(): MovieDetails =
        MovieDetails(
            originalLanguage = originalLanguage,
            imdbId = imdbId,
            video = video,
            title = title ?: EMPTY_STRING,
            backdropPath = backdropPath,
            genres = genres?.map { it.toGenres() },
            revenue = revenue,
            popularity = popularity,
            productionCountries = productionCountries?.map { it.toProductionCountries() },
            voteCount = voteCount,
            id = id,
            budget = budget,
            overview = overview ?: EMPTY_STRING,
            originalTitle = originalTitle,
            runtime = runtime,
            posterPath = posterPath,
            spokenLanguages = spokenLanguages?.map { it.toSpokenLanguages() },
            productionCompanies = productionCompanies?.map { it?.toProductionCompanies() },
            releaseDate = provideReleaseDate(releaseDate),
            voteAverage = voteAverage ?: 0.0,
            belongsToCollection = belongsToCollection?.toBelongsToCollection(),
            tagline = tagline,
            adult = adult,
            homepage = homepage,
            status = status,
            shortInfoAboutFilm = provideShortInfoAboutFilm(releaseDate, originalLanguage, runtime),
        )

    private fun provideReleaseDate(releaseDate: String?) =
        releaseDate.let { DateTimeConverter.formattedYear(releaseDate) }
            ?: EMPTY_STRING

    private fun provideShortInfoAboutFilm(
        releaseDate: String?,
        originalLanguage: String?,
        runtimeFilm: Int?,
    ) =
        provideReleaseDate(releaseDate) +
                "(${originalLanguage?.uppercase(Locale.ROOT)})" +
                " " + runtimeFilm?.let { DateTimeConverter.minuteToTime(it) }

    private fun BelongsToCollectionResponse.toBelongsToCollection(): BelongsToCollection =
        BelongsToCollection(
            backdropPath = backdropPath,
            name = name,
            id = id,
            posterPath = posterPath
        )

    private fun GenresResponse.toGenres(): Genres =
        Genres(name = name, id = id)

    private fun ProductionCompaniesResponse.toProductionCompanies(): ProductionCompanies =
        ProductionCompanies(
            logoPath = logoPath,
            name = name,
            id = id,
            originCountry = originCountry
        )

    private fun ProductionCountriesResponse.toProductionCountries(): ProductionCountries =
        ProductionCountries(iso = iso, name = name)

    private fun SpokenLanguagesResponse.toSpokenLanguages(): SpokenLanguages =
        SpokenLanguages(
            name = name,
            iso = iso,
            englishName = englishName
        )

    internal fun CreditsResponse.toCredits() = Credits(
        id = id,
        cast = cast.map { it.toCast() }
    )

    private fun CastResponse.toCast() = Cast(
        adult = adult,
        castId = castId,
        character = character,
        creditId = creditId,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        order = order,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
    )

    internal fun TrailerVideoResponse.toTrailerVideo(): TrailerVideo =
        TrailerVideo(id = id, results = results.map { it.toResultItem() })

    private fun ResultsItemResponse.toResultItem(): ResultsItem =
        ResultsItem(
            id = id,
            iso6391 = iso6391,
            iso31661 = iso31661,
            site = site,
            name = name,
            official = official,
            type = type,
            publishedAt = publishedAt,
            key = key,
            size = size,
        )
}