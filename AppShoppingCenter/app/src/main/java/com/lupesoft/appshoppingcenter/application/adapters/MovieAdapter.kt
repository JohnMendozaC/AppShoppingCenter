package com.lupesoft.appshoppingcenter.application.adapters

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.databinding.ItemMovieBinding
import com.lupesoft.appshoppingcenter.domain.entitys.Movie

class MovieAdapter(
    private val actionShoppingCart: (addOrDelete: Boolean, movieId: Int) -> Unit
) : ListAdapter<Movie, RecyclerView.ViewHolder>(TeamDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TeamViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val team = getItem(position)
        (holder as TeamViewHolder).bind(team)
    }

    inner class TeamViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { v ->
                showMenu(v)
            }
        }


        private fun showMenu(v: View) {
            val popup = PopupMenu(v.context, v)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                binding.movie?.let { data ->
                    when (menuItem.itemId) {
                        R.id.add_movie -> {
                            actionShoppingCart(true, data.id)
                        }
                        R.id.delete_movie -> {
                            actionShoppingCart(false, data.id)
                        }
                        R.id.detail_movie -> {
                            navigate(data, v)
                        }
                    }
                }
                true
            }
            popup.setOnDismissListener {
                // Respond to popup being dismissed.
            }
            // Show the popup menu.
            popup.show()
        }

        private fun navigate(elem: Movie, view: View) {
            view.findNavController().navigate(
                R.id.action_allMoviesFragment_to_aboutOfMovieFragment, bundleOf(
                    "movie" to elem
                )
            )
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
        }
    }
}

private class TeamDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.equals(newItem)
}
