package com.willvargas.deudoresapp.data.dao

import androidx.room.*
import com.willvargas.deudoresapp.data.entities.User

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * From tabla_usuarios WHERE correo LIKE :correo")
    fun searchUser(correo: String): User

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}