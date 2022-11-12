package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.slideshow

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R

class CartaMemoria(l:LienzoMemoria,recurso:Int,x:Float,y:Float) {
    val l = l
    var recurso = recurso
    var imagen = BitmapFactory.decodeResource(l.resources, recurso)
    var reajuste = Bitmap.createScaledBitmap(imagen,200,200,true)

    var x = x
    var y = y
    var volteada = false

    fun pintar(c: Canvas){
        reajuste = Bitmap.createScaledBitmap(imagen,200,200,true)
        c.drawBitmap(reajuste,x,y, Paint())
    }

    fun voltear(){
        if (volteada){
            volteada = false
            imagen = BitmapFactory.decodeResource(l.resources, recurso)
        }else{
            volteada = true
            imagen = BitmapFactory.decodeResource(l.resources, R.drawable.backimage)
        }
    }

    fun area(tx:Float,ty:Float):Boolean{
        var x2 = x+reajuste.width
        var y2 = y+reajuste.height

        if (tx >= x && tx <= x2){
            if (ty >= y && tx <= y2){
                return true
            }
        }
        return false
    }


}