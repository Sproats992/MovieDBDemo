package com.robertsproats.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.robertsproats.domain.model.DomainMoviesModel
import com.robertsproats.domain.usecase.feature.GetMoviesUseCase
import com.robertsproats.movies.boundary.MoviesPresentationMapper
import com.robertsproats.movies.features.master.MoviesViewModel
import com.robertsproats.movies.model.MoviePresentationItemModel
import com.robertsproats.movies.model.MoviesPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestLatencyAnalysisViewModel {

    val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Mock
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Mock
    lateinit var moviesPresentationMapper: MoviesPresentationMapper

    @Mock
    lateinit var flowObject: Flow<DomainMoviesModel>

    // create a fake lifecycleobserver for testing.
    class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
        private val lifecycle = LifecycleRegistry(this)

        init {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        override fun getLifecycle(): Lifecycle = lifecycle

        override fun onChanged(t: T) {
            handler(t)
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        }
    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    fun testStartStreamIsLiveData() {
        testDispatcher.runBlockingTest {
            // Given
            val expectedArray: Array<MoviePresentationItemModel>? = arrayOf(
                    MoviePresentationItemModel(
                            id = 42,
                            voteAverage = 43.0,
                            backdropPath = "backdropPath testStartStreamIsLiveData",
                            releaseDate = "releaseDate testStartStreamIsLiveData",
                            title = "testStartStreamIsLiveData",
                            overview = "overview testStartStreamIsLiveData",
                            posterPath = "posterPath testStartStreamIsLiveData")
            )

            val expectedResult = MoviesPresentationModel(
                    moviesList = expectedArray
            )

            val expectedDomainMoviePresentationItemModel = DomainMoviesModel(
                    movies = null
            )

            flowObject = flow {
                emit(expectedDomainMoviePresentationItemModel)
            }

            whenever(getMoviesUseCase.execute(any())).thenReturn(flowObject)
            whenever(moviesPresentationMapper.mapMoviesData(any())).thenReturn(expectedResult)
            val moviesViewModel = MoviesViewModel(
                    getMoviesUseCase = getMoviesUseCase,
                    moviesPresentationMapper = moviesPresentationMapper,
                    defaultDispatcher = testDispatcher
            )

            // Then
            moviesViewModel.moviesLiveData.observeOnce {
                Assert.assertArrayEquals(it.moviesList, expectedResult.moviesList)
                Assert.assertEquals(true, false)
            }

            // When
            moviesViewModel.fetchMovies()
        }

    }
}
