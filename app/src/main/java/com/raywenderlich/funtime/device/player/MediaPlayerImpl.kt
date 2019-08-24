package com.raywenderlich.funtime.device.player

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.raywenderlich.funtime.R

class MediaPlayerImpl : MediaPlayer {

    private lateinit var exoPlayer: ExoPlayer

    private lateinit var context: Context


    override fun play(url: String) {

        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))

        /**
         * Every peace of media is represented by a [MediaSource], so first step is creating
         * a MediaSource.
         *
         * Data source - a component from which streams of data can be read.
         *
         * [DefaultExtractorsFactory] - returns arrays of the extractors for video formats (e.g. mp4)
         *
         * [ExtractorMediaSource.Factory.createMediaSource] - takes the uri of the media
         */
        val mediaSource = ExtractorMediaSource
                .Factory(DefaultDataSourceFactory(context, userAgent))
                .setExtractorsFactory(DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(url))

        // Prepares player to play the provided media source
        exoPlayer.prepare(mediaSource)

        // Play video when it's ready
        // Also can be used for pausing and resuming video
        exoPlayer.playWhenReady = true
    }


    override fun getPlayerImpl(context: Context): ExoPlayer {

        this.context = context

        initializePlayer()

        return exoPlayer
    }

    private fun initializePlayer() {

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