package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.Observer
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.costular.marvelheroes.R
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import kotlinx.android.synthetic.main.activity_hero_detail_favorite.*

/**
 * Created by costular on 18/03/2018.
 */
class MarvelHeroeDetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_HEROE = "heroe"
    }

    lateinit var marvelHeroDetailViewModel: MarvelHeroDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail_favorite)
        setupViewModel()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        val hero: MarvelHeroEntity? = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let {
            fillHeroData(it)
            heroDetailFavoriteButton.setOnClickListener {
                Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show()
                heroDetailFavoriteButton.setImageResource(android.R.drawable.star_big_on)
            }
        }

    }

    private fun setupViewModel() {
        marvelHeroDetailViewModel = MarvelHeroDetailViewModel()
        bindEvents()
    }

    private fun bindEvents() {
        marvelHeroDetailViewModel.heroeState
                .observe(this, Observer {hero ->
                    hero?.let { fillHeroData(it) }
                })
    }

    private fun fillHeroData(hero: MarvelHeroEntity) {
        Glide.with(this)
                .load(hero.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(heroDetailImage)

        heroDetailName.text = hero.name
        heroDetailRealName.text = hero.realName
        heroDetailHeight.text = hero.height
        heroDetailPower.text = hero.power
        heroDetailAbilities.text = hero.abilities
        heroDetailFavoriteButton.setImageResource(getFavoriteIcon(hero.favorite))
    }

    private fun getFavoriteIcon(favorite: Boolean): Int {
        if (favorite)
            return android.R.drawable.star_big_on
        return android.R.drawable.star_big_off
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}