package com.e.myconnectfour

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_main1.*
import kotlinx.android.synthetic.main.game_fragment.*


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.board.observe(viewLifecycleOwner, Observer {
            val adapter = MyAdapter(it)
            adapter.notifyDataSetChanged()
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(context, 7)
        })

        viewModel.turn.observe(viewLifecycleOwner, Observer {
            turnView.text = getString(R.string.turn) + it
        })

        viewModel.winner.observe(viewLifecycleOwner, Observer {
            if(it != 0){
               // winnerView.text = "the winner is player $it"
                winnerView.text = getString(R.string.winner) + it
                linear.visibility = View.INVISIBLE
                if(it == 1){ // yellow won
                    winnerView.setBackgroundColor(0xAAFFFF00.toInt())
                } else { // red won
                    winnerView.setBackgroundColor(0xAAFF0000.toInt())
                }
            }
        })

        button0.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(0)
        })
        button1.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(1)
        })
        button2.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(2)
        })
        button3.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(3)
        })
        button4.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(4)
        })
        button5.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(5)
        })
        button6.setOnClickListener(View.OnClickListener {
            viewModel.buttonClicked(6)
        })

        finish.setOnClickListener(View.OnClickListener {
            this.activity?.onBackPressed()
        })
    }

}
