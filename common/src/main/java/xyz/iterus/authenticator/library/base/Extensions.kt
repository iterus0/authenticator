package xyz.iterus.authenticator.library.base

import java.nio.ByteBuffer

fun Long.toByteArray(): ByteArray = ByteBuffer.allocate(Long.SIZE_BYTES).putLong(this).array()
