package com.tnt.food_delivery.network.service.user

import com.google.gson.JsonParser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class UserServiceImpl @Inject constructor(
//    jsonParser: JsonParser
//) : WebSocketClient<Response<UserDto>>(jsonParser = jsonParser), UserService {
//
//    override operator fun invoke(
//        id: Long,
//        token: String
//    ): Flow<WebSocketState<Response<UserDto>>> = setBaseUrl(
//        newBaseUrl = "${Constants.WS_BASE_URL}websocket/user/?id=$id&token=$token"
//    ).setType(
//        Types.newParameterizedType(Response::class.java, UserDto::class.java)
//    ).openWebSocket().receiveAsFlow()
//
//    override fun sendMessage(data: String) = send(data)
//
//    override fun closeService() = close()
//
//}