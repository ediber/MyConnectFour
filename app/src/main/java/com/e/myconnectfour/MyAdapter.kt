package com.e.myconnectfour

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MyAdapter(val board: ArrayList<ArrayList<Int>>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.board_layout, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return board.size * board[0].size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var j = position / 7
        var i = position % 7
        val player = board[i][j]

        val playerView = holder.playerView
        when (player) {
            0 -> { // no player
               // playerView.setBackgroundColor(0)
            }
            1 -> { //  player 1
                playerView.setBackgroundResource(R.drawable.circle1)
               // button_change.setBackgroundResource(R.drawable.btn_center_gradient);
            }
            2 -> { // player2
                playerView.setBackgroundResource(R.drawable.circle2)
            }
            else -> { // win
                playerView.setBackgroundResource(R.drawable.circle_win)
            }
        }
        //playerView.text = position.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val playerView: TextView = itemView.findViewById(R.id.player)
    }

}