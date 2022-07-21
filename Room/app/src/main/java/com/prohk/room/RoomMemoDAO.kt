package com.prohk.room

import androidx.room.*

@Dao
interface RoomMemoDAO {
    @Query("select * from roomMemo")
    fun getAll():List<RoomMemo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo:RoomMemo)

    @Delete
    fun delete(memo:RoomMemo)
}