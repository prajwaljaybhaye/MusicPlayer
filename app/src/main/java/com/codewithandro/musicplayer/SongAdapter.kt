package com.codewithandro.musicplayer

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(var Context : Activity , var songArray : ArrayList<songData>):
RecyclerView.Adapter<SongAdapter.MyViewHolder>()
{
    //create the setOnClick Function
    interface setOnClickSong{
        //create function viewHolder pass the postition
        fun clickSongListner(position: Int)
    }
    //create var type interface
    lateinit var myListner : setOnClickSong

    //finally create the mainActivity Acasess function
    fun songClickListner(listner : setOnClickSong){
        myListner = listner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layManger = LayoutInflater.from(Context).inflate(R.layout.eachsong,parent, false)
        return MyViewHolder(layManger,myListner)
    }

    override fun getItemCount(): Int {
        return songArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currView = songArray[position]
        holder.songImg.setImageResource(currView.songImg)
        holder.songTitle.text = currView.songTitle
        holder.songDes.text = currView.songDes
    }

     class MyViewHolder(itemView: View,listner: setOnClickSong) : RecyclerView.ViewHolder(itemView) {

         val songImg = itemView.findViewById<ImageView>(R.id.songImg)
         val songTitle = itemView.findViewById<TextView>(R.id.songTitle)
         val songDes = itemView.findViewById<TextView>(R.id.songDes)

         init {

             itemView.setOnClickListener{
                 //pass adapter position
                 listner.clickSongListner(adapterPosition)
             }
         }
    }

}