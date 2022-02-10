package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.User

suspend fun getUsers(): List<User> {
    return userService.getUsers()
}

suspend fun getUser(id: Int): User {
    return userService.getUser(id)
}

suspend fun createUser(user: User) {
    return userService.createUser(user)
}

suspend fun updateUser(id: Int, user: User) {
    return userService.updateUser(id, user)
}

suspend fun deleteUser(id: Int) {
    return userService.deleteUser(id)
}