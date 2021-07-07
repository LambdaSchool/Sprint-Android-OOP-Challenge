package com.saucefan.stuff.sprint_oop_mdf.viewmodel

import android.content.Context
import android.widget.Toast
import com.saucefan.stuff.sprint_oop_mdf.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

object  AoeRepository {
    var AoeArrayList = mutableListOf<AoeTypes>()
    /*           val AoeArrayList = mutableListOf(
            Structures(1,"big place","dark age",500),
            Structures(2,"smallplace","new age",400),
            Technology(3,"catapult design","middle age","i dunno somethin"),
            Technology(4,"more catapults","late middle age","another descrip"),
            Civlizations(5,"egypt or whatever","big chariot army"),
            Civlizations(6,"byzantine","probably also chariots"),
            Units(7,"Chariots","way back","they got wheels and 1 horse power"),
            Units(8,"also chariots", "further back","only 1/2 horse power")

        )*/
    fun swapArrayFave(item: AoeTypes): String {
        for (i in 0 until AoeArrayList.size) {
            if (item.name == AoeArrayList[i].name) {
                if (AoeArrayList[i].isFavorite) {
                    AoeArrayList[i].isFavorite = false
                    return "${AoeArrayList[i].name} isfavorite is ${AoeArrayList[i].isFavorite}"
                } else {
                    AoeArrayList[i].isFavorite = true
                    return "${AoeArrayList[i].name} isfavorite is ${AoeArrayList[i].isFavorite}"
                }
            }
        }
        return "couldn't find it"
    }

    //types:
    // civilizations


    fun makeAoeList(number: Int, context: Context) {

        var apiInterface = ApiInterface.Factory.create()
        for (i in 1 until number) {
            apiInterface.getCiv(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Civlizations> {
                    override fun onFailure(call: Call<Civlizations>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<Civlizations>,
                        response: Response<Civlizations>
                    ) {
                        val newciv: Civlizations? = response.body()
                        if (newciv != null) {
                            AoeArrayList.add(newciv)
                        }
                    }
                })
        }
        for (i in 1 until number) {
            apiInterface.getUnit(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Units> {
                    override fun onFailure(call: Call<Units>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(context, response, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<Units>,
                        response: Response<Units>
                    ) {
                        val newunit: Units? = response.body()
                        if (newunit != null) {
                            AoeArrayList.add(newunit)
                        }
                    }
                })
        }

        for (i in 1 until number) {
            apiInterface.getTech(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Technology> {
                    override fun onFailure(call: Call<Technology>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(context, response, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<Technology>,
                        response: Response<Technology>
                    ) {
                        val newtech: Technology? = response.body()
                        if (newtech != null) {
                            AoeArrayList.add(newtech)
                        }
                    }
                })
        }

        for (i in 1 until number) {
            apiInterface.getStructure(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Structures> {
                    override fun onFailure(call: Call<Structures>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(context, response, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<Structures>,
                        response: Response<Structures>
                    ) {
                        val newstruc: Structures? = response.body()
                        if (newstruc != null) {
                            AoeArrayList.add(newstruc)
                        }
                    }
                })
        }
    }
}

