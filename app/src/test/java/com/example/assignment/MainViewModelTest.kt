package com.example.assignment

import androidx.lifecycle.MutableLiveData
import com.example.assignment.data.models.Artist
import com.example.assignment.data.repository.ArtistRepository
import com.example.assignment.ui.MainViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : BaseTestCase() {

    private lateinit var viewModel : MainViewModel
    var artistList: MutableLiveData<List<Artist>> = MutableLiveData()
    lateinit var repository: ArtistRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")



    @Before
    override fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = Mockito.mock(ArtistRepository::class.java)
        val artist = Gson().fromJson(DummyJson.ARTIST_DATA, Artist::class.java);
        val results: MutableList<Artist> = ArrayList()
        results.add(artist)
        artistList.postValue(results)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testOnGetDataButtonClicked() {
        viewModel.name = "Aiden"
        viewModel.onGetDataButtonClicked()
        Assert.assertEquals(true, viewModel.progress.value)
    }


    @Test
    fun testOnGetDataButtonClicked_2() {
        viewModel.name = null
        viewModel.onGetDataButtonClicked()
        Assert.assertEquals("Your Entry is Null!", viewModel.error.value)
        Assert.assertEquals(false, viewModel.progress.value)
    }

    @Test
    fun testOnGetDataButtonClicked_3() {
        viewModel.name = null
        viewModel.onGetDataButtonClicked()
        Assert.assertEquals(false, viewModel.progress.value)
    }

    @Test
    fun testOnGetDataButtonClicked_4() {
        viewModel.name = "Aiden"
        viewModel.onGetDataButtonClicked()
        Assert.assertEquals(null, viewModel.error.value)
    }

    @Test
    fun testOnGetDataButtonClicked_5() {
        runBlocking {
            launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
                viewModel.onGetDataButtonClicked()
                Assert.assertEquals("Your Entry is Null!", viewModel.error.value)
                Assert.assertEquals(false, viewModel.progress.value)
            }
        }
    }

    @After
    public override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

}