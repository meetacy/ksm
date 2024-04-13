package ksm.ktgbotapi.match

public fun MatchScope.command(
    text: String,
    args: Int? = null,
    prefix: String = "/",
    block: (List<String>) -> Unit
) {
    // todo: сделать реализацию, которая будет учитывать количество аргументов и т.п.
    //  возможно стоит тут задействовать интерцепторы и через них прокидывать дефолтное сообщение,
    //  когда команда неизвестна
}
