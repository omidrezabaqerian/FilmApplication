package ir.omidrezabagherian.filmapplication.ui.player

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import ir.omidrezabagherian.filmapplication.R
import ir.omidrezabagherian.filmapplication.databinding.FragmentPlayerBinding

@AndroidEntryPoint
class PlayerFragment : Fragment(R.layout.fragment_player) {

    private lateinit var fragmentPlayerBinding: FragmentPlayerBinding
    private lateinit var filmExoPlayer: ExoPlayer
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentPlayerBinding = FragmentPlayerBinding.bind(view)

        val link =
            "https://hajifirouz11.cdn.asset.aparat.com/aparat-video/f2066804872840b0be0812f243a207cb44872846-360p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjAyYmExZDE2N2M4YTg1MjZiMDE4ZDgzNzk1NGIxYjNiIiwiZXhwIjoxNjUyNzI0MzY0LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.ojmC2kdVEK-dsOMiqquSS5KjXerTzBFRg_US5bBTkU8"

        filmExoPlayer = ExoPlayer.Builder(view.context).build()
        val urlFilm = MediaItem.fromUri(link)
        filmExoPlayer.addMediaItem(urlFilm)
        fragmentPlayerBinding.exoPlayerViewPlayer.player = filmExoPlayer
        filmExoPlayer.prepare()
        filmExoPlayer.play()

        savedInstanceState?.getInt("TimeMediaItem")?.let {
            val seekTime = savedInstanceState.getLong("TimeSeekItem")
            filmExoPlayer.seekTo(it, seekTime)
            filmExoPlayer.play()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("TimeMediaItem", filmExoPlayer.currentMediaItemIndex)
        outState.putLong("TimeSeekItem", filmExoPlayer.currentPosition)
    }

    override fun onStop() {
        super.onStop()
        filmExoPlayer.release()
    }
}