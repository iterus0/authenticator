package xyz.iterus.authenticator.token

import org.koin.dsl.module
import xyz.iterus.authenticator.token.hotp.HOTP
import xyz.iterus.authenticator.token.hotp.RFC4226
import xyz.iterus.authenticator.token.totp.RFC6238
import xyz.iterus.authenticator.token.totp.Steam
import xyz.iterus.authenticator.token.totp.TOTP

object TestTokenModule {

    val module = module {
        factory { RFC4226() }
        factory { RFC6238(get()) }
        factory { Steam(get()) }

        factory<HOTP> { get<RFC4226>() }
        factory<TOTP> { get<RFC6238>() }
    }
}
