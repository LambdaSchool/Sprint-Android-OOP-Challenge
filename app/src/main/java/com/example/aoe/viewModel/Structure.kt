package com.example.aoe.viewModel

import com.google.gson.annotations.SerializedName

/*
Civilization Response

Example Value
Model
{
  "id": 0,
  "name": "string",
  "expansion": "string",
  "army_type": "string",
  "unique_unit": "string",
  "unique_tech": "string",*/
data class Structure(
    val model: String,
    val manufacturer: String,
    @SerializedName("cost_in_credits") val costInCredits: String,
    val length: String
): AOEobject (){

    override fun info(): String = "This is the overridden name for structure: $name, $manufacturer, as well as $costInCredits and $length"

    override fun toString(): String {
        return "This is the overridden name for structure: $name, $manufacturer, as well as $costInCredits and $length"
    }

}