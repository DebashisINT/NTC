package com.trackerbreeze.features.viewAllOrder.interf

import com.trackerbreeze.app.domain.NewOrderGenderEntity
import com.trackerbreeze.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}