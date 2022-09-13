package com.example.picshare

import com.android.volley.RequestQueue
import com.example.picshare.domain.User

object Metadata {
    lateinit var thisUser: User
    const val serverURL = "picshare-server-tmwc5shc6q-lm.a.run.app/"
    lateinit var requests : RequestQueue
}