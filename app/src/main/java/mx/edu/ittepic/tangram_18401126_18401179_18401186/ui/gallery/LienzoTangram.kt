package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.LienzoTangram
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery.GalleryFragment

class LienzoTangram(p: GalleryFragment): View(p.context)  {
    var fondo = BitmapFactory.decodeResource(resources, R.drawable.fondos)
    var ciervo = FiguraTangram(this,R.drawable.ciervo,200f,200f)
    var puntero : FiguraTangram?= null
    var este = p.context
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()

        var rect = Rect(0,0,width,height)
        c.drawBitmap(fondo,null,rect,p)

        p.color = Color.WHITE
        //---------------texto-----------------
        p.textSize= 60f
        c.drawText("Construye el pez",150f,80f,p)

        ciervo.pintarFigura(c)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when(e.action){
            MotionEvent.ACTION_DOWN->{
                if(ciervo.estaEnArea(e.x,e.y)){
                    puntero=ciervo
                }
            }
            MotionEvent.ACTION_MOVE->{
                if(puntero!=null){
                    puntero!!.arrastrar(e.x,e.y)
                }
            }
            MotionEvent.ACTION_UP->{
                puntero = null
            }
        }
        invalidate()
        return true
    }
}