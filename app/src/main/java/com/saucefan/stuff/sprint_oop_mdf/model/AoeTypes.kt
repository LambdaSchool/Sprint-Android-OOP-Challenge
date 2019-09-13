package com.saucefan.stuff.sprint_oop_mdf.model

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
    )

class Civlizations(
    id: Int,
    name: String,
    val army_type: String
) : AoeTypes(id, name)

class Structures(
    id: Int,
    name: String,
    age: String
) : AoeTypes(id, name, false, age)

class Units(
    id: Int,
    name: String,
    age: String,
    description: String
) : AoeTypes(id, name, false, age)

class Technology(
    id: Int,
    name: String,
    age: String,
    description: String
) : AoeTypes(id, name, false, age)






