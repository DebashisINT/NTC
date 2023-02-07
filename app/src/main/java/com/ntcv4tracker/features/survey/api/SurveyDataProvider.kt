package com.ntcv4tracker.features.survey.api

import com.ntcv4tracker.features.photoReg.api.GetUserListPhotoRegApi
import com.ntcv4tracker.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}