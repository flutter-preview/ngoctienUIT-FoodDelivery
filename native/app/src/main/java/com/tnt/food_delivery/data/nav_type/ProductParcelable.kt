package com.tnt.food_delivery.data.nav_type

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import com.tnt.food_delivery.data.response.ProductResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductParcelable(
    val id: String? = null,
    val name: String? = null,
    val restaurant: UserParcelable? = null,
    val image: String? = null,
    val description: String? = null,
    val status: String? = null,
    val isSize: Boolean? = null,
    val createAt: String? = null,
    val updateAt: String? = null,
    val price: Long? = null,
    val s: Long? = null,
    val m: Long? = null,
    val l: Long? = null,
) : Parcelable {
    companion object Factory {
        fun fromProductResponse(product: ProductResponse): ProductParcelable {
            return ProductParcelable(
                product.id,
                product.name,
                UserParcelable.fromUserResponse(product.restaurant!!),
                product.image,
                product.description,
                product.status,
                product.isSize,
                product.createAt,
                product.updateAt,
                product.price,
                product.s,
                product.m,
                product.l
            )
        }
    }

    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
