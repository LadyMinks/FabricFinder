package com.example.fabricfinder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * @author Minka Firth
 */
@Dao
interface FabricDao {
    @Upsert
    suspend fun upsertFabric(fabric: Fabric)

    @Delete
    suspend fun deleteFabric(fabric: Fabric)

    @Query("SELECT * FROM fabric ORDER BY colour ASC")
    fun getFabricOrderedByColour(): Flow<List<Fabric>>
    @Query("SELECT * FROM fabric ORDER BY length ASC")
    fun getFabricOrderedByLength(): Flow<List<Fabric>>
    @Query("SELECT * FROM fabric ORDER BY width ASC")
    fun getFabricOrderedByWidth(): Flow<List<Fabric>>
}