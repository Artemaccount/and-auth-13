package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.model.AuthModelState
import ru.netology.nmedia.model.FeedModelState
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryImpl

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    private val _authState = MutableLiveData<AuthModelState>()
    val authState: LiveData<AuthModelState>
        get() = _authState

    private val repository: PostRepository =
        PostRepositoryImpl(AppDb.getInstance(context = application).postDao())

    fun sign(login: String, password: String) {
        _authState.value = AuthModelState(error = false)
        viewModelScope.launch {
            try {
                repository.signIn(login, password)
            } catch (e: Exception) {
                _authState.value = AuthModelState(error = true)
            }
        }
    }
}