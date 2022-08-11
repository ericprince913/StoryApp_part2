package com.example.bpaai_submission1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bpaai_submission1.Model.UserAuthentication
import kotlinx.coroutines.launch

class UserViewModel (private val pref: Preference) : ViewModel() {

    fun getUser() : LiveData<UserAuthentication> {
        return pref.getUser().asLiveData()
    }

    fun saveUser(user: UserAuthentication) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}