package pl.pwr.andz1.pokemony

import android.media.Image

class Pokemon(
    val name : String,
    val primaryType: String,
    val secondaryType : String,
    val generation : Int,
    val image : Int
)
{
    var favourite : Boolean = false

    fun setToFav(){
        favourite = true
    }

    fun setToNotFav(){
        favourite = false
    }
}