package com.wufanfirstkotlin.yufalianxi

import java.lang.Integer.parseInt


/*
* 练习kotlin方法调用
* */


fun sum1(a: Int, b: Int): Int { //a,b 输入参数 a+b为返回值
    return a + b
}

fun sum2(a: Int, b: Int) = a + b //由系统自动判断返回值

public fun sum3(a: Int, b: Int) = a + b //public的方法必须申明返回值类型

fun printSum1(a: Int, b: Int): Unit { //Unit类似于java中的void 无返回值
    println(a + b)
}

fun printSum2(a: Int, b: Int) { //Unit可以省略 无返回值
    println(a + b)
}

fun vars(vararg a: Int) {
    for (vt in a) {
        print(vt)
    }
}

fun printString() {
    var a = 1 //var 代表可变变量
    val b = "a is $a" //val代表不可变常量  类似于java中的final , var和val在引用前必须被初始化赋值
    a = 2
    val c = "${b.replace("is", "was")},but now is $a"
    println(a)
    println(b)
    println(c)
}

val lambdaSum: (Int, Int) -> Int = { x, y -> x + y } //lambda表达式


fun showAge() {
    val age: String? = null//类型后面加？代表可以为null
    //val ages = age!!.toInt() //如果age为null ，则报空指针异常
    val ages1 = age?.toInt() //如果age 为null，则打印 null
    val ages2 = age?.toInt() ?: -1 //如果age为null ,则打印 -1
    println(age)
    //println(ages)
    println(ages1)
    println(ages2)
}

fun getStringLength(str: Any): Int? {
    if (str !is String) // is类似于java中的类型判断instanceof关键字
        return null

    return str.length
}

fun main(args: Array<String>) {
    println("sum1的值：" + sum1(1, 2))
    println("sum2的值：" + sum2(3, 4))
    println("sum3的值：" + sum3(5, 6))
    printSum1(7, 8)
    printSum2(9, 10)
    vars(1, 2, 3, 4, 5, 6, -5)
    println()
    println(lambdaSum(2, 6))
    printString()
    showAge()

    /*if (args.size < 2) {
        println("he is shorter than 2")
        println(args.size)
        return
    }


    var x: Int? = parseInt(args[1]);
    var y: Int? = parseInt(args[2]);
    if (x != null && y != null) {
        println("x*y=$x*$y")
    }*/

    println(getStringLength(123))
    println(getStringLength("123"))

    for (i in 1..4) {
        print(i)
    }
    println()
    for (i in 4..1) {
        print(i)
    }
    println()
    for (i in 1..4 step 2) {//从1到4.跨越两个步子，即为1,3
        print(i)
    }
    println()
    for (i in 4 downTo 1 step 2) {//从1到4倒过来,跨越2个步子 即为4,2
        print(i)
    }
    println()
    for (i in 1 until 10) { //从一开始不包括10
        print(i)
    }
    println()
    val d: Int = 1
    val a: Int = 2
    println(a === a)//===代表判断地址是否相等

    val b: Int = d
    val c: Int? = d
    println(b === c)
    println(b == c)

    println(a === a) // true，值相等，对象地址相等

    //经过了装箱，创建了两个不同的对象
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    //虽然经过了装箱，但是值是相等的，都是10000
    println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
    println(boxedA == anotherBoxedA) // true，值相等

    val text = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()
    println(text)


    val max = if (a > b) a else b
    var max1=if (a>b){
        println(a)
        a
    }else{
        println(b)
        b
    }

    var array = arrayOf(1,2,1,1,2,3,4,5,6)
    for ((index,value ) in array.withIndex()){
        println("array[$index]=$value")
    }
    var list= listOf("a","b","c")
    for (item in list.indices){
        println("list$item=${list.get(item)}")
    }
    println(reverse(-123))
}

fun reverse(x: Int): Int {
    var y:Int
    return try {
        if (x >= 0) {
            val s = x.toString()
            val stringBuilder = StringBuilder(s)
            val reverse = stringBuilder.reverse()
            val i=reverse.toString().toInt()
            i
        } else {
            y = -x
            val s = y.toString()
            val stringBuilder = StringBuilder(s)
            val reverse = stringBuilder.reverse()
            val i = reverse.toString().toInt()
            -i
        }
    } catch (e: NumberFormatException) {
        0
    }
}


