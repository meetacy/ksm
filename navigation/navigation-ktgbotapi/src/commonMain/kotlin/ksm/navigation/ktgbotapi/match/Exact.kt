package ksm.navigation.ktgbotapi.match

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.utils.RiskFeature
import ksm.navigation.ktgbotapi.message

@OptIn(RiskFeature::class)
public inline fun MatchScope.exact(
    text: String,
    block: () -> Unit
) {
    if (context.message.text == text) block()
}
