package com.example.oopsprintchallenge.viewModel

import com.example.oopsprintchallenge.model.*

object TempItemList {
    /*Step 5

    Create your ViewModel/Presenter.

    You don't have to use Data Binding for this part, just create a ViewModel/Presenter that will hold your data.
    Convert the data you collected in 2 into a list of API objects held in the ViewModel/Presenter
    Replace all the DummyItem object references from the project template with the objects you have created.
    You'll have to create a instance of the ViewModel/Presenter in your ItemListActivity to do this.

*/

    //Deleting this once Api is working
    val empireList: MutableList<EmpireRepository> = ArrayList()
    val empireMap: MutableMap<String?/*<- this nullable destroy's lives*/, EmpireRepository> = HashMap()


    init {

        empireList.add(Civilization(1,"Aztecs", "The Conquerors",
            false ))
        empireList.add(Civilization(2,"Britons", "Age of Kings",
            true ))
        empireList.add(Units(1, "Archer", "Age of Kings", true,
            "Quick and light. Weak at close range; excels at battle from distance"))
        empireList.add(Units(2, "Crossbowman", "Age of Kings", false,
            "Upgraded archer"))
        empireList.add(Structure(1, "Barracks", "Age of kings", false, "Dark"))
        empireList.add(Structure(2, "Dock", "Age of kings", false, "Dark"))
        empireList.add(Technology(2, "Thumb Ring", "Age of kings", false, "Faster reload time (10-20%) and 100% accuracy" ))
        empireList.add(Technology(3, "Arbalest", "Age of kings", false, "Upgrade to Arbalest"))

        //spent hours wondering why my fragment wasn't showing any thing UGGGGGH I am so confused
        empireMap[empireList[0].name] = empireList[0]
        empireMap[empireList[1].name] = empireList[1]
        empireMap[empireList[2].name] = empireList[2]
        empireMap[empireList[3].name] = empireList[3]
        empireMap[empireList[4].name] = empireList[4]
        empireMap[empireList[5].name] = empireList[5]
        empireMap[empireList[6].name] = empireList[6]
        empireMap[empireList[7].name] = empireList[7]


    }

}