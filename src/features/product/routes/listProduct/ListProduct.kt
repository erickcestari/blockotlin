package com.blockotlin.features.product.routes.listProduct

import com.blockotlin.features.product.data.ProductData
import com.blockotlin.features.product.model.FilterProductsDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.listProduct() {
    val productData: ProductData by inject(ProductData::class.java)
    get("/public-api/v1/product") {
        val nameFilter = call.request.queryParameters["name"]
        val filter = FilterProductsDto(
            name = nameFilter,
        )

        val products = productData.listProduct(filter)
        call.respond(HttpStatusCode.OK, mapOf("products" to products))
    }
}