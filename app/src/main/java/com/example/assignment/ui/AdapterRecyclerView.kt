package com.example.assignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.models.Artist

class AdapterRecyclerView() : RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>() {

    // initializing mList to store the data passed in using setData function
    var mList: List<Artist> = ArrayList()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // find the TextView element from the rows layout and store them into variables
        var artistName = itemView.findViewById<TextView>(R.id.text_view_artist_name)
        var trackName = itemView.findViewById<TextView>(R.id.text_view_artist_track_name)
        var releaseDate = itemView.findViewById<TextView>(R.id.text_view_artist_release_date)
        var primaryGenreName =
            itemView.findViewById<TextView>(R.id.text_view_artist_track_primary_genre_name)
        var trackPrice = itemView.findViewById<TextView>(R.id.text_view_artist_release_trackPrice)

        // update the ui elements inside bind function
        fun bind(artist: Artist) {
            artistName.text = artist.artistName
            trackName.text = artist.trackName
            releaseDate.text = artist.releaseDate
            primaryGenreName.text = artist.primaryGenreName
            trackPrice.text = "$ ${artist.trackPrice}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var root = LayoutInflater.from(parent.context)
            .inflate(R.layout.rows_artist_information, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // bind the each index of mList to different ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    // every time this function is called, I will store that data into the mList
    // and notify that data set has changed.
    fun setData(list: List<Artist>) {
        mList = list
        notifyDataSetChanged()
    }
}