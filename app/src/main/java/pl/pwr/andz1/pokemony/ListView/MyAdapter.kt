package pl.pwr.andz1.pokemony.ListView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.pokemony.Pokemon
import pl.pwr.andz1.pokemony.databinding.PokemonDataBlocksBinding

class MyAdapter(private val myDataset: ArrayList<Pokemon>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: PokemonDataBlocksBinding) : RecyclerView.ViewHolder(view.root)
    {
        val bind = PokemonDataBlocksBinding.bind(view.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val binding =
            PokemonDataBlocksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = myDataset[position]
        holder.bind.apply {
            pokemonNameTextView.text = "Name: " + data.name
            pokemonTypesTextView.text = "Types: " + data.primaryType + data.secondaryType
            pokemonGenerationTextView.text = "Generation: " + data.generation.toString()
        }
    }

    override fun getItemCount() = myDataset.size
}