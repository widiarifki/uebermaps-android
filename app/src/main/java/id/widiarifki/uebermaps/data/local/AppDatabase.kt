package id.widiarifki.uebermaps.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.widiarifki.uebermaps.data.local.dao.UserDao
import id.widiarifki.uebermaps.data.model.User

@Database(entities = [User::class], version = DbConstant.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DbConstant.DB_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(object: RoomDatabase.Callback() {
                            // TODO:
                        })
                        .build()
                }
            }

            return instance!!
        }

    }
}