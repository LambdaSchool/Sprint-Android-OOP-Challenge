package com.saucefan.stuff.sprint_oop_mdf.viewmodel

import com.saucefan.stuff.sprint_oop_mdf.model.*

object  ArrayListVehicles
     {
        val AoeArrayList = arrayListOf<AoeTypes>(
            Structures(1,"big place","dark age",500),
            Structures(2,"smallplace","new age",400),
            Technology(3,"catapult design","middle age","i dunno somethin"),
            Technology(4,"more catapults","late middle age","another descrip"),
            Civlizations(5,"egypt or whatever","big chariot army"),
            Civlizations(6,"byzantine","probably also chariots"),
            Units(7,"Chariots","way back","they got wheels and 1 horse power"),
            Units(8,"also chariots", "further back","only 1/2 horse power")

        )
        fun swapArrayFave(item:AoeTypes):String {
            for (i in 0 .. AoeArrayList.size-1) {
                if (item.name==AoeArrayList[i].name){
                    if (AoeArrayList[i].isFavorite){
                        AoeArrayList[i].isFavorite=false
                        return "${AoeArrayList[i].name} isfavorite is ${AoeArrayList[i].isFavorite}"
                    } else{
                        AoeArrayList[i].isFavorite=true
                        return "${AoeArrayList[i].name} isfavorite is ${AoeArrayList[i].isFavorite}"
                    }
                }
            }
            return "couldn't find it"
        }


        var ITEMS: MutableList<AoeTypes> = AoeArrayList








    }
