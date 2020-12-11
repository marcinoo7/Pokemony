package pl.pwr.andz1.pokemony.ListView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.pokemony.Pokemon
import pl.pwr.andz1.pokemony.databinding.PokemonDataBlocksBinding
import pl.pwr.andz1.pokemony.*

class MyAdapter(private val myDataset: ListViewViewModel):
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
            pokemonNameTextView.text = data.name
            pokemonTypesTextView.text = types
            pokemonGenerationTextView.text = data.generation.toString()
            pokemonImageView.setImageResource(data.image)
        }
    }

    override fun getItemCount() = myDataset.get_pokemon_list().size
}