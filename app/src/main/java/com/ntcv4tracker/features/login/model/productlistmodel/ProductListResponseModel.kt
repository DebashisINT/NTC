package com.ntcv4tracker.features.login.model.productlistmodel

import com.ntcv4tracker.app.domain.ProductListEntity
import com.ntcv4tracker.base.BaseResponse

/**
 * Created by Saikat on 20-11-2018.
 */
class ProductListResponseModel : BaseResponse() {
    //var product_list: ArrayList<ProductListDataModel>? = null
    var product_list: ArrayList<ProductListEntity>? = null
}