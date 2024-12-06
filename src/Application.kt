package com.blockotlin

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.blockotlin.di.applicationModule
import com.blockotlin.errors.GenericServerError
import com.blockotlin.features.authentication.di.authenticationModule
import com.blockotlin.features.cart.di.cartModule
import com.blockotlin.features.healthcheck.di.healthCheckModule
import com.blockotlin.features.product.di.productModule
import com.blockotlin.jwt.JwtManager
import com.blockotlin.utils.DatabaseFactory
import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.forwardedheaders.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger
import org.slf4j.event.Level
import java.security.InvalidParameterException
import java.text.DateFormat

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init()

    install(ForwardedHeaders)

    install(Koin) {
        SLF4JLogger()
        modules(applicationModule, authenticationModule, healthCheckModule, productModule, cartModule)
    }

    val jwtManager: JwtManager by inject(JwtManager::class.java)

    install(CORS) {
        anyHost()
        allowCredentials = true
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
        allowNonSimpleContentTypes = true
        maxAgeInSeconds = 3600
    }

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(jwtManager.getVerifier())
            validate {
                UserIdPrincipal(it.payload.getClaim("email").asString())
            }

            authHeader { call ->
                val cookieValue = call.request.cookies["token"] ?: call.request.authorization()?.substring(7)
                try {
                    parseAuthorizationHeader("Bearer $cookieValue")
                } catch (cause: IllegalArgumentException) {
                    cause.message
                    null
                }
            }
        }
        jwt("auth-admin") {
            verifier(jwtManager.getVerifier())
            validate {
                if (it.payload.getClaim("role").asString() == "ADMIN") {
                    UserIdPrincipal(it.payload.getClaim("email").asString())
                } else {
                    null
                }
            }

            authHeader { call ->
                val cookieValue = call.request.cookies["token"] ?: call.request.authorization()?.substring(7)
                try {
                    parseAuthorizationHeader("Bearer $cookieValue")
                } catch (cause: IllegalArgumentException) {
                    cause.message
                    null
                }
            }
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
            registerModule(JavaTimeModule())
            dateFormat = DateFormat.getDateInstance()
        }
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.response.status(HttpStatusCode.InternalServerError)
            call.respond(GenericServerError(500, cause.message.toString()))
            throw cause
        }
        exception<InvalidParameterException> { call, cause ->
            call.response.status(HttpStatusCode.BadRequest)
            call.respond(GenericServerError(400, cause.message.toString()))
            throw cause
        }
        exception<MissingKotlinParameterException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest)
            throw cause
        }
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    routes()
}