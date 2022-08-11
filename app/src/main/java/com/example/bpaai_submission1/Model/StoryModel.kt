package com.example.bpaai_submission1.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryModel (
    var name: String? = null,
    var avatar: String? = null,
    var description: String? = null,
    var lat: Double? = null,
    var lon: Double? = null
) : Parcelable