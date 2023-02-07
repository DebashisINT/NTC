package com.ntcv4tracker.features.document.api

import com.ntcv4tracker.features.dymanicSection.api.DynamicApi
import com.ntcv4tracker.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}