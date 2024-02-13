package com.example.fabricfinder

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Minka Firth
 */
@Entity
data class Fabric(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
//        val image: String,
        val length: Int,
        val width: Int,
        val colour: String
)
