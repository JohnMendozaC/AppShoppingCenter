package com.lupesoft.appshoppingcenter.application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.application.extensions.showMessage
import com.lupesoft.appshoppingcenter.application.viewmodels.MoviesViewModel
import com.lupesoft.appshoppingcenter.databinding.FragmentAboutOfMovieLayoutBinding
import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.Status
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.response.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutOfMovieFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentAboutOfMovieLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutOfMovieLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeClick()
    }

    private fun subscribeClick() {
        with(binding) {
            movie = (arguments?.get("movie") as Movie)
            fabActionShoppingCart.setOnClickListener { showMenu(it) }
        }
    }

    private fun showMenu(v: View) {
        PopupMenu(requireContext(), v).apply {
            menuInflater.inflate(R.menu.popup_menu_detail, menu)
            setOnMenuItemClickListener { menuItem: MenuItem ->
                binding.movie?.let { data ->
                    when (menuItem.itemId) {
                        R.id.add_movie_detail -> {
                            viewModel.addMovie(data.id)
                                .observe(viewLifecycleOwner, Observer { result ->
                                    actionEventAddOrRemove(result)
                                })
                            true
                        }
                        R.id.delete_movie_detail -> {
                            viewModel.deleteMovie(data.id)
                                .observe(viewLifecycleOwner, Observer { result ->
                                    actionEventAddOrRemove(result)
                                })
                            true
                        }
                        else -> false
                    }
                } ?: false
            }
        }.also { it.show() }


    }

    private fun <T> actionEventAddOrRemove(result: Resource<T>) {
        with(binding) {
            when (result.status) {
                Status.LOADING -> loaderAbout.isLoading = false
                Status.SUCCESS -> loaderAbout.isLoading = true
                Status.ERROR -> loaderAbout.isLoading = true
            }
            if (result.status != Status.LOADING) {
                (result.message
                    ?: requireContext().getString(R.string.something_unexpected_happened)
                        ).showMessage(requireContext())
            }
        }
    }
}