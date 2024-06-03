package com.trackerbreeze.features.login.model.productlistmodel

import com.trackerbreeze.app.domain.ModelEntity
import com.trackerbreeze.app.domain.ProductListEntity
import com.trackerbreeze.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}