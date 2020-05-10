package com.tkpd.basketballapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.basketballapp.teamlist.TeamListViewModel

/**
 * Created by Yehezkiel on 10/05/20
 */
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamListViewModel() as T
    }
}