package com.holiday

//data class Holiday(
//    val date: String,
//    val localName: String,
//    val name: String,
//    val countryCode: String,
//    val fixed: Boolean,
//    val global: Boolean,
//    val counties: List<String>?,
//    val launchYear: Int?,
//    val type: String
//)


class Holiday(private var date: String, private var name: String, private var type: String) {

    fun getDate(): String {
        return date
    }

    fun getName(): String {
        return name
    }

    fun getType(): String {
        return type
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setType(type: String) {
        this.type = type
    }
}