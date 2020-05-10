package com.tkpd.basketballapp.util

import androidx.lifecycle.LiveData
import com.tkpd.basketballapp.model.NbaBasketballTeams
import com.tkpd.basketballapp.model.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yehezkiel on 10/05/20
 */
interface BasketballAPI {

    @GET("lookup_all_teams.php")
    suspend fun getNBALeagueTeam(@Query("id") leagueId: String): Response<NbaBasketballTeams>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Response<Team>
}