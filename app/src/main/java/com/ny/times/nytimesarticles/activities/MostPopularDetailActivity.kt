package com.ny.times.nytimesarticles.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ny.times.nytimesarticles.R
import com.ny.times.nytimesarticles.models.MostPopular
import com.ny.times.nytimesarticles.utils.Constants

class MostPopularDetailActivity : BaseAppCompatActivity() {

    private var builder: CustomTabsIntent.Builder? = null
    private var customTabsIntent: CustomTabsIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_detail)

        val mostPopular = intent.getSerializableExtra(EXTRA_NEWS_ITEM) as MostPopular

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = mostPopular.section

        val imageViewTop = findViewById<ImageView>(R.id.xImageViewTop)
        val textViewTitle = findViewById<TextView>(R.id.xTextViewTitle)
        val textViewDate = findViewById<TextView>(R.id.xTextViewDate)
        val textViewDescription = findViewById<TextView>(R.id.xTextViewDescription)
        val textViewBy = findViewById<TextView>(R.id.xTextViewBy)
        val textViewReadMore = findViewById<TextView>(R.id.xTextViewReadMore)

        Glide.with(this)
            .load(mostPopular.media[0].mediaMetaData[0].url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageViewTop)

        textViewTitle.text = mostPopular.title
        textViewBy.text = mostPopular.byline
        textViewDate.text = mostPopular.publishedDate
        textViewDescription.text = mostPopular.abs

        textViewReadMore.setOnClickListener {
            if (builder == null) {
                builder = CustomTabsIntent.Builder()
            }
            builder?.setToolbarColor(ContextCompat.getColor(this, R.color.purple_500))
            if (customTabsIntent == null) {
                customTabsIntent = builder?.build()
            }
            customTabsIntent?.launchUrl(this, Uri.parse(mostPopular.url))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    companion object {
        private const val TAG = "MostPopularDetailActivity"
        private const val EXTRA_NEWS_ITEM = "extra:mostPopularObj"

        fun start(context: Context, mostPopular: MostPopular) {

            context.startActivity(
                Intent(context, MostPopularDetailActivity::class.java)
                .putExtra(EXTRA_NEWS_ITEM, mostPopular))
        }
    }
}
