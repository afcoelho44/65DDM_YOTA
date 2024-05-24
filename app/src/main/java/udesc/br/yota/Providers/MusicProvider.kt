package udesc.br.yota.Providers


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import udesc.br.yota.ui.model.Music
import java.util.UUID


interface MusicProvider {
    @GET("/Track/{id}")
    fun select(@Path("id") id: UUID): Call<ByteArray>

    @GET("/Track/all")
    fun getMusics(): Call<List<ByteArray>>

    @GET("/Track/{title}")
    fun getMusicByTitle(@Path("title") title: String): Call<ByteArray>
}