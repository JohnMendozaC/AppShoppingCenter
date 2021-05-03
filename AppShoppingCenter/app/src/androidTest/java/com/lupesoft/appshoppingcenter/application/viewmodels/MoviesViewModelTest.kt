package com.lupesoft.appshoppingcenter.application.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.lupesoft.appshoppingcenter.application.utilities.getValue
import com.lupesoft.appshoppingcenter.domain.services.MovieService
import com.lupesoft.appshoppingcenter.domain.services.ShoppingCartService
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.AppDataBase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
class MoviesViewModelTest {

    private lateinit var appDataBase: AppDataBase
    private lateinit var viewModel: MoviesViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var movieService: MovieService

    @Inject
    lateinit var shoppingCartService: ShoppingCartService

    @Before
    fun setUp() {
        hiltRule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        viewModel = MoviesViewModel(movieService, shoppingCartService)
    }

    @After
    fun tearDown() {
        appDataBase.close()
    }

    @Test
    fun consumeFirstService_AllMovies() {
        //Arrange
        getValue(viewModel.allMovies)
        Thread.sleep(100)

        //Act
        val allMovies = getValue(viewModel.allMovies).data

        //Assert
        assertNotNull(allMovies)
    }
}