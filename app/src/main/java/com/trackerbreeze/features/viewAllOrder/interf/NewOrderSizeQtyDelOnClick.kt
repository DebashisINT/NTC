package com.trackerbreeze.features.viewAllOrder.interf

import com.trackerbreeze.app.domain.NewOrderGenderEntity
import com.trackerbreeze.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}