package com.taylorgameco.monscanner

import android.os.FileUtils
import rita.RiTa
import java.io.File
import java.io.InputStream
import kotlin.random.Random

public class Mon(val p: String, val animals: List<String>) {
    val product : String = RiTa.singularize(p).toLowerCase()
    val animal : String = getAnimal(product).toLowerCase()
    val monName : String = nameMon(product,animal)


    fun getAnimal(p: String): String {
        var rhymes = mutableListOf<String>()
        var allits = mutableListOf<String>()
        var a : String = ""

         animals.forEach {
             if (RiTa.isRhyme(it,p)) {
                 rhymes.add(it)
             }
             if (it.first().toLowerCase().equals(p.first().toLowerCase())) {
                 allits.add(it)
             }
         }

        if (rhymes.size>0) {
            a = rhymes[(0 until rhymes.size).random()]
        } else if (allits.size>0) {
            a = allits[(0 until allits.size).random()]
        } else {
            a = animals[(0 until animals.size).random()]
        }
        println(a)
        return a
    }

    fun nameMon(p: String, a: String): String {

        var pSyll = RiTa.syllables(p)
        var aSyll = RiTa.syllables(a)

        var prodNumSyll = pSyll.length-pSyll.replace("/","").length+1
        var animNumSyll = aSyll.length-aSyll.replace("/","").length+1

        var partOne : String = ""
        var partTwo : String = ""

        var pSyllables : List<String> = pSyll.split("/")
        var aSyllables : List<String> = aSyll.split("/")

        var splitIndex = 0

        if (p.contains(" ")) {
            partOne = p.substring(0,p.indexOf(" "))
            if (animNumSyll <3) {
                partOne= "$partOne "
            }
        } else if (prodNumSyll <3) {
            partOne = p
        } else {
            var s = pSyllables[(pSyllables.size)/2+1].first()
            if (p.contains(s)) {
                splitIndex = p.indexOf(s)
                if (splitIndex == 1) {
                    if (p.substring(2,p.length).contains(s)) {
                        splitIndex = p.substring(2,p.length).indexOf(s)
                    } else {
                        splitIndex = p.length/2
                    }
                }
            } else {
                splitIndex = p.length/2
            }
            partOne = p.substring(0,splitIndex)

        }

        if (animNumSyll <3) {
            partTwo = a
        } else {
            var s = aSyllables[(aSyllables.size)/2].first()
            if (a.contains(s)) {
                splitIndex = a.indexOf(s)
                if (splitIndex == 1) {
                    if (a.substring(2,a.length).contains(s)) {
                        splitIndex = a.substring(2,a.length).indexOf(s)
                    } else {
                        splitIndex = a.length/2
                    }
                }
            } else {
                splitIndex = a.length/2
            }

            partTwo = a.substring(splitIndex,a.length)

        }

        var monName = partOne + partTwo
        println(monName)
        return monName

    }

}