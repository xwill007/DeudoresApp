package com.willvargas.deudoresapp

import android.app.Application
import androidx.room.Room
import com.willvargas.deudoresapp.data.DebtorDatabase
import com.willvargas.deudoresapp.data.UsuariosDatabase

class DeudoresApp : Application() {

    companion object{
        lateinit var  database: DebtorDatabase
        lateinit var databaseUser: UsuariosDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, DebtorDatabase::class.java, "debtor_db").allowMainThreadQueries().build()
        databaseUser = Room.databaseBuilder(this, UsuariosDatabase::class.java, "usuario_db").allowMainThreadQueries().build()
    }

}