package com.example.aoe.viewModel

import com.google.gson.annotations.SerializedName

/*   "id": 0,
      "name": "string",
      "description": "string",
      "expansion": "string",
      "age": "string",*/
data class Unit(
    override var name: String,
    @SerializedName("description") val description: String,
    @SerializedName("expansion") val expansion: String,
    @SerializedName("age") val age: String
): AOEobject (){

    override fun info(): String = "This is the overridden name: $name, $description, as well as $expansion and $age"

    override fun toString(): String {
        return "This is the overridden name: $name, $description, as well as $expansion and $age"
    }

}