package com.wufanfirstkotlin.yufalianxi


/*
* 练习kotlin方法调用
* */


fun sum1(a: Int, b: Int): Int { //a,b 输入参数 a+b为返回值
    return a + b
}

fun sum2(a: Int, b: Int) = a + b //由系统自动判断返回值

public fun sum3(a: Int, b: Int): Int = a + b //public的方法必须申明返回值类型

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



fun showage(){
    val age:String?= null//类型后面加？代表可以为null
    //val ages = age!!.toInt() //如果age为null ，则报空指针异常
    val ages1= age?.toInt() //如果age 为null，则打印 null
    val ages2= age?.toInt() ?: -1 //如果age为null ,则打印 -1
    println(age)
    //println(ages)
    println(ages1)
    println(ages2)
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
    showage()

}


