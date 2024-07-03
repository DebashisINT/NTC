package com.trackerbreeze.features.login.model.opportunitymodel

import com.trackerbreeze.app.domain.OpportunityStatusEntity
import com.trackerbreeze.app.domain.ProductListEntity
import com.trackerbreeze.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}