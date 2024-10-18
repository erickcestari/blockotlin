package com.blockotlin.features.product.routes.findProduct

import com.blockotlin.features.product.data.ProductData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.findProduct() {
    val productData: ProductData by inject(ProductData::class.java)

    get("/public-api/v1/product/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid product ID")
            return@get
        }

        val product = productData.findProductById(id)

        if (product == null) {
            call.respond(HttpStatusCode.NotFound, "Product not found")
            return@get
        }

        call.respond(HttpStatusCode.OK, product)
    }
}