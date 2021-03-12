package id.widiarifki.uebermaps.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.widiarifki.uebermaps.data.local.DbConstant
import id.widiarifki.uebermaps.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM ${DbConstant.TBL_USER} WHERE isUserLogin=1")
    fun getUserLogin() : LiveData<User?>

    @Query("SELECT * FROM ${DbConstant.TBL_USER} WHERE isUserLogin=1")
    suspend fun getUserLoginSync() : User?

    @Query("DELETE FROM ${DbConstant.TBL_USER} WHERE isUserLogin=1")
    suspend fun deleteUserLogin()

}