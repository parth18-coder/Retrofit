package com.example.retrofit.fetchingReqData

import com.example.retrofit.data.remote.ListedDataClass

class TodayData() {
    fun todayData(dataResponse: ListedDataClass?): TodayDataType {

        return TodayDataType(
            dataResponse?.todayClicks,
            dataResponse?.topSource,
            dataResponse?.topLocation
        )

    }
}