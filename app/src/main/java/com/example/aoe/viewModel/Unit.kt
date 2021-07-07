package com.example.aoe.viewModel

import android.text.style.LineHeightSpan
import android.text.style.MaskFilterSpan
import com.google.gson.annotations.SerializedName

/*   "id": 0,
      "name": "string",
      "armyType": "string",
      "expansion": "string",
      "teamBonus": "string",*/
data class Unit(
    val height: String,
    val mass: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("eye_color") val eyeColor: String
): AOEobject (){

    override fun info(): String = "This is the overridden name: $name, $hairColor, as well as $skinColor and $eyeColor"

    override fun toString(): String {
        return   "This is the overridden name: $name, $hairColor, as well as $skinColor and $eyeColor"
    }

}