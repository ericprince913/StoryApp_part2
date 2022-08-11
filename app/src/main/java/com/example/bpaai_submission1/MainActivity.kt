package com.example.bpaai_submission1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpaai_submission1.enterapp.LoginActivity
import com.example.bpaai_submission1.camera.MainCamera
import com.example.bpaai_submission1.databinding.ActivityMainBinding
import com.example.bpaai_submission1.paging.LoadingStateAdapter
import com.example.bpaai_submission1.paging.QuoteListAdapter
import com.example.bpaai_submission1.paging.QuoteViewModel


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {

    private lateinit var storyViewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    private val MainViewModel: QuoteViewModel by viewModels {
        QuoteViewModel.ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupViewModel()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        getStories()

        binding.button.setOnClickListener {
            startActivity(Intent(this, MainCamera::class.java))
        }

        binding.buttonLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        binding.outButton.setOnClickListener {
            storyViewModel.logout()
            true
            startActivity(Intent(this, SplashScreen::class.java))

        }

        binding.mapButton.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
    private fun setupViewModel() {
        storyViewModel = ViewModelProvider(
            this,
            ViewModelFactory(Preference.getInstance(dataStore))
        )[UserViewModel::class.java]
    }

    private fun getStories() {
        val adapter = QuoteListAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        storyViewModel.getUser().observe(this) { userAuth ->
            if(userAuth != null) {
                MainViewModel.stories("Bearer " + userAuth.token).observe(this) { stories ->
                    adapter.submitData(lifecycle, stories)
                }
            }
        }
    }

    /*private fun setStoriesData(items: List<ListStoryItem>) {
        val listStories = ArrayList<StoryModel>()
        for(item in items) {
            val story = StoryModel(
                item.name,
                item.photoUrl,
                item.description,
            )
            listStories.add(story)
        }

        val storyAdapter = StoryAdapter(listStories)
        binding.recyclerView.adapter = storyAdapter
    }*/

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "Story Activity"
    }

}