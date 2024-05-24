package udesc.br.yota.ui.musicLibrary

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.R
import com.example.yota.databinding.FragmentLibraryBinding
import com.example.yota.databinding.FragmentSearchBinding
import udesc.br.yota.MainActivity
import udesc.br.yota.Providers.MusicPlayerProvider
import udesc.br.yota.ui.model.Music
import udesc.br.yota.ui.musicLibrary.adapter.MusicsAdapter
import udesc.br.yota.ui.musicSearch.SearchViewModel
import udesc.br.yota.ui.repository.MusicDao
import udesc.br.yota.ui.repository.MusicRepository

class LibraryFragment : Fragment(), AdapterView.OnItemClickListener {

    lateinit var musics: List<Music>
    lateinit var repository: MusicRepository
    lateinit var adapter: MusicsAdapter

    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listMusicViewModel =
            ViewModelProvider(this).get(LibraryViewModel::class.java)


        val listMusic: ListView = binding.listMusics
        listMusicViewModel.text.observe(viewLifecycleOwner) {
            val context = context as MainActivity
            repository= MusicDao()
            musics= repository.getAllMusics()
            adapter = MusicsAdapter(musics, context)


            listMusic.adapter= adapter
        }

        listMusic.setOnItemClickListener(this)

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val music = parent?.getItemAtPosition(position) as Music
        val player= MusicPlayerProvider.getInstance(null)

        player.setMusic(music.id)

    }

}