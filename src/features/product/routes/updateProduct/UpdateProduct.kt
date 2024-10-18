package com.blockotlin.features.product.routes.updateProduct


import com.blockotlin.features.product.data.ProductData
import com.blockotlin.features.product.model.UpdateProductDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.updateProduct() {
    val productData: ProductData by inject(ProductData::class.java)

    put("/api/v1/product/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val body = call.receive<UpdateProductDto>()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid product ID")
            return@put
        }

        val wasUpdated = productData.updateProduct(id, body)

        if (wasUpdated) {
            call.respond(HttpStatusCode.OK, "Product updated successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "Product not found")
        }
    }
}