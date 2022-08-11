package com.example.bpaai_submission1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bpaai_submission1.Model.StoryModel
import com.example.bpaai_submission1.databinding.DetailLayoutBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Your Story Detail"

        val story = intent.getParcelableExtra<StoryModel>(STORY_DETAIL) as StoryModel
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(story.avatar)
                .into(imageView7)
            textView5.text = story.name
            textView6.text = story.description
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

    companion object {
        const val STORY_DETAIL = "Detail Story"
    }
}