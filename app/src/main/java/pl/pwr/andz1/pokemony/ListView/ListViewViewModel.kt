package pl.pwr.andz1.pokemony.ListView

import androidx.lifecycle.ViewModel
import pl.pwr.andz1.pokemony.Pokemon
import pl.pwr.andz1.pokemony.R

class ListViewViewModel : ViewModel() {

    private lateinit var pokemonList: ArrayList<Pokemon>
    private lateinit var filterList: ArrayList<Pokemon>
    init
    {
        pokemonList = ArrayList<Pokemon>()
        pokemonList.add(Pokemon("Pikachu", "Electric", "", 1, R.drawable.pikachu))
        pokemonList.add(Pokemon("Lotad", "Water", "Grass", 3, R.drawable.lotad))
        pokemonList.add(Pokemon("Mareep", "Electric", "", 2, R.drawable.mareep))
        pokemonList.add(Pokemon("Charizard", "Fire", "Flying", 1, R.drawable.charizard))
        pokemonList.add(Pokemon("Snorlax", "Normal", "", 1, R.drawable.snorlax))
        pokemonList.add(Pokemon("Skarmory", "Steel", "Flying", 2, R.drawable.skarmory))
        pokemonList.add(Pokemon("Treeko", "Grass", "", 3, R.drawable.treecko))
        pokemonList.add(Pokemon("Scizor", "Bug", "Steel", 2, R.drawable.scizor))
        pokemonList.add(Pokemon("Torchic", "Fire", "", 3, R.drawable.torchic))
        filterList = pokemonList
    }

    fun get_unchanged_list() : ArrayList<Pokemon>{
        return pokemonList
    }

    fun get_pokemon_list() : ArrayList<Pokemon>{
        return filterList
    }

    fun resetList() {
        filterList = pokemonList
    }

    fun takeFavourite() {
        filterList = ArrayList(pokemonList.filter{it.favourite})
    }

    fun takeGen(gen : Int){
        filterList = ArrayList(pokemonList.filter{it.generation == gen})
    }

}