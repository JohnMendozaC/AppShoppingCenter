package com.lupesoft.appshoppingcenter.application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.application.adapters.MovieAdapter
import com.lupesoft.appshoppingcenter.application.extensions.showMessage
import com.lupesoft.appshoppingcenter.application.viewmodels.ShoppingCartViewModel
import com.lupesoft.appshoppingcenter.databinding.FragmentShoppingCartLayoutBinding
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.Status
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.response.Resource
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
        updateListMovies()
        subscribeActionRemoveAllMovies()
    }

    private fun subscribeActionRemoveAllMovies() {
        with(binding) {
            fabRemoveAllMovies.setOnClickListener {
                loaderShopping.isLoading = true
                viewModel.deleteAllMovie()
                    .observe(viewLifecycleOwner, Observer { result ->
                        actionEventAddOrRemove(result)
                    })
            }
        }
    }

    private fun subscribeActionShoppingCart() {
        with(binding) {
            shoppingCartMovieList.adapter = MovieAdapter(
                { _, movieId ->
                    loaderShopping.isLoading = true
                    viewModel.deleteMovie(movieId)
                        .observe(viewLifecycleOwner, Observer { result ->
                            actionEventAddOrRemove(result)
                        })
                }, menuRes = R.menu.popup_menu_shopping
            )
        }
    }

    private fun <T> actionEventAddOrRemove(result: Resource<T>) {
        with(binding) {
            when (result.status) {
                Status.LOADING -> loaderShopping.isLoading = false
                Status.SUCCESS -> {
                    updateListMovies()
                    loaderShopping.isLoading = true
                }
                Status.ERROR -> loaderShopping.isLoading = true
            }
            if (result.status != Status.LOADING) {
                (result.message
                    ?: requireContext().getString(R.string.something_unexpected_happened)
                        ).showMessage(requireContext())
            }
        }
    }

    private fun updateListMovies() {
        with(binding) {
            loaderShopping.isLoading = false
            viewModel.shoppingCartMovies.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Status.LOADING -> loaderShopping.isLoading = false
                    Status.SUCCESS -> {
                        listShoppingCartMovie = result.data
                        loaderShopping.isLoading = true
                    }
                    Status.ERROR -> {
                        listShoppingCartMovie = null
                        loaderShopping.isLoading = true
                        (result.message
                            ?: requireContext().getString(R.string.something_unexpected_happened)
                                ).showMessage(requireContext())
                    }
                }
            })
        }
    }
}