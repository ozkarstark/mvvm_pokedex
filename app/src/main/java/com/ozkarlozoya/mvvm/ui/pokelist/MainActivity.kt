package com.ozkarlozoya.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozkarlozoya.mvvm.ui.pokelist.PokeListAdapter
import com.ozkarlozoya.mvvm.ui.pokelist.PokeListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel=ViewModelProvider(this).get(PokeListViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        pokelistRecyclerView.layoutManager = LinearLayoutManager(this)
        pokelistRecyclerView.adapter=PokeListAdapter{
            val intent= Intent(this,PokeInfoActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, Observer { list ->
            (pokelistRecyclerView.adapter as PokeListAdapter).setData(list)
        })
    }


}