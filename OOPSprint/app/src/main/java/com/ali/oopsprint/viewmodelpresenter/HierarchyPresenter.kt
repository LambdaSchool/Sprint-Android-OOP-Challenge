package com.ali.oopsprint.viewmodelpresenter

import com.ali.oopsprint.model.*
import com.ali.oopsprint.model.Unit

class HierarchyPresenter {
    object HierarchyList {

        var hierarchy= ArrayList<Hierarchy>()
    }
    companion object{
    var civilization_1=Civilization(1,"Aztecs","Infantry and Monk","The Conquerors",false,HierarchyList.hierarchy.size)
    var civilization_2=Civilization(4,"Celts","Infantry","Age Of Kings",false,HierarchyList.hierarchy.size)
    var unit_1=Unit(9,"Eagle Warrior","Dark","The Conquerors",true,HierarchyList.hierarchy.size)
    var unit_2=Unit(6,"Hand Cannoneer","Imperial","Age of Kings",false,HierarchyList.hierarchy.size)
    var structure_1= Structure(10,"Archery Range","Feudal","Age of Kings",false,HierarchyList.hierarchy.size)
    var structure_2=Structure(12,"Blacksmith","Feudal","Age of Kings",false,HierarchyList.hierarchy.size)
    var technology_1=Technology(14,"Halberdier","Imperial","The Conquerors",false,HierarchyList.hierarchy.size)
    var technology_2=Technology(18,"Padded Archer Armor","Feudal","Age of Kings",false,HierarchyList.hierarchy.size)
        }

}
