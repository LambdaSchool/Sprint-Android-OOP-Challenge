package com.example.aoe.viewModel

import com.google.gson.annotations.SerializedName

/*   "id": 0,
      "name": "string",
      "armyType": "string",
      "expansion": "string",
      "teamBonus": "string",*/
data class Unit(
    override var name: String,
    @SerializedName("armyType") val description: String,
    @SerializedName("expansion") val expansion: String,
    @SerializedName("teamBonus") val age: String
): AOEobject (){

    override fun info(): String = "This is the overridden name: $name, $description, as well as $expansion and $age"

    override fun toString(): String {
        return "This is the overridden name: $name, $description, as well as $expansion and $age"
    }

}