package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName ="report"
)
//dfasdf
data class Report(
    @PrimaryKey var dayDate: String,
    var dayScore: String,
    var month:String,
    var quarter: String

) {

}