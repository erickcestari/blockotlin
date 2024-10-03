package com.blockotlin.features.product.routes.createProduct

import com.blockotlin.features.product.data.ProductData
import com.blockotlin.features.product.model.CreateProductDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.createProduct() {
    val productData: ProductData by inject(ProductData::class.java)
    post("/api/v1/product/createProduct") {

        productData.createProduct(call.receive<CreateProductDto>())
        call.respond(HttpStatusCode.OK)
    }
}