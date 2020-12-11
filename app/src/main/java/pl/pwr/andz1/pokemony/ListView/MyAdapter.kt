package pl.pwr.andz1.pokemony.ListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.pokemony.Pokemon
import pl.pwr.andz1.pokemony.databinding.PokemonDataBlocksBinding
import pl.pwr.andz1.pokemony.*

class MyAdapter(private val myDataset: ListViewViewModel, context : Context?):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val bind = PokemonDataBlocksBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val binding =
            PokemonDataBlocksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = myDataset.get_pokemon_list()[position]

        holder.bind.apply {
            val types = data.primaryType + " " + data.secondaryType
            pokemonNameTextView.text = (mContext?.getString(R.string.pokemon_name) + " " + data.name)
            pokemonTypesTextView.text = (mContext?.getString(R.string.pokemon_types) + " " + types)
            pokemonGenerationTextView.text =
                    (mContext?.getString(R.string.pokemon_generation) + " " + data.generation)
            pokemonImageView.setImageResource(data.image)
            pokemonFavCheckbox.isChecked = data.favourite

            pokemonFavCheckbox.setOnClickListener{ changeFav(position) }
        }
    }

    private fun changeFav(position : Int){
        myDataset.changeFav(position)
    }

    val mContext = context

    override fun getItemCount() = myDataset.get_pokemon_list().size
}