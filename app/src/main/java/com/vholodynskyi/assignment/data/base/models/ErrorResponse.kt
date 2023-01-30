package com.vholodynskyi.assignment.data.base.models

data class ErrorResponse(
  val detail: String,
  val instance: Any,
  val status: Int,
  val title: String,
  val type: String
)