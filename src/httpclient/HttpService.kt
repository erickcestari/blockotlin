package com.blockotlin.httpclient

import io.ktor.client.*

interface HttpService {
    fun getClient(): HttpClient
}