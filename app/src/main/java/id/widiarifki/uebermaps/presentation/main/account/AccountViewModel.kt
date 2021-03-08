package id.widiarifki.uebermaps.presentation.main.account

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel
@Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    val userLogin = StatedLiveData<User?>()

    init {
        getUserLogin()
    }

    private fun getUserLogin() {
        userLogin.loading()
        userLogin.addSource(userRepository.userLogin) {
            userLogin.load(it)
        }
    }

    fun actionLogout(view: View)
    {
        viewModelScope.launch {
            userRepository.logoutUser()
        }
    }

}