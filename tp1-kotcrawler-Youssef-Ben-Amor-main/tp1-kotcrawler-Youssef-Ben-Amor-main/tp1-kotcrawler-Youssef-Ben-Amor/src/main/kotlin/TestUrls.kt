
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun main() {
    // explorer les liens dans https://info.cegepmontpetit.ca/3N5-Prog3/z/index.html
     var matches= emptySet<String>()
     val regex = Regex("\\bhttps?://[\\w.-]+(?:/[\\w/.-]*)?|\\b[\\w.-]+(?:/[\\w/.-]*)?\\b")
        val doc: Document = Jsoup.connect("https://info.cegepmontpetit.ca/3N5-Prog3/z/2.html")
            .get()
        for (lien in doc.select("a")) {
            val href= lien.attr("abs:href")
            if (regex.matches(href)) {
                println(href)  // Affiche le lien seulement s'il correspond à la regex
                matches += href
            }
        }



}