package com.vholodynskyi.assignment.framework

sealed class Events {
  object Loading : Events()

  object Done : Events()
}
