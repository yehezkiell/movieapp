package com.tkpd.movieapp.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.model.NbaBasketballTeams
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import kotlinx.coroutines.launch

/**
 * Created by Yehezkiel on 10/05/20
 */
class TeamListViewModel : ViewModel() {

    val allTeams = MutableLiveData<NbaBasketballTeams>()

    fun getNBATeams() {
        viewModelScope.launch {
            allTeams.value = getNbaAPI()
        }
    }

    private suspend fun getNbaAPI(): NbaBasketballTeams? {
        return RetrofitInstanceBuilder.RETROFIT_INSTANCE.getNBALeagueTeam("4387").body()
    }
}