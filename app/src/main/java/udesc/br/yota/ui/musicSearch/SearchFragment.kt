package udesc.br.yota.ui.musicSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yota.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import udesc.br.yota.Providers.ExternalMusicProvider
import udesc.br.yota.Providers.MusicPlayerProvider


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var provider: ExternalMusicProvider

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val searchButton = binding.searchButton
        val textSearch = binding.textSearch

        provider = ExternalMusicProvider()
        provider.retrofitInitializer()

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        searchButton.setOnClickListener {
            val musicName = textSearch.text.toString()
            searchInApi(musicName)
        }

        return root
    }

    private fun searchInApi(musicName: String){
        try {
            val call = provider.getMusic().getMusicByTitle(musicName)

            call.enqueue(object : Callback<ByteArray> {
                override fun onResponse(call: Call<ByteArray>, response: retrofit2.Response<ByteArray>) {
                    if(response.isSuccessful){
                        val music = response.body()
                        MusicPlayerProvider.getInstance(null).setMusicWithByteArray(music!!)
                    } else {
                        Toast.makeText(activity?.application, "Não achei :(", Toast.LENGTH_LONG);
                    }
                }

                override fun onFailure(call: Call<ByteArray>, t: Throwable) {
                    Toast.makeText(activity?.application, "Não achei :(", Toast.LENGTH_LONG);
                }
            })

        } catch (e: Throwable){
            Toast.makeText(activity?.application, "Não achei :(", Toast.LENGTH_LONG);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}