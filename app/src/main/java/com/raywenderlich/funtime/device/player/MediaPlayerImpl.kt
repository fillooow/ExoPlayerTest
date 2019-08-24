package com.raywenderlich.funtime.device.player

import android.content.Context
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

class MediaPlayerImpl : MediaPlayer {

    private lateinit var exoPlayer: ExoPlayer

    private lateinit var context: Context

    private fun initializePlayer(){

        // Render media from some stream
        val renderersFactory = DefaultRenderersFactory(context)

        // Select  tracks to be consumed by each of the player’s renderers.
        val trackerSelector = DefaultTrackSelector()

        // Controls the buffering of the media
        val loadControl = DefaultLoadControl()

        exoPlayer = ExoPlayerFactory.newSimpleInstance(

                renderersFactory, trackerSelector, loadControl
        )
    }
}