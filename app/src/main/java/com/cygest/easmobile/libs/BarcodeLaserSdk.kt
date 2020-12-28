package com.cygest.easmobile.libs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class BarcodeLaserSdk(ctx: Context) {
    private val STOP: String = "com.zebra.sdl.action.STOP"
    private val START: String = "com.zebra.sdl.action.START"
    private val RELEASE: String = "com.zebra.sdl.action.RELEASE"
    private val ENABLE: String = "com.zebra.sdl.action.ENABLE"
    private val DISABLE: String = "com.zebra.sdl.action.DISABLE"
    private val COMPLETE: String = "com.zebra.sdl.action.SCAN_COMPLETE"
    private val RELEASED: String = "com.zebra.sdl.action.RELEASED"
    private val ev: String = "com.zebra.sdl"
    private val context: Context = ctx
    private var intentFilter: IntentFilter? = null
    private var mCameraReleasedIntentFilter: IntentFilter? = null

    companion object {
        /**
         * @param [context] of activity
         * @return A singleton that will allow to control the laser
         */
        @Synchronized
        fun getInstance(context: Context): BarcodeLaserSdk? {
            if (INSTANCE == null) {
                INSTANCE = BarcodeLaserSdk(context)
            }
            return INSTANCE
        }

        private var INSTANCE: BarcodeLaserSdk? = null
        private var mBarcodeInterface: BarcodeResultInterface? = null;
    }

    init {
        val scanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val inputString: String = intent?.extras?.getString("com.zebra.sdl.extra.CONTENT") ?: ""
                if(mBarcodeInterface != null) {
                    mBarcodeInterface?.getResult(inputString);
                }
            }
        }

        if (intentFilter == null) {
            intentFilter = IntentFilter(COMPLETE);
            context.registerReceiver(scanReceiver, intentFilter);
        }

        val cameraReleasedBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if(mBarcodeInterface != null) {
                    mBarcodeInterface?.onCameraReleased();
                }
            }
        }

        if(mCameraReleasedIntentFilter == null) {
            mCameraReleasedIntentFilter = IntentFilter(RELEASED);
            context.registerReceiver(cameraReleasedBroadcastReceiver, mCameraReleasedIntentFilter);
        }
    }

    /**
     * @param [barcodeResultInterface]
     * that will allow to retrieve events and the scanned results.
     * This function set current barcodeResultInterface
     */
    fun setCurrentListener(barcodeResultInterface: BarcodeResultInterface){
        mBarcodeInterface = barcodeResultInterface;
    }

    fun enable() {
        val intent: Intent = Intent(DISABLE)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        context.startService(intent)
    }

    fun disable() {
        val intent: Intent = Intent(ENABLE)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        context.startService(intent)
    }

    fun releaseCamera() {
        val intent: Intent = Intent(RELEASE)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        context.startService(intent)
    }

    fun start() {
        val intent: Intent = Intent(START)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        context.startService(intent)
    }

    fun stop() {
        val intent: Intent = Intent(STOP)
        intent.setPackage(ev)
        intent.flags = Intent.FLAG_FROM_BACKGROUND
        context.startService(intent)
    }
}