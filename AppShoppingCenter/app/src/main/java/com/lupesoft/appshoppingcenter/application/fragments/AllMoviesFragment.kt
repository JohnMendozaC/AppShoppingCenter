package com.lupesoft.appshoppingcenter.application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.application.adapters.MovieAdapter
import com.lupesoft.appshoppingcenter.application.extensions.showMessage
import com.lupesoft.appshoppingcenter.application.viewmodels.MoviesViewModel
import com.lupesoft.appshoppingcenter.databinding.FragmentAllMoviesLayoutBinding
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.Status
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.response.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentAllMoviesLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllMoviesLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeActionShoppingCart()
        subscribeUi()
    }

    private fun <T> actionEventAddOrRemove(result: Resource<T>) {
        with(binding) {
            when (result.status) {
                Status.LOADING -> loader.isLoading = false
                Status.SUCCESS -> loader.isLoading = true
                Status.ERROR -> loader.isLoading = true
            }
            if (result.status != Status.LOADING) {
                (result.message
                    ?: requireContext().getString(R.string.something_unexpected_happened))
                    .showMessage(requireContext())
            }
        }
    }

    private fun subscribeActionShoppingCart() {
        with(binding) {
            binding.movieList.adapter = MovieAdapter({ addOrDelete, movieId ->
                loader.isLoading = true
                if (addOrDelete) {
                    viewModel.addMovie(movieId).observe(viewLifecycleOwner, Observer { result ->
                        actionEventAddOrRemove(result)
                    })
                } else {
                    viewModel.deleteMovie(movieId).observe(viewLifecycleOwner, Observer { result ->
                        actionEventAddOrRemove(result)
                    })
                }
            }, R.menu.popup_menu)
        }
    }

    private fun subscribeUi() {
        with(binding) {
            loader.isLoading = false
            viewModel.allMovies.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listMovie = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        listMovie = null
                        loader.isLoading = true
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
        }
    }
}