package com.prohk.fileio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prohk.fileio.databinding.ActivityMainBinding
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val directoryName = "memo"
            val fileName = "memo01.txt"

            btnSave.setOnClickListener {
                val content = textWrite.text.toString()
                writeTextFile(directoryName,fileName,content)
            }

            val path = directoryName + "/" + fileName
            val writtenContent = readTextFile(path)
            textRead.text = writtenContent
        }
    }

    fun writeTextFile(directory:String,fileName:String,content:String) {
        // 앱 기본경로 / files / memo
        val dir = File(filesDir.path+"/"+directory)
        if(!dir.exists()) dir.mkdirs() // 폴더 생성

        val fullPath = dir.path + "/" + fileName
        val writer = FileWriter(fullPath)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
        writer.close()
    }

    fun readTextFile(path:String):String {
        val fullPath = File(filesDir.path+ "/" + path)
        if(!fullPath.exists()) return ""

        val reader = FileReader(fullPath)
        val buffer = BufferedReader(reader)

        val result = StringBuilder()  // StringBuffer 가능
        var temp:String? = ""
        while (true) {
            temp = buffer.readLine()
            if(temp==null) break
            result.append(temp).append("\n")
        }
        buffer.close()
        reader.close()
        return result.toString()
    }
}