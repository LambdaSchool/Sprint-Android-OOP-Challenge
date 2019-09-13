package com.example.oopsprintchallenge.model

import android.nfc.tech.TagTechnology

/*Build your model classes. The API we are using has endpoints for civilizations, units, structures, and technologies.
We want to create a list containing all the different API objects,
and we want to be able to display interesting data in our RecyclerView.
Examine the data you copied in step 2.
to figure out how to extract attributes from each class to construct your class hierarchy.
We want to be able to put objects of each type in the same list structure.
You don't have to use every single attribute;
it may be easier to ignore any attributes that are themselves JSON Objects or JSON Arrays (like "cost", for example),
but that is up to you.

Create a class hierarchy to model the API Objects.

In your class hierarchy, create description function that will give us information specific to each type of object,
but we don't want to have to check the type (in other words, we want to use polymorphism).
In addition to the attributes provided in the data set, add a Boolean variable that tracks some state -
choose something that interests you, perhaps isFavorite or isInUse - about the API objects.
Every type of object should have this state (where is the best place to put it?).
You can provide a default value of false.
 */

//To reach for example Wood from an activity
// reference to the data class Cost must be in CivilizationRepository(val cost: Cost)

abstract class CivilizationRepository(
    val id: Int?,
    val name: String?,
    val expansion: String?,
    var isFavorite: Boolean = false
) {
    abstract fun getDetails(): String
}

/*
from website
Wood	integer
How much wood it costs
Food	integer
How much food it costs
Stone	integer
How much stone it costs
Gold	integer
How much gold it costs
*/

data class Cost(
    val Wood: Int?,
    val Food: Int?,
    val Stone: Int?,
    val Gold: Int?
)


class Civilization(
    id: Int?,
    name: String?,
    expansion: String?,
    isFavorite: Boolean = false,
    private val army_type: String?,
    private val unique_unit: MutableList<String>?,
    private val unique_tech: MutableList<String>?,
    private val team_bonus: String?,
    private val civilization_bonus: MutableList<String>?
) : CivilizationRepository(id, name, expansion, isFavorite) {
    override fun getDetails(): String {
        return "ID: $id " +
                "Name: $name " +
                "Expansion: $expansion " +
                "Favorite True or False: $isFavorite " +
                "Army Type: $army_type " +
                "Unique unit: $unique_unit " +
                "Unique tech: $unique_tech " +
                "Team bonus: $team_bonus " +
                "Civilization bonus: $civilization_bonus "

    }
}

class Unit(
    id: Int?,
    name: String?,
    expansion: String?,
    isFavorite: Boolean = false,
    private val description: String?,
    private val age: String?,
    private val created_in: String?,
    private val cost: Cost?,
    private val build_time: Int?,
    private val reload_time: Int?,    //number?
    private val attack_delay: Int?,    //number?
    private val movement_rate: Int?,    //number?
    private val line_of_sight: Int?,
    private val hit_points: Int?,
    private val range: String?,
    private val attack: Int?,
    private val armor: String?,
    private val attack_bonus: MutableList<String>?,
    private val armor_bonus: MutableList<String>?,
    private val search_radius: Int?,
    private val accuracy: String?,
    private val blast_radius: Int? //number?
) : CivilizationRepository(id, name, expansion, isFavorite) {
    override fun getDetails(): String {
        return "ID: $id " +
                "Name: $name " +
                "Expansion: $expansion " +
                "Favorite True or False: $isFavorite " +
                "Description: $description " +
                "Age: $age " +
                "Created: $created_in " +
                "Cost: $cost " +
                "Build: $build_time " +
                "Reload Time: $reload_time " +
                "Attack Delay: $attack_delay " +
                "Movement Rate: $movement_rate " +
                "Line Of Sight: $line_of_sight " +
                "Hit Points: $hit_points " +
                "Range: $range " +
                "Attack: $attack " +
                "Armor: $armor " +
                "Attack Bonus: $attack_bonus " +
                "Armor Bonus: $armor_bonus " +
                "Search Radius: $search_radius " +
                "Accuracy: $accuracy " +
                "Blast Radius: $blast_radius "

    }
}

class Structure(
    id: Int?,
    name: String?,
    expansion: String?,
    isFavorite: Boolean = false,
    private val description: String?,
    private val age: String?,
    private val cost: Cost?,
    private val build_time: Int?,
    private val hit_points: Int?,
    private val line_of_sight: Int?,
    private val armor: String?,
    private val range: String?,
    private val reload_time: Int?,    //number?
    private val attack: Int?,
    private val special: MutableList<String>?
) : CivilizationRepository(id, name, expansion, isFavorite) {
    override fun getDetails(): String {

        return "ID: $id " +
                "Name: $name " +
                "Expansion: $expansion " +
                "Favorite True or False: $isFavorite " +
                "Description $description " +
                "Age: $age " +
                "Cost: $cost " +
                "Build Time: $build_time " +
                "Hit Points: $hit_points " +
                "Line Of Sight: $line_of_sight " +
                "Armor: $armor " +
                "Range: $range " +
                "Reload Time: $reload_time " +
                "Attack: $attack " +
                "Special: $special"
    }
}

class Technology(
    id: Int?,
    name: String?,
    expansion: String?,
    isFavorite: Boolean = false,
    private val description: String?,
    private val age: String?,
    private val develops_in: String?,
    private val cost: Cost?,
    private val build_time: Int?,
    private val applies_to: MutableList<String>?
) : CivilizationRepository(id, name, expansion, isFavorite) {
    override fun getDetails(): String {
        return "ID: $id " +
                "Name: $name " +
                "Expansion: $expansion " +
                "Favorite True or False: $isFavorite " +
                "Description: $description " +
                "Age: $age " +
                "Develops In: $develops_in " +
                "Cost: $cost " +
                "Build Time: $build_time " +
                "Applies To: $applies_to "

    }
}


