package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.slideshow

import android.graphics.*
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery.GalleryFragment
import kotlin.system.measureNanoTime

class LienzoMemoria(p: SlideshowFragment):View(p.context) {
    var fondo = BitmapFactory.decodeResource(resources, R.drawable.fondo)
    var imagenes = arrayOf(R.drawable.borrego,R.drawable.ciervo,R.drawable.elefante,
            R.drawable.hipo,R.drawable.jirafa,R.drawable.koala,R.drawable.mono,
            R.drawable.osonegro,R.drawable.osopardo,R.drawable.panda,
            R.drawable.borrego,R.drawable.ciervo,R.drawable.elefante,
            R.drawable.hipo,R.drawable.jirafa,R.drawable.koala,R.drawable.mono,
            R.drawable.osonegro,R.drawable.osopardo,R.drawable.panda)

    var audios = arrayOf(R.raw.borrego,R.raw.ciervo,R.raw.elefante,
        R.raw.hipo,R.raw.jirafa,R.raw.koala,R.raw.mono,
        R.raw.osonegro,R.raw.osopardo,R.raw.panda,
        R.raw.borrego,R.raw.ciervo,R.raw.elefante,
        R.raw.hipo,R.raw.jirafa,R.raw.koala,R.raw.mono,
        R.raw.osonegro,R.raw.osopardo,R.raw.panda)

    var bitmaps = ArrayList<CartaMemoria>()
    var reset = BitmapFactory.decodeResource(this.resources, R.drawable.reset)
    var reajuste = Bitmap.createScaledBitmap(reset,150,150,true)
    var inicio = true
    var puntero1 : CartaMemoria ?= null
    var puntero2 : CartaMemoria ?= null
    var puntos = 0
    var tiempo = 180
    var bloqueo = false
    var stop = false
    lateinit var mp:MediaPlayer
    var reinicio = false
    var victoria = false
    var derrota = false

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()
        //---------------fondo----------------
        var rect = Rect(0,0,width,height)
        c.drawBitmap(fondo,null,rect,p)
        p.color = Color.rgb(0,0,0)
        //---------------texto-----------------
        p.textSize= 60f
        c.drawText("Puntuacion: "+puntos,60f,80f,p)
        c.drawText("Tiempo: "+tiempo,680f,80f,p)
        //c.drawRect(260f,1450f,810f,1600f,p)
        c.drawBitmap(reajuste,470f,1450f, Paint())
        //---------------cartas----------------
        if (inicio) {
            imagenes.shuffle()
            asignarcartas()
            corrutina()
        }
        for (bitmap in bitmaps){
            bitmap.pintar(c)
        }

        if (victoria){
            var victoriaImagen = BitmapFactory.decodeResource(this.resources, R.drawable.victoria)
            var reajusteVictoria = Bitmap.createScaledBitmap(victoriaImagen,500,500,true)
            c.drawBitmap(reajusteVictoria,280f,500f, Paint())
        }
        if (derrota){
            var derrotaImagen = BitmapFactory.decodeResource(this.resources, R.drawable.derrota)
            var reajusteDerrota = Bitmap.createScaledBitmap(derrotaImagen,500,700,true)
            c.drawBitmap(reajusteDerrota,280f,300f, Paint())
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN-> {
                if (!bloqueo) {
                    corrutina(event.x, event.y)
                }
                if (event.x >= 470f && event.x <=620f ){
                    if (event.y >= 1450f && event.y <= 1600f){
                        asignarcartas()
                        tiempo = 180
                        stop = false
                        bloqueo = false
                        reinicio = true
                        puntero1=null
                        puntero2=null
                        puntos=0
                        victoria= false
                        derrota = false
                    }
                }
            }
            MotionEvent.ACTION_DOWN->{}
            MotionEvent.ACTION_DOWN->{}
        }
        return true
    }

   fun asignarcartas(){
       var contador = 0
       imagenes.shuffle()
       bitmaps.clear()
       var x = 60f
       var y = 150f
       for(imagen in imagenes){
           if (x>810){
               x = 60f
               y += 250f
           }
           var nombre = resources.getResourceName(imagen).split("/")
           val carta = CartaMemoria(this,imagen,x,y,resources.getIdentifier(nombre[1],"raw",context.packageName))
           bitmaps.add(carta)
           x += 250f
           contador++
       }
    }

    fun corrutina() = GlobalScope.launch {
        while (true) {
            if (reinicio) {
                desbloquearTodas()
                delay(2000)
                voltearTodas()
                reinicio = false
            } else if(inicio){
                voltearTodas()
                inicio = false
            }

            if(!stop) {
                delay(1000)
                tiempo--
            }
            if (tiempo==0 && !derrota){
                derrota = true
                mp = MediaPlayer.create(context,R.raw.derrota)
                mp.start()
                bloqueo=true
                stop = true
            }
            invalidate()
        }
    }
    fun desbloquearTodas(){
        try {
            for (carta in bitmaps) {
                carta.desbloquear()
            }
        } catch (e: Exception) {}
    }
    fun voltearTodas(){
        try {
            for (carta in bitmaps) {
                carta.voltear()
            }
        } catch (e: Exception) {}
    }
    fun corrutina(x:Float,y:Float) = GlobalScope.launch {
        for(bitmap in bitmaps){
            if (bitmap.area(x,y)) {
                if (puntero1 == null) {
                    puntero1 = bitmap
                    puntero1!!.voltear()
                    puntero1!!.bloquear()
                    invalidate()
                }else if (puntero1 !=null && puntero2 == null){
                    puntero2 = bitmap
                    if (!mismaArea(puntero1!!,puntero2!!)) {
                        puntero2!!.voltear()
                        invalidate()
                        if (puntero1!!.recurso == puntero2!!.recurso) {
                            puntos += 1
                            mp = MediaPlayer.create(context,puntero2!!.audio)
                            mp.start()
                            if (puntos==10){
                                victoria = true
                                mp = MediaPlayer.create(context,R.raw.victoria)
                                mp.start()
                                bloqueo=true
                                stop = true
                            }
                            puntero1!!.bloquear()
                            puntero2!!.bloquear()
                        } else {
                            puntero1!!.desbloquear()
                            puntero2!!.desbloquear()
                            delay(500)
                            puntero1!!.voltear()
                            puntero2!!.voltear()
                            invalidate()
                        }
                        puntero1 = null
                        puntero2 = null
                    }else{
                        puntero2 = null
                    }
                }
                break
            }
        }
    }



    fun mismaArea(carta1:CartaMemoria,carta2: CartaMemoria):Boolean{
        if (carta1.x==carta2.x){
            if (carta1.y==carta2.y){
                return true
            }
        }
        return false
    }
}