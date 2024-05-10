package udesc.br.yota.ui.repository

import udesc.br.yota.ui.model.User

interface UserRepository {
    fun saveUser(user: User)
    fun getAllUsers(): MutableList<User>

}
class UserDao: UserRepository{

    companion object{
        private val users= ArrayList<User>()
    }
    override fun saveUser(user: User) {
        users.add(user)
    }

    override fun getAllUsers(): MutableList<User> {
        return users
    }

}