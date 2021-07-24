import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import java.net.URL

fun main(args: Array<String>) = CurrencyCommand().main(args)

class CurrencyCommand : CliktCommand(help = "Returns currency exchange rate in PLN") {
    val currency by option(help = Currency.values().joinToString(" and ")).enum<Currency>().default(Currency.USD)
    override fun run() {
        echo("Chosen currency: $currency")
        echo("Exchange rate to PLN: ${CurrencyFetchService.fetch(currency)}")
    }
}

enum class Currency {
    USD,
    EUR,
}

object CurrencyFetchService {
    fun fetch(currency: Currency): Float {
        val text = URL("https://www.money.pl/pieniadze/nbp/srednie/").readText()
        val searchPhrase = searchPhrases[currency] ?: error("Currency $currency not implemented")
        val startIndex = text.indexOf(searchPhrase) + searchPhrase.count() + 1
        return text.substring(startIndex, startIndex + 4).toFloatOrNull()
            ?: error("Error fetching currency exchange rate")
    }

    private val searchPhrases = mapOf(
        Currency.USD to "\"USD\",\"average\"",
        Currency.EUR to "\"EUR\",\"average\"",
    )
}
