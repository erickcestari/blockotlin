package com.blockotlin.features.product.routes

import com.blockotlin.features.product.routes.createProduct.createProduct
import com.blockotlin.features.product.routes.deleteProduct.deleteProduct
import com.blockotlin.features.product.routes.findProduct.findProduct
import com.blockotlin.features.product.routes.listProduct.listProduct
import com.blockotlin.features.product.routes.updateProduct.updateProduct
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.productRoutes() {
    routing {
        listProduct()
        findProduct()
        authenticate("auth-admin") {
            createProduct()
            deleteProduct()
            updateProduct()
        }
    }
}