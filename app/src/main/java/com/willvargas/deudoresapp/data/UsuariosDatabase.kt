package com.willvargas.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.willvargas.deudoresapp.data.dao.UserDAO
import com.willvargas.deudoresapp.data.entities.User


@Database(entities=[User::class],version= 1)
abstract class UsuariosDatabase : RoomDatabase() {
    abstract fun UserDAO(): UserDAO
}
