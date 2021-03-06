package net.kodein

import net.kodein.lang.Strings
import net.kodein.pages.blog.Blog
import net.kodein.pages.contact.Contact
import net.kodein.pages.credits.Credits
import net.kodein.pages.home.Home
import net.kodein.pages.oss.Oss
import net.kodein.pages.services.Services
import net.kodein.pages.team.Team
import net.kodein.pages.training.Training
import react.FunctionalComponent
import react.RProps


interface PageStrings {
    val pageTitle: String
    val pageDescription: String
}

data class Page(
    val id: String,
    val strings: Strings.() -> PageStrings,
    val illus: String,
    val component: () -> FunctionalComponent<RProps>
)

val appPages = listOf(
    Page("index", Strings::home, "services") { Home },
    Page("services", Strings::services, "services") { Services },
    Page("training", Strings::training, "training") { Training },
    Page("oss", Strings::oss, "open-source") { Oss },
    Page("team", Strings::team, "team") { Team },
    Page("blog", Strings::blog, "open-source") { Blog },
    Page("contact", Strings::contact, "training") { Contact },
    Page("credits", Strings::credits, "team") { Credits },
)
