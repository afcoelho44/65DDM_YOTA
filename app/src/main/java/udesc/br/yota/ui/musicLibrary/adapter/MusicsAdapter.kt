package udesc.br.yota.ui.musicLibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.yota.R
import udesc.br.yota.ui.model.Music

class MusicsAdapter (private val listMusics: List<Music>, val context: Context): BaseAdapter() {
    override fun getCount(): Int {
       return listMusics.size
    }

    override fun getItem(position: Int): Any {
        return listMusics.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val l = LayoutInflater.from(context)
        val item= l.inflate(R.layout.layout_musics_adapter, parent, false)
        val txtName= item.findViewById<TextView>(R.id.nameMusic)

        txtName.setText(listMusics[position].name)

        return item
    }
}