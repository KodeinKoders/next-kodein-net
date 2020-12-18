package net.kodein.pages.oss

import kotlinx.css.*
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.oss.fragment.FrameworkLayers
import net.kodein.pages.oss.fragment.FrameworkOnion
import net.kodein.utils.Illus
import net.kodein.utils.maxWidth
import react.RProps
import react.child
import react.dom.a
import react.dom.b
import react.dom.br
import react.functionalComponent
import styled.styledDiv


val Oss = functionalComponent<RProps> {

    // TODO refactor this
    val coverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Kodein Framework" }
        override val title: TextHandler = {
            +"Discover our powerful"
            br {}
            +"Open Source Software."
        }
        override val chapo: TextHandler = {
            +"""Since the dawn of Kotlin/Multiplatform, we have been contributing many tools to the ecosystem.
                        |In fact, Kodein Koders released the very first Open Source Kotlin/Multiplatform community library.
                        |We are always looking for new ways to contribute to the multiplatform narrative!""".trimMargin()
        }
    }

    // TODO refactor this
    val wantMoreStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Want more?" }
        override val title: TextHandler = {
            +"Check our "
            a(href = "https://github.com/Kodein-Framework", target = "_blank") { +"Github" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Ask us anything about the Open Source "
            b { +"KODEIN" }
            +"Framework on "
            a(href = "https://stackoverflow.com/tags/kodein", target = "_blank") { +"Stack Overflow" }
            +", "
            a(href = "https://kotlinlang.slack.com/archives/C0BLU9K96", target = "_blank") { +"Slack" }
            +" or "
            a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" }
            +"!"
        }
    }

    child(MenuTop) {
        attrs {
            animated = true
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.kyzantium
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.kyzantium,
                text = Color.kodein.kaumon,
                title = Color.kodein.orange,
                overTitle = Color.kodein.korail,
                layers = listOf(Color.kodein.krouille, Color.kodein.orange)
            )
            content = coverStrings
        }
    }

    contentRow(
        backgroundColor = Color.kodein.kyzantium,
        indexPosition = 1,
        illustration = Illus(Illus.Title.OSS, Illus.Position.RIGHT, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.kuivre),
        additionalStyle = {
          backgroundColor = Color.kodein.kyzantium
        },
        noPadding = true
    ) {
        child(FrameworkLayers)
    }

    imageRow(
        backgroundColor = Color.kodein.kyzantium,
        indexPosition = 2,
        illustration = Illus(Illus.Title.OSS, Illus.Position.CENTER, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.purple)
    ) {
        display = Display.none
        maxWidth(980) { display = Display.flex }
    }

    child(FrameworkOnion)

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.dark,
                title = Color.kodein.orange,
                layers = listOf(Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)
            )
            content = wantMoreStrings
        }
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)


}