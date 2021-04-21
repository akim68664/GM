package com.example.assignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.models.Artist
import com.example.assignment.data.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

// injecting a repository that contains the api call function
@HiltViewModel
class MainViewModel @Inject constructor(var repository: ArtistRepository) : ViewModel() {

    var name: String? = null
    var artistList: MutableLiveData<List<Artist>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    var progress: MutableLiveData<Boolean> = MutableLiveData()

    /*
    creating a function that when the button is clicked,
    we first the progress bar and check if the name is null or empty.
    if the name is null or empty, we post the value for error and pass a string to show on activity
    if the name is not null or empty, we replace the white space with underscore by convention
    then we launch viewmodelscope to execute the suspend function inside the repository then we will pass a name
    after we get our results and store it inside a variable, we first check if the result count is 0
    if it's 0, then we want to post a message that there is no returns with your search.
    if not, we will post the value so that we can pass the data into the recycler view from main activity
    In case of exception, we have coroutine exception handler to cancel our job.
     */
    fun onGetDataButtonClicked() {
        progress.postValue(true)
        if (!name.isNullOrEmpty()) {
            name?.replace(" ", "_")
            viewModelScope.launch {
                try {
                    var response = repository.getData(name!!)
                    if (response.resultCount == 0) {
                        progress.postValue(false)
                        error.postValue("Your search returns 0 values")
                            .also { artistList.postValue(response.results) }
                    } else
                        artistList.postValue(response.results)

                } catch (e: Exception) {
                    cancel()
                    error.postValue(e.message)
                }
            }
        } else {
            progress.postValue(false)
            error.postValue("Your Entry is Null!")
        }
    }

}