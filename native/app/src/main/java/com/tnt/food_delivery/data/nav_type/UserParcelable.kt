package com.tnt.food_delivery.data.nav_type

import android.os.Parcelable
import com.tnt.food_delivery.data.response.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserParcelable(
    val id: String? = null,
    val name: String? = null,
    val avatar: String? = null,
    val description: String? = null,
    val isMale: Boolean = true,
    val birthOfDate: String? = null,
    val username: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val password: String? = null,
    val address: String? = null,
    val wards: String? = null,
    val district: String? = null,
    val province: String? = null,
    val userRole: String = "USER",
    val status: String = "ACTIVATED",
) : Parcelable {
    companion object Factory {
        fun fromUserResponse(user: UserResponse): UserParcelable {
            return UserParcelable(
                user.id,
                user.name,
                user.avatar,
                user.description,
                user.isMale,
                user.birthOfDate,
                user.username,
                user.email,
                user.phoneNumber,
                user.password,
                user.address,
                user.wards,
                user.district,
                user.province,
                user.userRole,
                user.status
            )
        }
    }
}