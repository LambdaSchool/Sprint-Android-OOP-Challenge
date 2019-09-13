package com.ali.oopsprint.model

import java.io.Serializable

abstract class Hierarchy (open val id : Int, open val name: String,open val age:String,open val expansion:String,open var favorite: Boolean,open val index:Int):
    Serializable {
    abstract fun description():String
}
class Civilization(id:Int,name:String,val armyType:String,expansion: String,favorite: Boolean,index: Int):Hierarchy(id,name,armyType,expansion,favorite,index){
    override fun description(): String {
        return "Civilization : $id,$name,$age,$expansion$armyType"
    }
}
class Unit(id:Int,name:String,age:String,expansion: String,favorite: Boolean,index: Int):Hierarchy(id,name,age,expansion,favorite,index){
    override fun description(): String {
        return "UNIT : $id,$name,$age,$expansion"
    }
}
class Structure(id:Int,name:String,age:String,expansion: String,favorite: Boolean,index: Int):Hierarchy(id,name,age,expansion,favorite,index){
    override fun description(): String {
        return "Structure : $id,$name,$age,$expansion"
    }
}
class Technology(id:Int,name:String,age:String,expansion: String,favorite: Boolean,index:Int):Hierarchy(id,name,age,expansion,favorite,index){
    override fun description(): String {
        return "Technology : $id,$name,$age,$expansion"
    }
}
