package com.wcisang.networking.util

import java.security.MessageDigest

class AuthApi {
    val timeStamp = System.currentTimeMillis()
    val apiPublicKey = "43b5f478ad3c8ff24ecd3ae3e19b53b7"
    val apiPrivateKey = "b190973b1bcfe75ffeefe1c827e8aa1e306559f2"
    val md5Hash : String
    get() {
        val code = timeStamp.toString() + apiPrivateKey + apiPublicKey
        val bytes = MessageDigest.getInstance("MD5").digest(code.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}