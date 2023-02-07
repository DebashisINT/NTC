package com.ntcv4tracker.features.lead.api

import com.ntcv4tracker.features.NewQuotation.api.GetQuotListRegRepository
import com.ntcv4tracker.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}