package udesc.br.yota.ui.musicLibrary

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.R
import com.example.yota.databinding.FragmentLibraryBinding
import udesc.br.yota.MainActivity
import udesc.br.yota.ui.model.Music
import udesc.br.yota.ui.musicLibrary.adapter.MusicsAdapter
import udesc.br.yota.ui.repository.MusicDao
import udesc.br.yota.ui.repository.MusicRepository

class LibraryFragment : Fragment() {

    lateinit var listMusic: ListView
    lateinit var musics: List<Music>
    lateinit var repository: MusicRepository
    lateinit var adapter: MusicsAdapter
    lateinit var context: Context
    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        context = context as MainActivity
        repository= MusicDao()
        musics= repository.getAllMusics()
        adapter = MusicsAdapter(musics, context)
        listMusic= binding.listMusics

        listMusic.adapter= adapter

        return LayoutInflater.from(container?.context).inflate(com.example.yota.R.layout.fragment_library, container, false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}