package com.blockotlin.features.product.routes.deleteProduct

import com.blockotlin.features.product.data.ProductData
import com.blockotlin.features.product.model.FilterProductsDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.deleteProduct() {
    val productData: ProductData by inject(ProductData::class.java)

    delete("/api/v1/product/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid product ID")
            return@delete
        }

        val wasDeleted = productData.deleteProduct(id)

        if (wasDeleted) {
            call.respond(HttpStatusCode.OK, "Product deleted successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "Product not found")
        }
    }
}