package pl.pwr.andz1.pokemony.ListView

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.pokemony.R
import pl.pwr.andz1.pokemony.databinding.ListViewFragmentBinding


class ListViewFragment : Fragment() {

    private lateinit var viewModel: ListViewViewModel
    private lateinit var binding : ListViewFragmentBinding
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListViewFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ListViewViewModel::class.java)
        viewAdapter = MyAdapter(viewModel, this.context)
        viewManager = LinearLayoutManager(this.context)
        binding.list.apply{
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        setHasOptionsMenu(true)

        container

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()

        val reset = menu.add(Menu.NONE, 0, 0, "Reset")
        reset.setOnMenuItemClickListener {
            viewModel.resetList()
            viewAdapter.notifyDataSetChanged()
            true
        }
        val favourite = menu.add(Menu.NONE, 0, 0, "Favourite")
        favourite.setOnMenuItemClickListener {
            viewModel.takeFavourite()
            viewAdapter.notifyDataSetChanged()
            true
        }

        val generationsMenu = menu.addSubMenu(Menu.NONE, 0, 0, "Generation")
        val generations = mutableSetOf<Int>()
        viewModel.get_unchanged_list().forEach {
            generations.add(it.generation)
        }
        generations.sorted().forEach{
            val generationItem: MenuItem = generationsMenu.add(
                Menu.NONE,
                0,
                0,
                it.toString()
            )
            val gen = it
            generationItem.setOnMenuItemClickListener {
                viewModel.takeGen(gen)
                viewAdapter.notifyDataSetChanged()
                return@setOnMenuItemClickListener true
            }
        }

        val typesMenu = menu.addSubMenu(Menu.NONE, 0, 0, "Type")
        val types = mutableSetOf<String>()
        viewModel.get_unchanged_list().forEach {
            types.add(it.primaryType)
            if(it.secondaryType != "") types.add(it.secondaryType)
        }
        types.sorted().forEach{
            val typeItem: MenuItem = typesMenu.add(
                    Menu.NONE,
                    0,
                    0,
                    it
            )
            val type = it
            typeItem.setOnMenuItemClickListener {
                viewModel.takeType(type)
                viewAdapter.notifyDataSetChanged()
                return@setOnMenuItemClickListener true
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

}