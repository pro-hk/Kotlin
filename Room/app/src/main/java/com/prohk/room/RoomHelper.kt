package com.prohk.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper:RoomDatabase() { // Dao 꺼내서 사용
    abstract fun roomMemoDao():RoomMemoDAO
}