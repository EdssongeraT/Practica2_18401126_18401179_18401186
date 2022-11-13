package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class FiguraTangram(l:LienzoTangram,recurso:Int,x:Float,y:Float) {
    val l = l
    var x = x
    var y = y
    var imagen = BitmapFactory.decodeResource(l.resources,recurso)

    fun pintarFigura(c: Canvas){
        c.drawBitmap(imagen,x,y,Paint())
    }
    fun estaEnArea(toqueX: Float, toqueY:Float):Boolean{
        var x2 = x + imagen.width
        var y2 = y + imagen.height
        if(toqueX>=x && toqueX<= x2){
            if(toqueY>=y && toqueY<=y2){
                return true
            }
        }
        return false
    }
    fun arrastrar(toqueX:Float, toqueY:Float)
    {
        x = toqueX-(imagen.width/2)
        y = toqueY-(imagen.height/2)
    }
    fun colision(imagenB: FiguraTangram): Boolean{
        var x2 = x + imagen.width
        var y2 = y + imagen.height
        return false
    }//Colision
}