package com.tnt.food_delivery.data.repository

import com.tnt.food_delivery.core.Action
import com.tnt.food_delivery.core.utils.RetrofitUtils.bodyOrThrow
import com.tnt.food_delivery.core.utils.constants.Status.SUCCESS
import com.tnt.food_delivery.core.utils.constants.Status.USER_NOT_VERIFIED
import com.tnt.food_delivery.core.utils.runIo
import com.tnt.food_delivery.core.utils.toAction
import com.tnt.food_delivery.data.model.User
import com.tnt.food_delivery.network.api.AuthService
import com.tnt.food_delivery.network.api.UserApi
import com.tnt.food_delivery.network.repository.UserRepository
import com.tnt.food_delivery.network.service.user.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val userApi: UserApi,
//    private val userDao: UserDao,
//    private val feedService: FeedService,
    private val userService: UserService
) : UserRepository {

    override fun loginWith(username: String, password: String): Flow<Action<User>> = flow {
        emit(Action.Loading())

        val response = runIo {
            authService.login(
                mapOf(
                    "username" to username,
                    "password" to password
                )
            ).execute()
        }
        val body = response.bodyOrThrow()

        when (body.status) {
            SUCCESS, USER_NOT_VERIFIED -> emit(Action.Success(data = body.data?.asDomain()))
            else -> emit(Action.Empty(body.status))
        }
    }.catch { emit(it.toAction()) }

    override fun registerWith(
        name: String,
        surname: String,
        nickname: String,
        email: String,
        password: String
    ): Flow<Action<User>> {
        TODO("Not yet implemented")
    }

//    override fun registerWith(
//        name: String,
//        surname: String,
//        nickname: String,
//        email: String,
//        password: String
//    ): Flow<Action<User>> = flow {
//        emit(Action.Loading())
//        val response =
//            runIo { authService.registerWith(name, surname, nickname, email, password).execute() }
//        val body = response.bodyOrThrow()
//
//        if (body.status == SUCCESS) emit(Action.Success(data = body.data?.asDomain()))
//        else emit(Action.Empty(body.status))
//
//    }.catch { emit(it.toAction()) }
}