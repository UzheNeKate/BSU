package com.example.weather

import java.io.Serializable


class Weather : Serializable {
    var name: String? = null
    var main: Main? = null
    var wind: Wind? = null

    inner class Main : Serializable {
        var temp: Double? = null
        var humidity: Double? = null
    }

    inner class Wind : Serializable {
        var speed = 0.0
    }
}
