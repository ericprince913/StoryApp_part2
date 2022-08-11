package com.example.bpaai_submission1.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAuthentication (
    val token: String,
    val isLogin: Boolean
    ): Parcelable