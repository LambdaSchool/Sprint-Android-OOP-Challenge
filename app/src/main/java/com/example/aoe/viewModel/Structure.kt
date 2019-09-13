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
    override var name: String,
    @SerializedName("army_type") val armyType: String,
    @SerializedName("expansion") val expansion: String,
    @SerializedName("team_bonus") val teamBonus: String
): AOEobject (){

    override fun info(): String = "This is the overridden name for structure: $name, $armyType, as well as $expansion and $teamBonus"

    override fun toString(): String {
        return "This is the overridden name for structure: $name, $armyType, as well as $expansion and $teamBonus"
    }

}