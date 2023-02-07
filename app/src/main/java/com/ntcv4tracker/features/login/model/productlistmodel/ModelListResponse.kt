package com.ntcv4tracker.features.login.model.productlistmodel

import com.ntcv4tracker.app.domain.ModelEntity
import com.ntcv4tracker.app.domain.ProductListEntity
import com.ntcv4tracker.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}