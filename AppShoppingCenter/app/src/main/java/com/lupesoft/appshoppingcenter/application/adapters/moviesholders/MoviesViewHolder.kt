package com.lupesoft.appshoppingcenter.application.adapters.moviesholders

import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.databinding.ItemMovieBinding
import com.lupesoft.appshoppingcenter.domain.entitys.Movie

class MoviesViewHolder(
    private val actionShoppingCart: (addOrDelete: Boolean, movieId: Int) -> Unit,
    @MenuRes private val menuRes: Int,
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener { v ->
            showMenu(v)
        }
    }

    private fun showMenu(v: View) {
        PopupMenu(v.context, v).apply {
            menuInflater.inflate(menuRes, menu)

            setOnMenuItemClickListener { menuItem: MenuItem ->
                binding.movie?.let { data ->
                    when (menuItem.itemId) {
                        R.id.add_movie -> {
                            actionShoppingCart(true, data.id)
                            true
                        }
                        R.id.delete_movie -> {
                            actionShoppingCart(false, data.id)
                            true
                        }
                        R.id.detail_movie -> {
                            navigate(data, v)
                            true
                        }
                        else -> false
                    }
                } ?: false
            }
        }.also { it.show() }
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