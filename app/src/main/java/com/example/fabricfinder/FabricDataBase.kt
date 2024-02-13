package com.example.fabricfinder

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Minka Firth
 */
@Database(
        entities = [Fabric::class],
        version = 1
)
abstract class FabricDataBase: RoomDatabase() {

    abstract val dao: FabricDao

}