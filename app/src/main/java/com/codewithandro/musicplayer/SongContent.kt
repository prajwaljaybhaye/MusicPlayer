package com.codewithandro.musicplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class SongContent : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var songTime : Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_song_content)


        //buttons
        val songLoop = findViewById<ImageView>(R.id.loopBtn)
        val preSong = findViewById<ImageView>(R.id.preBtn)
        val playSong = findViewById<ImageView>(R.id.playBtn)
        val nextSong = findViewById<ImageView>(R.id.nextBtn)
        val musicStock = findViewById<ImageView>(R.id.musicQueue)

        val songSeekBar = findViewById<SeekBar>(R.id.seekBar)

        //resive the data in the prious
        val songImg = intent.getIntExtra("Img",R.drawable.outline_headset_24)
        val songName = intent.getStringExtra("Name")
        val songDes = intent.getStringExtra("Descrption")
        var songs = intent.getIntExtra("song",R.raw.main)



        //featch the xml ids
        val songImgId = findViewById<ImageView>(R.id.songImgMain)
        val imgMain = findViewById<LinearLayout>(R.id.main)
        val songNameId  = findViewById<TextView>(R.id.songTitleMain)
        val songDesId = findViewById<TextView>(R.id.songDesMain)
        val songRate = findViewById<RatingBar>(R.id.songRate)

        //set the xml
        songImgId.setImageResource(songImg)
        songNameId.text = songName
        songDesId.text = songDes

        //create method pass the context and song
        mediaPlayer = MediaPlayer.create(this,songs)//pass the songs on by one
        mediaPlayer.setVolume(1f,1f)
        songTime = mediaPlayer.duration

        // featch the xml buttons


        //time show
        val mintSong = findViewById<TextView>(R.id.mint)
        val secSong = findViewById<TextView>(R.id.secd)

        //songLoop Function
        songLoopFun(songLoop)//calling
        //playSong Function
        playSongFun(playSong)//calling
        //seekBar Function
        seekBarSetup(songSeekBar,mintSong,secSong)//calling
        }

    //seekBar Start
    private fun seekBarSetup(seekBar: SeekBar?, mintSong: TextView, secSong: TextView) {
        //seekBar Drag
        seekBar?.max = songTime
        seekBar?.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                   if(fromUser){
                       mediaPlayer.seekTo(progress)
                   }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    //no need
                     }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    //no need
                }

            })
        //seekBar Drag End

        //Automatic Change The SeekBar
        var handler = Handler()
        handler.postDelayed(object : Runnable{

            override fun run() {
                try {
                    seekBar?.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this,500) // 0.5 sec run seekBar
                    val currSec = mediaPlayer.currentPosition/1000 //sec
                    val currMin = currSec/60

                    mintSong.text = "0"+currMin.toString()
                    secSong.text = ":"+currSec.toString()

                }catch (e : java.lang.Exception){
                    seekBar?.progress = 0
                }
            }
        },0)
        }
    //seekBar End


    //playSong Function Start
    private fun playSongFun(playSong: ImageView?) {
        var play = true
        playSong?.setOnClickListener{
            if(play){
                mediaPlayer.start()
                playSong.setImageResource(R.drawable.baseline_pause_circle_outline_24)
                play = false
            }else{
                mediaPlayer.pause()
                playSong.setImageResource(R.drawable.outline_play_circle_24)
                play = true
            }
        }
    }
    //playSong Function End

    //songLoop Function Start
    private fun songLoopFun(songLoop: ImageView?) {
        var singleSong = true
        songLoop?.setOnClickListener{
            if(singleSong){
                mediaPlayer.isLooping = true
                Toast.makeText(applicationContext,"Song Loop On",Toast.LENGTH_SHORT).show()
                songLoop.setImageResource(R.drawable.baseline_change_circle_24)
                singleSong=false

            }else{
                mediaPlayer.isLooping = false
                Toast.makeText(applicationContext,"Song Loop Off",Toast.LENGTH_SHORT).show()
                songLoop.setImageResource(R.drawable.baseline_loop_24)
                singleSong=true
            }
        }
    }
    //end songLoop Fun

}

