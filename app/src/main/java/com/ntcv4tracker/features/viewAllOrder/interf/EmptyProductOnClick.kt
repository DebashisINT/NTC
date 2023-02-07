package com.ntcv4tracker.features.viewAllOrder.interf

import com.ntcv4tracker.features.viewAllOrder.model.ProductOrder

interface EmptyProductOnClick {
    fun emptyProductOnCLick(emptyFound:Boolean)
    fun delProductOnCLick(isDel:Boolean)
}