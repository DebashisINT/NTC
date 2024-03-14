package com.ntcv4tracker.app.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ntcv4tracker.app.AppConstant

@Entity(tableName = AppConstant.MODE_TEMPLATE)
data class ModeTemplateEntity (
    @PrimaryKey(autoGenerate = true) var sl_no: Int = 0,
    @ColumnInfo var mode_template_id:Int = 0,
    @ColumnInfo var mode_template_name:String = ""
)