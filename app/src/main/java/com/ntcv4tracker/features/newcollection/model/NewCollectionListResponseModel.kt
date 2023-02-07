package com.ntcv4tracker.features.newcollection.model

import com.ntcv4tracker.app.domain.CollectionDetailsEntity
import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}