package com.example.aoe.viewModel

import java.io.Serializable

abstract class AOEobject (
    open val name: String? = null,
    open var id: Int = 0
) : Serializable{
    open fun info():String = "AOE Object!"

    override fun toString(): String {
        return "I am ovverriden fun for $name and and $id"
    }
}