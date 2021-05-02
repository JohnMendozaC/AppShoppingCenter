package com.lupesoft.appshoppingcenter.application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lupesoft.appshoppingcenter.application.adapters.MovieAdapter
import com.lupesoft.appshoppingcenter.application.viewmodels.ShoppingCartViewModel
import com.lupesoft.appshoppingcenter.databinding.FragmentShoppingCartLayoutBinding
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {

    private val viewModel: ShoppingCartViewModel by viewModels()
    private lateinit var binding: FragmentShoppingCartLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeActionShoppingCart()
        subscribeUI()
    }

    private fun subscribeActionShoppingCart() {
        with(binding) {
            shoppingCartMovieList.adapter = MovieAdapter { addOrDelete, movieId ->
                loader.isLoading = true
                if (addOrDelete) {
                    viewModel.addMovie(movieId).observe(viewLifecycleOwner, Observer { result ->
                        when (result.status) {
                            Status.LOADING -> loader.isLoading = false
                            Status.SUCCESS -> {
                                loader.isLoading = true
                            }
                            Status.ERROR -> {
                                loader.isLoading = true
                                Toast.makeText(
                                    context,
                                    "Error no se pudo agregar la pelicula",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    })
                } else {
                    viewModel.deleteMovie(movieId).observe(viewLifecycleOwner, Observer { result ->
                        when (result.status) {
                            Status.LOADING -> loader.isLoading = false
                            Status.SUCCESS -> {
                                loader.isLoading = true
                            }
                            Status.ERROR -> {
                                loader.isLoading = true
                                Toast.makeText(
                                    context,
                                    "Error no se pudo eliminar la pelicula",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun subscribeUI() {
        with(binding) {
            loader.isLoading = false
            viewModel.shoppingCartMovies.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listShoppingCartMovie = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        listShoppingCartMovie = null
                        loader.isLoading = true
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
        }
    }

}