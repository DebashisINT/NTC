package com.ntcv4tracker.features.viewAllOrder.interf

import com.ntcv4tracker.app.domain.NewOrderGenderEntity
import com.ntcv4tracker.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}