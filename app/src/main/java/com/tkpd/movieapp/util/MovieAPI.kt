package com.tkpd.movieapp.util

import com.tkpd.movieapp.model.NbaBasketballTeams
import com.tkpd.movieapp.model.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yehezkiel on 10/05/20
 */
interface MovieAPI {

    @GET("lookup_all_teams.php")
    suspend fun getNBALeagueTeam(@Query("id") leagueId: String): Response<NbaBasketballTeams>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Response<Team>
}