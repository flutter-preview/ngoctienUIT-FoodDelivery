package com.tnt.food_delivery.network.use_case

import com.tnt.food_delivery.network.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        login: String,
        password: String
    ) = userRepository.loginWith(login, password)
}