package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class LienzoTangram(p: Fragment): View(p.context), ViewModelStoreOwner {
    override fun onDraw(c: Canvas) {
        val p = Paint()
        p.color = Color.rgb(204,255,255)
        c.drawRect(0f,0f,900f,800f,p)
        super.onDraw(c)
    }

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }
}