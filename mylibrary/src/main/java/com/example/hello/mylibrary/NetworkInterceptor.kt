package com.example.hello.mylibrary

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor {
    fun buildClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.addInterceptor {
            return@addInterceptor intercept(it)
        }.addNetworkInterceptor {
            return@addNetworkInterceptor intercept(it)
        }.build()
    }

    private fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        if (response.isRedirect) {
            println("ε=ε=ε=ε=ε=ε= ┌(= ・ω・=)┘")
            return response
        }

        if (response.isSuccessful) {
            println("(=・∀・=)b")
        } else {
            println("(=・A・=)q")
            printError(response = response)
        }
        return response
    }

    fun printError(response: Response) {
        println("failed/ code: ${response.code} / message: ${response.message}")
    }
}