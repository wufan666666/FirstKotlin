package com.wufanfirstkotlin.kotlinyufa

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.function.Consumer

@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {
    println("hello,world!")

    var list: List<String> = listOf("1", "2", "3")


    list.forEach(Consumer { println(it) })


    println("----------")
    list.forEach(System.out::println)
}
