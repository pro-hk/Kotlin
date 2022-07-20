package com.prohk.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Memo(var no:Long?, var content: String, var datetime: Long)

class SqliteHelper(context: Context, name:String, version:Int)
                        :SQLiteOpenHelper(context,name,null, version) {
    //                   DB
    override fun onCreate(p0: SQLiteDatabase?) {
        // (이름 타입) : column -- primary key 인 경우 번호 자동부여 & 1씩 커짐 -- `예약어` 가능
        val create = "create table memo (`no` integer primary key, content text, datetime integer)"
        p0?.execSQL(create)
    }

    //                     DB             oldVersion   newVersion
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // 테이블에 변경사항 있을 경우 호출됨
        // SqliteHelper()에 생성자를 호출할 때 기존 데이터베이스와 version을 비교해서 높으면 호출됨
    }

    // 데이터 입력함수
    fun insertMemo(memo:Memo){
        // DB 가져오기
        val wd = writableDatabase
        // Memo를 입력타입으로 변환
        val values = ContentValues()
        values.put("content",memo.content)
        values.put("datetime",memo.datetime)
        // DB에 넣기 (테이블이름,null,값)
        wd.insert("memo",null,values)
        // DB 닫기
        wd.close()
    }

    // 데이터 조회함수
    fun selectMemo():MutableList<Memo> {
        val list = mutableListOf<Memo>()

        val select = "select * from memo"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null) // 반환값이 있음
        while(cursor.moveToNext()) {
            val a = cursor.getColumnIndex("no")
            val b = cursor.getColumnIndex("content")
            val c = cursor.getColumnIndex("datetime")
            val no = cursor.getLong(a)
            val content = cursor.getString(b)
            val datetime = cursor.getLong(c)

            val memo = Memo(no,content,datetime)
            list.add(memo)
        }
        cursor.close()
        rd.close()

        return list
    }

    // 데이터 수정함수
    fun updateMemo(memo:Memo) {
        val wd = writableDatabase

        val values = ContentValues()
        values.put("content",memo.content)
        values.put("datetime",memo.datetime)

        wd.update("memo",values,"no = ${memo.no}",null)
        wd.close()
    }

    // 데이터 삭제함수
    fun deleteMemo(memo:Memo) { // no:Long 가능
        val wd = writableDatabase
        /*val delete = "delete from memo where no = ${memo.no}"
        wd.execSQL(delete)*/
        wd.delete("memo","no = ${memo.no}",null) // 선택 가능
        wd.close()

    }
}