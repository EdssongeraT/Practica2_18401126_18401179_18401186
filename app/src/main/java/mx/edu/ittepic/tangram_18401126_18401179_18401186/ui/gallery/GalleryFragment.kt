package mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.tangram_18401126_18401179_18401186.ui.gallery.GalleryFragment
class GalleryFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        return LienzoTangram(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}