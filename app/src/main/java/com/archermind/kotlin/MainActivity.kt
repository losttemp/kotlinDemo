package com.archermind.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //var是自定义变量，val是自定义常量，相当于java当中的fina
    var a: Int = 2
    var b: Double = 1.0
    var c: Char = '1'
    var d: Boolean = false
    var name = "name"
    var page: String = "nn"
    val TAG: String = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MainActivity::class.java)
    }

    //“:”被广泛用于变量类型的定义
    /*定义函数的参数和返回值
    * */

    fun funa(str: String): String {
        var strhello = "hello"
        strhello += str
        return strhello
    }

    interface IinterfaceA {
        fun A(str: String): String
    }


    class KClassA() : IinterfaceA {
        override fun A(str: String): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            val name = "zhangsan"
            val age = 25
            val s = "${name}今年${age}岁"
            test(s1= 22, s3 = "s3")
            test1("1","2",s2 = "hh")
            return s
        }

        //对Kotlin函数中的某个参数可以用“=”号指定其默认值，调用函数方法时可不不传这个参数，但其他参数需要用“=”号指定
        fun test(s1: Int, s2: String = "s2", s3: String) {

        }

        /**
         * 可变参数值的话，需要用关键字vararg来定义。这里需要注意的是，一个函数仅能有一个可变参数。该可变参数不一定得是最后一个参数，但当这种情况下时，调用该方法，需要给其他未指明具体值的参数传值。
         */
        fun test1(vararg s1: String, s2: String) {}
    }


}
