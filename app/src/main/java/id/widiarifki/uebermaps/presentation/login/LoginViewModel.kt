package id.widiarifki.uebermaps.presentation.login

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    val fieldUsername = ObservableField<String>()
    val fieldPassword = ObservableField<String>()
    val loginState = StatedLiveData<User>()

    fun actionLogin(view: View) {
        if (!validateInput(fieldUsername.get(), fieldPassword.get()))
            return

        loginState.loading()
        viewModelScope.launch {
            loginState.addSource(userRepository.loginUser(fieldUsername.get(), fieldPassword.get())) {
                when {
                    it.isSuccess() -> loginState.success()
                    it.isError() -> loginState.error(it.message)
                }
            }
        }
    }

    private fun validateInput(username: String?, password: String?): Boolean {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginState.error("Username & password harus diisi")
            return false
        }
        return true
    }
}