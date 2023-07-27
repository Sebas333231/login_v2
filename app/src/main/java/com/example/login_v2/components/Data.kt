package com.example.login_v2.components

data class Data(
    val id: String?,
    val UserId: String,
    val displayName: String,
    val avatarUrl: String,
    val profession: String,
    val rool: String
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.UserId,
            "displayName" to this.displayName,
            "profession" to this.profession,
            "avatar_Url" to this.avatarUrl,
            "rool" to this.rool
        )
    }
}
