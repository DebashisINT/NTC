package com.ntcv4tracker.features.viewAllOrder.interf

import com.ntcv4tracker.app.domain.NewOrderGenderEntity
import com.ntcv4tracker.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}