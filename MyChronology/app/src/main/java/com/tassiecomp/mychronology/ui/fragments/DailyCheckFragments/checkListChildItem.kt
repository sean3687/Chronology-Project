package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.SubjectItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class checkListChildItem {
}

open class ChildItem( val dailyGrade: DailyGrade) : com.xwray.groupie.Item<com.xwray.groupie.GroupieViewHolder>(){


    override fun getLayout(): Int {
        return R.layout.main_subjectitemlist_child_item
    }

    override fun bind(viewHolder: com.xwray.groupie.GroupieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}