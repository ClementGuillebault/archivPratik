package com.cygest.easmobile

open class ResponseFromAPI {
    var IsOk: Boolean = false
    var Message: String = ""
    var Information: String = ""
}

class ResponseError: ResponseFromAPI() {
    var Error: String = ""
}

class ResponseSuccess: ResponseFromAPI() {
    var IsArchive: Boolean = false
    var DataList: Any? = null
    var Data: Any? = null
    var Criteria: Any? = null
    var Emplacement: Any? = null
}