package com.willvargas.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.willvargas.deudoresapp.data.dao.DebtorDao
import com.willvargas.deudoresapp.data.entities.Debtor

@Database(entities = [Debtor::class], version = 1)
abstract class DebtorDatabase: RoomDatabase(){

    abstract fun DebtorDao(): DebtorDao

}
