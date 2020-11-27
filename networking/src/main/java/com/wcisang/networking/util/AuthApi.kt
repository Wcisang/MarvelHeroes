package com.wcisang.networking.util

import java.security.MessageDigest

class AuthApi {
    val timeStamp = System.currentTimeMillis()
    val apiPublicKey = "8beb81223c818db8df92faff86495494"
    private val apiPrivateKey = "78d83a1d79ed2c274c58a706010a472f94c4dfcf"
    val md5Hash: String
        get() {
            val code = timeStamp.toString() + apiPrivateKey + apiPublicKey
            val bytes = MessageDigest.getInstance("MD5").digest(code.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }
}