package com.handarui.game.util

import java.text.DecimalFormat

object NumberFormat {

    private val numberFormat = DecimalFormat("#0.00")

    fun formatDouble(d: Double?):String{
        return if(d == null){
            "0.00"
        }else{
            numberFormat.format(d)
        }
    }
}