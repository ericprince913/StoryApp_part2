package com.example.bpaai_submission1.paging

import android.content.Context
import com.example.bpaai_submission1.Retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): QuoteRepository {
        val database = QuoteDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return QuoteRepository(database, apiService)
    }
}

