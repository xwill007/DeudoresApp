package com.willvargas.deudoresapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_usuarios")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "nombre") val nombre :String ? = null,
    @ColumnInfo(name = "telefono") val telefono :String ? = null,
    @ColumnInfo(name = "correo") val correo :String ? = null,
    @ColumnInfo(name = "clave") val clave :String ? = null,

    )