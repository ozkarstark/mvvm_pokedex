package com.ozkarlozoya.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ozkarlozoya.mvvm.ui.pokeinfo.PokeInfoViewModel
import kotlinx.android.synthetic.main.activity_poke_info.*

class PokeInfoActivity : AppCompatActivity() {

    lateinit var viewModel:PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_info)

        viewModel=ViewModelProvider(this).get(PokeInfoViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        val id=intent.extras?.get("id") as Int
        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            nameTextView.text=pokemon.name
            heightText.text="Altura: ${pokemon.height/10.0}"
            weightText.text="Peso: ${pokemon.weight/10.0}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(ImageView)
        })
    }
}