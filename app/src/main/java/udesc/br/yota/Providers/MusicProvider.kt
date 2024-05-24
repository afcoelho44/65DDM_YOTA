package udesc.br.yota.Providers


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import udesc.br.yota.ui.model.Music


interface MusicProvider {
    @GET("/Track/{id}")
    fun select(@Path("id") id: Int): Call<Music>

    @GET("/Track/all")
    fun getMusics(): Call<List<Music>>

    @GET("/Track/{title}")
    fun getMusicByTitle(@Path("title") title: String): Call<Music>
}