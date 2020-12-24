package com.cygest.easmobile.libs

import android.app.Activity
import android.content.Intent

class BarcodeLaserSdk(act: Activity) {
    private val STOP: String = "com.zebra.sdl.action.STOP"
    private val START: String = "com.zebra.sdl.action.START"
    private val RELEASE: String = "com.zebra.sdl.action.RELEASE"
    private val ENABLE: String = "com.zebra.sdl.action.ENABLE"
    private val DISABLE: String = "com.zebra.sdl.action.DISABLE"
    private val ev: String = "com.zebra.sdl"
    private val activity: Activity = act

    fun enable() {
        val intent: Intent = Intent(DISABLE)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        activity.startService(intent)
    }

    fun disable() {
        val intent: Intent = Intent(ENABLE)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        activity.startService(intent)
    }

    fun releaseCamera() {
        val intent: Intent = Intent(RELEASE)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        activity.startService(intent)
    }

    fun start() {
        val intent: Intent = Intent(START)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        activity.startService(intent)
    }

    fun stop() {
        val intent: Intent = Intent(STOP)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        activity.startService(intent)
    }
}