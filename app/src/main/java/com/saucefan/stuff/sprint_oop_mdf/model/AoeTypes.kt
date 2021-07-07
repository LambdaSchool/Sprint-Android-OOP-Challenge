package com.saucefan.stuff.sprint_oop_mdf.model


import java.io.Serializable

/*
*
* TODO: change maping type from id to the name as it is much more likely to be unique, leave for now as this change makes me nervous
*
*
* */


/*
so we know
everything has an
ID:Int
name:String
 and we'll also set a boolean var isFavorite for later and default to false

civilizations seem pretty oddball compared to the others
we'll leave isFavorite out as a constructor cause that shouldn't need to be gotten from the initial retrieval of info but instead set by user
civilizations don't have ages but the other two do, i will set them

for the moment i'm not gonna dive that much further down into the data stucture as i want to finish this sprint somewhere close to on time...
TODO:come back and incorporate more data
*/

abstract class AoeTypes(
    val id: Int? = null,
    val name: String? = null,
    var isFavorite: Boolean = false,

//civilizations don't have this
    val age: String? = null
    ) : Serializable {
    open fun show():String {
        return "$name, is at id:$id and is it a favorite? $isFavorite"
    }
}

class Civlizations(
    id: Int,
    name: String,
    val army_type: String) : AoeTypes(id, name) {
   override fun show():String {
       return "$name.  Army type:$army_type, $isFavorite/$id/C"
    }
}

class Structures(
    id: Int,
    name: String,
    age: String,
    val build_time:Int
) : AoeTypes(id, name, false, age) {
    override fun show():String {
        return "$name.   $age.  build time: ${build_time.toString()} . $isFavorite/$id/S "
    }
}


class Units(
    id: Int,
    name: String,
    age: String,
    val description: String,
    val hit_points:String
) : AoeTypes(id, name, false, age) {
    override fun show():String {
        return "$name.   $age.  $description. hit_points: $hit_points $isFavorite/$id/U"
    }
}

class Technology(
    id: Int,
    name: String,
    age: String,
    val description: String
) : AoeTypes(id, name, false, age) {
    override fun show():String {
        return "$name.   $age.  $description. $isFavorite/$id/T "
    }
}






