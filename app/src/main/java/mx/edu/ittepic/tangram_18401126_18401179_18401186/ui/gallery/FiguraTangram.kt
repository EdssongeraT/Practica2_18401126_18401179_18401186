package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class FiguraTangram(l:LienzoTangram,recurso:Int,x:Float,y:Float) {
    val l = l
    var x = x
    var y = y
    var imagen = BitmapFactory.decodeResource(l.resources,recurso)
    var ancho = imagen.getWidth();
    var alto = imagen.getHeight();
    var sePinta=true
    var seArrastra=true

    fun pintarFigura(c: Canvas){
        if(!sePinta) return
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
        if(!seArrastra) return
        x = toqueX-(imagen.width/2)
        y = toqueY-(imagen.height/2)
    }
    fun colision(imagenB: FiguraTangram): Boolean{
        var x2 = imagenB.x + imagenB.ancho
        var y2 = imagenB.y + imagenB.alto
        if(imagenB.estaEnArea(x2,y2))return true
        if(imagenB.estaEnArea(x,y2))return true
        if(imagenB.estaEnArea(x,y)) return true
        if(imagenB.estaEnArea(x2,y)) return true
        return false
    }//Colision
    fun invisible(){
        sePinta = false
        seArrastra = false
    }
}