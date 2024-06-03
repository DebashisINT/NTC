package com.trackerbreeze.features.viewAllOrder.model

import com.trackerbreeze.app.domain.NewOrderColorEntity
import com.trackerbreeze.app.domain.NewOrderGenderEntity
import com.trackerbreeze.app.domain.NewOrderProductEntity
import com.trackerbreeze.app.domain.NewOrderSizeEntity
import com.trackerbreeze.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

