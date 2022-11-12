package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.slideshow

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery.GalleryFragment

class LienzoMemoria(p: SlideshowFragment):View(p.context) {
    var fondo = BitmapFactory.decodeResource(resources, R.drawable.fondo)
    var imagenes = arrayOf(R.drawable.borrego,R.drawable.ciervo,R.drawable.elefante,
            R.drawable.hipo,R.drawable.jirafa,R.drawable.koala,R.drawable.mono,
            R.drawable.osonegro,R.drawable.osopardo,R.drawable.panda,
            R.drawable.borrego,R.drawable.ciervo,R.drawable.elefante,
            R.drawable.hipo,R.drawable.jirafa,R.drawable.koala,R.drawable.mono,
            R.drawable.osonegro,R.drawable.osopardo,R.drawable.panda)
    var bitmaps = ArrayList<CartaMemoria>()
    var reinicio = true
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()
        //---------------fondo----------------
        var rect = Rect(0,0,width,height)
        c.drawBitmap(fondo,null,rect,p)
        p.color = Color.rgb(0,0,0)
        //---------------texto-----------------
        p.textSize= 60f
        c.drawText("Puntuacion: 00",60f,80f,p)
        c.drawText("Fallos: 00",750f,80f,p)
        //---------------cartas----------------
        p.color = Color.rgb(204,255,255)
        if (reinicio) {
            asignarcartas()
            reinicio = false
        }
        for (bitmap in bitmaps){
            bitmap.pintar(c)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN->{
            for(bitmap in bitmaps){
                if (bitmap.area(event.x,event.y)) {
                    bitmap.voltear()
                }
            }
            }
            MotionEvent.ACTION_DOWN->{}
            MotionEvent.ACTION_DOWN->{}
        }
        invalidate()
        return true
    }

   fun asignarcartas(){
       bitmaps.clear()
       var x = 60f
       var y = 150f

       for(imagen in imagenes){
           if (x>810){
               x = 60f
               y += 250f
           }
           var carta = CartaMemoria(this,imagen,x,y)
           bitmaps.add(carta)
           x += 250f
       }
    }

}