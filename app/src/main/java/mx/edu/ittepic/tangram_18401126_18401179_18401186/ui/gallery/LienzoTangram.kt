package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.LienzoTangram
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery.GalleryFragment

class LienzoTangram(p: GalleryFragment): View(p.context)  {
    var fondo = BitmapFactory.decodeResource(resources, R.drawable.fondos)
    var bandera=0
    var primeropunto = FiguraTangram(this,R.drawable.equisnegra,100f,450f)
    var segundopunto = FiguraTangram(this,R.drawable.equisnegra,100f,850f)
    var tercerpunto = FiguraTangram(this,R.drawable.equisnegra,320f,200f)
    var cuartopunto = FiguraTangram(this,R.drawable.equisnegra,320f,700f)
    var quintopunto = FiguraTangram(this,R.drawable.equisnegra,320f,1100f)
    var sextopunto = FiguraTangram(this,R.drawable.equisnegra,550f,450f)
    var septimopunto = FiguraTangram(this,R.drawable.equisnegra,550f,850f)
    var trianguloUno = FiguraTangram(this,R.drawable.triangulouno,200f,400f)
    var trianguloTres = FiguraTangram(this,R.drawable.triangulotres,200f,400f)
    var trianguloCuatro = FiguraTangram(this,R.drawable.triangulocuatro,200f,400f)
    var trianguloDos = FiguraTangram(this,R.drawable.triangulodos,200f,800f)
    var cuadrado = FiguraTangram(this,R.drawable.cuadrado,200f,400f)
    var trianguloSeis = FiguraTangram(this,R.drawable.trianguloseis,200f,400f)
    var paralelogramo = FiguraTangram(this,R.drawable.paralelogramo,200f,400f)
    var puntero : FiguraTangram?= null
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()
        var rect = Rect(0,0,width,height)
        c.drawBitmap(fondo,null,rect,p)
        p.color = Color.BLACK
        //---------------texto-----------------
        p.textSize= 60f
        c.drawText("Construye el pez",150f,80f,p)
        trianguloUno.pintarFigura(c)
        trianguloDos.pintarFigura(c)
        trianguloTres.pintarFigura(c)
        trianguloCuatro.pintarFigura(c)
        cuadrado.pintarFigura(c)
        trianguloSeis.pintarFigura(c)
        paralelogramo.pintarFigura(c)
        primeropunto.pintarFigura(c)
        tercerpunto.pintarFigura(c)
        cuartopunto.pintarFigura(c)
        quintopunto.pintarFigura(c)
        sextopunto.pintarFigura(c)
        septimopunto.pintarFigura(c)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when(e.action){
            MotionEvent.ACTION_DOWN->{
                if(trianguloUno.estaEnArea(e.x,e.y)){
                    puntero=trianguloUno
                }
                if(trianguloDos.estaEnArea(e.x,e.y)){
                    puntero=trianguloDos
                }
                if(trianguloTres.estaEnArea(e.x,e.y)){
                    puntero=trianguloTres
                }
                if(trianguloCuatro.estaEnArea(e.x,e.y)){
                    puntero=trianguloCuatro
                }
                if(cuadrado.estaEnArea(e.x,e.y)){
                    puntero=cuadrado
                }
                if(trianguloSeis.estaEnArea(e.x,e.y)){
                    puntero=trianguloSeis
                }
                if(paralelogramo.estaEnArea(e.x,e.y)){
                    puntero=paralelogramo
                }
            }
            MotionEvent.ACTION_MOVE->{
                if(puntero==trianguloUno){
                    trianguloUno.arrastrar(e.x,e.y)
                    if(primeropunto.colision(trianguloUno)){
                        primeropunto.invisible()
                    }
                }
                if(puntero==trianguloDos){
                    if(segundopunto.colision(trianguloDos)){
                        segundopunto.invisible()
                    }
                }
                if(puntero==trianguloTres){
                    if(tercerpunto.colision(trianguloTres)){
                        tercerpunto.invisible()
                    }
                }
                if(puntero==cuadrado){
                    if(tercerpunto.colision(cuadrado)){
                        cuartopunto.invisible()
                    }
                }
                if(puntero==trianguloCuatro){
                    if(cuartopunto.colision(trianguloCuatro)){
                        quintopunto.invisible()
                    }
                }
                if(puntero==paralelogramo){
                    if(quintopunto.colision(paralelogramo)){
                        quintopunto.invisible()
                    }
                }
                if(puntero==trianguloSeis){
                    if(sextopunto.colision(trianguloSeis)){
                        trianguloSeis.invisible()
                    }
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