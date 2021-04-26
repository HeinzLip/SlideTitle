package com.why.slidetitle

import java.lang.StringBuilder

/**
 * @ClassName: Tools
 * @Description: java类作用描述
 * @Author: wanghaiyang91
 * @Date: 4/25/21 5:55 PM
 */
fun IntRange.randomStr(): String{
    val strs = mutableListOf<Char>().apply { "123456789zxcvbnmasdfghjklqwertyuiop".forEach { this.add(it) } }
    val str = StringBuilder().apply {  (0..this@randomStr.random()).onEach { append(strs.random()) } }
    return str.toString()
}