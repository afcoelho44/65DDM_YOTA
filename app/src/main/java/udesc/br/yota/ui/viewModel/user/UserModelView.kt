package udesc.br.yota.ui.viewModel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import udesc.br.yota.ui.model.User
import udesc.br.yota.ui.repository.UserRepository

class UserModelView constructor(private val repository: UserRepository): ViewModel() {

    private val currentUserLive =MutableLiveData<User>()

    private val usersLiveData =MutableLiveData<List<User>>()

    fun getCurrenttUser(): LiveData<User>{
    if (currentUserLive.value==null){
        loadUser()
    }
        return currentUserLive
}
    private fun loadUser(){
        val user= User("Ana", "aninha", "senha123")
    }

}