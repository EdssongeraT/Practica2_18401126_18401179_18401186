package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import mx.edu.ittepic.tangram_18401126_18401179_18401186.R
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery.GalleryFragment

class LienzoTangram(p: GalleryFragment): View(p.context)  {
    var fondo = BitmapFactory.decodeResource(resources, R.drawable.fondo)
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()

        var rect = Rect(0,0,width,height)
        c.drawBitmap(fondo,null,rect,p)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        return super.onTouchEvent(e)
    }
}