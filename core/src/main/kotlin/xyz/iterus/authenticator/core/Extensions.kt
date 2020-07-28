package xyz.iterus.authenticator.core

import java.nio.ByteBuffer

fun Long.toByteArray(): ByteArray = ByteBuffer.allocate(Long.SIZE_BYTES).putLong(this).array()
