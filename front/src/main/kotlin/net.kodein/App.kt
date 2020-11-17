package net.kodein

import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import net.kodein.pages.blog.Blog
import net.kodein.pages.contact.Contact
import net.kodein.pages.home.Home
import net.kodein.pages.oss.Oss
import net.kodein.pages.services.Services
import net.kodein.pages.team.Team
import net.kodein.pages.training.Training
import net.kodein.utils.maxSize
import net.kodein.utils.minSize
import org.w3c.dom.get
import react.*
import react.dom.render


val appGlobalStyle: CSSBuilder.() -> Unit = {
    universal {
        margin(0.px)
        padding(0.px)
    }

    html {
        maxSize(380) { fontSize = 10.px }
        maxSize(480) { fontSize = 12.px }
        maxSize(768) { fontSize = 13.px }
        maxSize(980) { fontSize = 14.px }
        maxSize(1024) { fontSize = 15.px }
        minSize(min = 1920) { fontSize = 17.px }
        minSize(min = 2500) { fontSize = 18.px }
    }

    body {
        fontFamily = "Picon, Arial, sans-serif"
    }

    a {
        textDecoration = TextDecoration.none
        color = Color.inherit
        transition(::color, 0.2.s)
    }

    "noscroll" {
        overflow = Overflow.hidden
    }
}

data class Page<P : RProps>(
    val id: String,
    val component: () -> FunctionalComponent<P>,
    val props: P.() -> Unit = {}
)

val appPages = listOf(
    Page("index", { Home }),
    Page("services", { Services } ),
    Page("training", { Training } ),
    Page("oss", { Oss } ),
    Page("team", { Team } ),
    Page("blog", { Blog } ),
    Page("contact", { Contact } ),
)

fun renderApp() {
    val element = document.getElementById("page") ?: error("Could not find page element")
    val id = element.attributes["data-page"]?.value ?: error("Page element has no data-page attribute")
    val page = appPages.find { it.id == id } ?: error("Could not find page $id")

    render(element) {
        child(page.component()) { page.props(attrs) }
    }
}
