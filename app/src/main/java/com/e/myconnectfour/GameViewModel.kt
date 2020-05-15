package com.e.myconnectfour

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _board = MutableLiveData<ArrayList<ArrayList<Int>>>()
    val board: LiveData<ArrayList<ArrayList<Int>>>
        get() {
            return _board
        }

    private val _turn = MutableLiveData<Int>()
    val turn: LiveData<Int>
        get() {
            return _turn
        }

    private val _winner = MutableLiveData<Int>()
    val winner: LiveData<Int>
        get() {
            return _winner
        }

    init {
        _board.value = ArrayList<ArrayList<Int>>()
        for (i in 0..6) {
            val arr = ArrayList<Int>()
            for (j in 0..5) {
                arr.add(0)
            }
            _board.value?.add(arr)
        }

        _turn.value = 1

        _winner.value = 0;
    }

    fun buttonClicked(i: Int) {
        //  _board.value?.get(i)?.add(1)
        val tmpBoard = _board.value

        for (j in (tmpBoard?.get(i)?.size!!.minus(1)) downTo 0) {
            if (tmpBoard?.get(i)?.get(j)!! == 0) {
                tmpBoard?.get(i)!![j] = _turn.value!!
                _board.value = tmpBoard
                break
            }
        }

        val winner = checkWin()
        if(winner == 0){
            if (_turn.value == 1) {
                _turn.value = 2
            } else {
                _turn.value = 1
            }
        } else {
            _winner.value = winner
        }



    }

    private fun checkWin(): Int {
        val tmpBoard = _board.value
        var winner = 0
        for (i in 0..6) {
            for (j in 0..5) {
                if(tmpBoard!![i][j] != 0){
                    var currPlayer = tmpBoard[i][j]
                    if (checkDiagonalDown(i, j)) {
                        paintDiagonalDown(i, j)
                        winner = currPlayer
                    } else if (checkDiagonalUp(i, j)) {
                        paintDiagonalUp(i, j)
                        winner = currPlayer
                    } else if (checkRow(i, j)) {
                        paintRow(i, j)
                        winner = currPlayer
                    } else if (checkCol(i, j)) {
                        paintCol(i, j)
                        winner = currPlayer
                    }
                }

            }
        }
        return winner
    }


    private fun paintDiagonalDown(i0: Int, j0: Int) {
        val tmpBoard = _board.value
        var i = i0
        var j = j0

        for (k in 0..3) {
            tmpBoard!![i][j] = 3
            i++
            j++
        }
        _board.value = tmpBoard
    }

    private fun checkDiagonalDown(i0: Int, j0: Int): Boolean {
        val tmpBoard = _board.value
        var i = i0
        var j = j0
        var b = true

        val player = tmpBoard!![i][j]
        for (k in 0..2) { // 3 times
            if(i+1 == 7 || j+1 == 6){
                return false
            }
            if (player != tmpBoard!![i + 1][j + 1]) {
                b = false
            }
            i++
            j++
        }
        return b
    }

    private fun checkDiagonalUp(i0: Int, j0: Int): Boolean {
        val tmpBoard = _board.value
        var i = i0
        var j = j0
        var b = true

        val player = tmpBoard!![i][j]
        for (k in 0..2) { // 3 times
            if(i+1 == 7 || j-1 == -1){
                return false
            }
            if (player != tmpBoard!![i + 1][j - 1]) {
                b = false
            }
            i++
            j--
        }
        return b
    }

    private fun paintDiagonalUp(i0: Int, j0: Int) {
        val tmpBoard = _board.value
        var i = i0
        var j = j0

        for (k in 0..3) {
            tmpBoard!![i][j] = 3
            i++
            j--
        }
        _board.value = tmpBoard
    }

    private fun paintCol(i0: Int, j0: Int) { // j moves
        val tmpBoard = _board.value
        var i = i0
        var j = j0

        for (k in 0..3) {
            tmpBoard!![i][j] = 3
            j++
        }
        _board.value = tmpBoard
    }

    private fun checkCol(i0: Int, j0: Int): Boolean { // j moves
        val tmpBoard = _board.value
        var i = i0
        var j = j0
        var b = true

        val player = tmpBoard!![i][j]
        for (k in 0..2) { // 3 times
            if(j+1 == 6){
                return false
            }
            if (player != tmpBoard!![i][j+1]) {
                b = false
            }
            j++
        }
        return b
    }

    private fun paintRow(i0: Int, j0: Int) { // i moves
        val tmpBoard = _board.value
        var i = i0
        var j = j0

        for (k in 0..3) {
            tmpBoard!![i][j] = 3
            i++
        }
        _board.value = tmpBoard
    }

    private fun checkRow(i0: Int, j0: Int): Boolean { // i moves
        val tmpBoard = _board.value
        var i = i0
        var j = j0
        var b = true

        val player = tmpBoard!![i][j]
        for (k in 0..2) { // 3 times
            if(i+1 == 7){
                return false
            }
            if (player != tmpBoard!![i+1][j]) {
                b = false
            }
            i++
        }
        return b
    }

}
