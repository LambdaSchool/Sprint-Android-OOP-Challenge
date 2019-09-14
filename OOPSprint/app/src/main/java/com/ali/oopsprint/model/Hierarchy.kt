package com.ali.oopsprint.model

import java.io.Serializable

abstract class Hierarchy (open val id : Int, open val name: String,open val age:String,open val expansion:String,open var favorite: Boolean,open val index:Int):
    Serializable {
    abstract fun description():String
}
class Civilization(id:Int,name:String,val armyType:String,expansion: String,favorite: Boolean,index: Int):Hierarchy(id,name,armyType,expansion,favorite,index){
    override fun description(): String {
        return "Civilization :\n Name: $name\nAge: $age\nExpansion: $expansion\nArmyType: $armyType"
    }
}
class Unit(id:Int,name:String,age:String,expansion: String,favorite: Boolean,index: Int):Hierarchy(id,name,age,expansion,favorite,index){
    override fun description(): String {
        return "UNIT :\n Name: $name\nAge: $age\nExpansion: $expansion"
    }
}
class Structure(id:Int,name:String,age:String,expansion: String,favorite: Boolean,index: Int):Hierarchy(id,name,age,expansion,favorite,index){
    override fun description(): String {
        return "Structure :\n Name: $name\nAge: $age\nExpansion: $expansion"
    }
}
class Technology(id:Int,name:String,age:String,expansion: String,favorite: Boolean,index:Int):Hierarchy(id,name,age,expansion,favorite,index){
    override fun description(): String {
        return "Technology :\n Name: $name\nAge: $age\nExpansion: $expansion"
    }
}
