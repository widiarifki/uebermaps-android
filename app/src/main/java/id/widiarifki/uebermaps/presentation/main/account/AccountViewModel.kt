package id.widiarifki.uebermaps.presentation.main.account

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.helper.Resource
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userLogin = StatedLiveData<User?>()

    init {
        getUserLogin()
        viewModelScope.launch {
            userRepository.refreshUserLogin()
        }
    }

    private fun getUserLogin() {
        userLogin.loading()
        userLogin.addSource(userRepository.userLogin) {
            userLogin.load(it)
        }
    }

    fun logoutUser() : StatedLiveData<User?> {
        userLogin.loading()
        viewModelScope.launch {
            try {
                userRepository.logoutUser()
            } catch (e: Exception) {
                userLogin.error(e)
            }
        }
        return userLogin
    }

}