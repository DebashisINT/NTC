package com.ntcv4tracker.features.viewAllOrder.interf

import com.ntcv4tracker.app.domain.NewOrderGenderEntity
import com.ntcv4tracker.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}