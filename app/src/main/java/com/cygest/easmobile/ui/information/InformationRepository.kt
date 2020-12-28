package com.cygest.easmobile.ui.information

import com.cygest.easmobile.libs.HttpBuilder
import com.cygest.easmobile.libs.WebService

class InformationRepository {
    suspend fun getInformation(cb: String): Any {
        return HttpBuilder().build(WebService::class.java).getInformation(cb)
    }
}