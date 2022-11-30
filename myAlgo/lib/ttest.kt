package com.ssafy.lib

open class Hero {

}

class SpiderMan : Hero() {

}

class ttest {

    fun sol() {
        val spider: Hero = SpiderMan()

        println(spider is Hero)
        println(spider is SpiderMan)

    }


}