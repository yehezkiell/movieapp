package com.tkpd.basketballapp.teamlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.basketballapp.model.NbaBasketballTeams
import com.tkpd.basketballapp.util.RetrofitInstanceBuilder
import kotlinx.coroutines.coroutineScope
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
        return RetrofitInstanceBuilder.retrofitInstance.getNBALeagueTeam("4387").body()
    }
}