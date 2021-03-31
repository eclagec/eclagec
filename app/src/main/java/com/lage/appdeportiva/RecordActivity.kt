package com.lage.appdeportiva

import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    private var isRecording = false
    private val mediarecorder by lazy {
        MediaRecorder()
    }
    private lateinit var currentVideoFilePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        // Record
        record()

    }

    private fun previewSession() {
        val surfaceTexture = previewTextureView.surfaceTexture

    }

    private fun setupmediarecorder(){
        mediarecorder.apply{
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setVideoEncodingBitRate(1000000)
            setVideoFrameRate(30)
            setVideoSize(1920,1080)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            prepare()
        }
    }

    private fun stopMediaRecorder() {
        mediarecorder.apply{
            stop()
            reset()
        }
    }

    private fun record() {
        capturebutton.setOnClickListener{
            if(isRecording){
                isRecording = false
                startChronometer()
            } else{
                isRecording = true
                stopChronometer()
            }
        }
    }

    private fun startChronometer() {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.setTextColor(resources.getColor(android.R.color.holo_red_light,null))
        chronometer.start()
    }

    private fun stopChronometer() {
        chronometer.setTextColor(resources.getColor(android.R.color.white, null))
        chronometer.stop()
    }
}