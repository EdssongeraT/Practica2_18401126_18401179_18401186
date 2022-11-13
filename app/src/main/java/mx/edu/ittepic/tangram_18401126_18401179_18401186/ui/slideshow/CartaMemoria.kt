package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.slideshow

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R

class CartaMemoria(l:LienzoMemoria,recurso:Int,x:Float,y:Float,audio:Int) {
    val l = l
    var recurso = recurso
    var imagen = BitmapFactory.decodeResource(l.resources, recurso)
    var reajuste = Bitmap.createScaledBitmap(imagen,200,200,true)
    var x = x
    var y = y
    var volteada = false
    var bloqueada = false
    var audio = audio
    fun pintar(c: Canvas){
        reajuste = Bitmap.createScaledBitmap(imagen,200,200,true)
        c.drawBitmap(reajuste,x,y, Paint())
    }

    fun voltear(){
        if(!bloqueada) {
            if (volteada) {
                volteada = false
                imagen = BitmapFactory.decodeResource(l.resources, recurso)
            } else {
                volteada = true
                imagen = BitmapFactory.decodeResource(l.resources, R.drawable.backimage)
            }
        }
    }

    fun bloquear(){
        bloqueada = true
    }
    fun desbloquear(){
        bloqueada = false
    }
    fun area(tx:Float,ty:Float):Boolean{
        var x2 = x+200f
        var y2 = y+200f

        if (tx >= x && tx <= x2){
            if (ty >= y && ty <= y2){
                return true
            }
        }
        return false
    }


}