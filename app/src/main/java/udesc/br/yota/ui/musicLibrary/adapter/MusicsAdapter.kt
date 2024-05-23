package udesc.br.yota.ui.musicLibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MusicsAdapter (val listMusics: MutableList<Int>, val context: Context): BaseAdapter() {
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
        return convertView!!
    }
}