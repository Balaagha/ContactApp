package com.vholodynskyi.assignment.data.base.models




data class ModelWrapper<T> constructor(
  val message: String? = null,
  val data: T? = null
)