package id.widiarifki.uebermaps.repository

import id.widiarifki.uebermaps.data.local.dao.UserDao
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.data.network.APIService
import id.widiarifki.uebermaps.helper.StatedLiveData
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val apiService: APIService,
    private val userDao: UserDao
) {

    val userLogin = userDao.getUserLogin()

    suspend fun loginUser(username: String?, password: String?): StatedLiveData<User> {
        val liveData = StatedLiveData<User>()
        try {
            val loginRequest = apiService.loginUser(username, password)
            if (loginRequest.data != null) {
                saveLoginUser(loginRequest.data)
                liveData.load(loginRequest.data)
            } else {
                liveData.error(loginRequest.meta?.errorMessage)
            }
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }

    suspend fun logoutUser() {
        try {
            delay(3000) // pretend logout process need to call API as well
            userDao.deleteUserLogin()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun refreshUserLogin() {
        try {
            val loginUser = userDao.getUserLoginSync()
            loginUser?.let {
                val request = apiService.getUser(it.id)
                request.data?.let { user ->
                    saveLoginUser(user)
                }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getUser(userId: Int?): StatedLiveData<User> {
        val liveData = StatedLiveData<User>()
        try {
            val request = apiService.getUser(userId)
            request.data?.let { user ->
                val loginUser = userDao.getUserLoginSync()

                if (user.id == loginUser?.id) saveLoginUser(request.data)

                liveData.load(request.data)
            } ?: run {
                liveData.error(request.meta?.errorMessage)
            }
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }

    private suspend fun saveLoginUser(user: User?) {
        try {
            user?.let {
                userDao.insert(it.also {
                    it.isUserLogin = true
                })
            }
        } catch (e: Exception) {
            throw (e)
        }
    }
}