package com.example.aoe.viewModel

import com.google.gson.annotations.SerializedName

/*  "structures": [
    {
      "id": 0,
      "name": "string",
      "description": "string",
      "expansion": "string",
      "age": "string"*/
data class Structure(
    override var name: String,
    @SerializedName("description") val description: String,
    @SerializedName("expansion") val expansion: String,
    @SerializedName("age") val age: String
): AOEobject (){

    override fun info(): String = "This is the overridden name for structure: $name, $description, as well as $expansion and $age"

    override fun toString(): String {
        return "This is the overridden name for structure: $name, $description, as well as $expansion and $age"
    }

}