package com.vholodynskyi.assignment.data.base.models


object EmptyRequest

data class RequestWrapper<T>(
  val requestValue: T,
  val customHeaders: HashMap<String, String> = HashMap()
)