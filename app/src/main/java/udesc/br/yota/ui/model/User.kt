package udesc.br.yota.ui.model

class User (private val name:String, private val username:String, private val password:String){

    override fun toString(): String {
        return StringBuilder()
            .append("Nome: $name")
            .append("Nome: $username")
            .append("Nome: $password").toString()
    }
}