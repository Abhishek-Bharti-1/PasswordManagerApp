package com.example.passwordmanagerapplication.data.local.dao

import androidx.room.*
import com.example.passwordmanagerapplication.data.local.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Query("SELECT * FROM passwords ORDER BY createdAt DESC")
    fun getAll(): Flow<List<PasswordEntity>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getById(id: Long): PasswordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: PasswordEntity)

    // ✅ Integer (Int) - EXACT match
    @Update
    fun update(entry: PasswordEntity)

    // ✅ Integer (Int) - EXACT match
    @Delete
    fun delete(entry: PasswordEntity)
}