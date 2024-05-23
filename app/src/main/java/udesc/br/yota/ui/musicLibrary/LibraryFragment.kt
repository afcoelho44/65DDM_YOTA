package udesc.br.yota.ui.musicLibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yota.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(LibraryViewModel::class.java)

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listMusics: ListView = binding.listMusics

//        val musics = arrayListOf()
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
           return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}