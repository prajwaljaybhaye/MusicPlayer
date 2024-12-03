package com.codewithandro.musicplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity() {

    lateinit var songArrayList: ArrayList<songData>
    lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        //create data
        val songImgs = intArrayOf(
            R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4,
            R.drawable.s5,
            R.drawable.s6,
            R.drawable.s7,
            R.drawable.s8,
            R.drawable.s9,
            R.drawable.s10,
            R.drawable.s11,
            R.drawable.s12,
            R.drawable.s13,
            R.drawable.s14,
            R.drawable.s15,
            R.drawable.s16,
            R.drawable.s17,
            R.drawable.s18,
            R.drawable.s19,
            R.drawable.s20,
            R.drawable.s21,
            R.drawable.s22,
            R.drawable.s23,
            R.drawable.s24
        )

        val songTitles = arrayOf(
            "Roots The Village Punjabi s...",
            "Masha Ultrafunk_320 mp...",
            "Fikar - Pagalworld.io...",
            "Sajjda (DjPunjab.CoM)...",
            "Sajjan Raazi  New La...",
            "Vardaan Carrayminati s...",
            "Fed Up(PagalWorld)...",
            "O Sikander Corporate M...",
            "Dabya Ni Karde Punjabi s...",
            "Samjhawan Popular S...",
            "Ishwer Allah Akshay Ku...",
            "Maine Royaan Heart S...",
            "Veera Soora - PagalNew",
            "Ye Jo Has Rahi Hai ...",
            "Bapu Tere Karke - ...",
            "Ghar Aaja Pardeshi ...",
            "Thaa - PagalNew...",
            "Still Rollin New Lat ...",
            "Jeb Mai Tha Chillar Sath ...",
            "Go Down Deh (feat. Shaggy)...",
            " Eminem - Mockingbird ... ",
            "Chal Ve Tu Bandeya ...",
            "AURORA - Runaway",
            "Jeena Jeena - PagalNew"
        )

        val songDes = arrayOf(
            "Bintu Pabra (village)",
            "Viral Mp3 Song Mesha",
            "Rahat Fateh Ali Khan",
            "Gulam Jugni ",
            "Satinder Sartaaj ",
            "Carryminati",
            "PagaliWorld.Com",
            "Kailash Kher",
            "Ndee Kundu, Bintu Pabra",
            "Humpty Sharma ",
            "Khiladi ",
            "New Hindi Song 2021",
            "Naane Varuvean",
            "Instagram Reels Songs",
            "Bapu Tere Karke",
            "Bobby Deoal",
            "Thaa ",
            "Shubh - Still Rollin ",
            "Indian Pop Mp3 ",
            " Eminem - Mockingbird  ",
            "Reels Mp3 Songs",
            "Bandeya",
            "AURORA - Runaway",
            "Badlapur",

        )

        val songs = arrayOf(
            R.raw.root,
            R.raw.mesa,
            R.raw.fikar,
            R.raw.sajda,
            R.raw.sajanrazi,

            R.raw.root,
            R.raw.mesa,
            R.raw.fikar,
            R.raw.sajda,
            R.raw.sajanrazi,

            R.raw.root,
            R.raw.mesa,
            R.raw.fikar,
            R.raw.sajda,
            R.raw.sajanrazi,

            R.raw.root,
            R.raw.mesa,
            R.raw.fikar,
            R.raw.sajda,
            R.raw.sajanrazi,

            R.raw.mesa,
            R.raw.fikar,
            R.raw.sajda,
            R.raw.sajanrazi,

        )

        songArrayList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)


        for (Indx in songTitles.indices ){
            val userData = songData(
                songImgs[Indx],
                songTitles[Indx],
                songDes[Indx],
                songs[Indx]
            )
            songArrayList.add(userData)
        }

        //data ready

        val myAdapter = SongAdapter(this,songArrayList)
        recyclerView.adapter = myAdapter

        //interface itemAction call pass-> object
        myAdapter.songClickListner(object :SongAdapter.setOnClickSong{
            override fun clickSongListner(position: Int) {

                //send the data content
                val songIntent = Intent(applicationContext,SongContent::class.java)
                songIntent.putExtra("Img",songArrayList[position].songImg)//key and value
                songIntent.putExtra("Name",songArrayList[position].songTitle)//key and value
                songIntent.putExtra("Descrption",songArrayList[position].songDes)//key and value
                songIntent.putExtra("song",songArrayList[position].songs)//key and value
                startActivity(songIntent)
            }

        })





        val navBar = findViewById<BottomNavigationView>(R.id.navBar)

       navBar.setOnItemSelectedListener{
           when(it.itemId){
               R.id.myMusic -> Toast.makeText(applicationContext,"Music Fragment",Toast.LENGTH_SHORT).show()//replaseFragment(myMusicFragment())
               R.id.videoItem ->Toast.makeText(applicationContext,"Video Fragment ",Toast.LENGTH_SHORT).show() //replaseFragment(videoFragment())
           }
           true
       }
    }

    /*private fun replaseFragment(fragment: Fragment) {
        //support fragment manager
        val supFrag = supportFragmentManager
        val fragTran = supFrag.beginTransaction()
        fragTran.replace(R.id.recyclerView,fragment)
        fragTran.commit()
    }*/
}