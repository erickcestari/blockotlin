package com.blockotlin.errors

data class GenericServerError (val httpStatus: Int, val message:String)