package net.kodein.pages.credits

import kotlinx.css.*
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.Cover
import net.kodein.components.CoverPalette
import net.kodein.components.Footer
import net.kodein.components.MenuTop
import net.kodein.components.strings.CoverStrings
import net.kodein.utils.flexColumn
import react.RProps
import react.child
import react.dom.a
import react.dom.b
import react.dom.br
import react.dom.p
import react.functionalComponent
import styled.css
import styled.styledP


val Credits = functionalComponent<RProps>("Credits") {
    child(MenuTop) {
        attrs {
            animated = false
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.klycine
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.klycine,
                title = Color.kodein.dark,
                overTitle = Color.kodein.kamethiste,
                text = Color.kodein.kinzolin,
                layers = listOf(Color.kodein.korail, Color.kodein.kinzolin)
            )
            content = object : CoverStrings {
                override val overTitle: TextHandler = { +"a great project" }
                override val title: TextHandler = { +"We collaborated with great artists!" }
                override val chapo: TextHandler = {
                    p {
                        +"This web site's design has been created by the very talented "
                        a(href = "https://www.crc-studio.fr/") {
                            +"Rémi B. Loizeau"
                        }
                        +"."
                    }
                    br {}
                    p {
                        +"The beautiful illustrations are from the limitless imagination of "
                        a(href = "https://www.behance.net/using-art") {
                            +"Thimothée Boubay"
                        }
                        +"."
                    }
                }
            }
            overrideContentRuleSet = {
                "a" {
                    color = Color.kodein.orange
                    put("text-shadow", "none")
                }
            }
        }
    }

    flexColumn(alignItems = Align.center) {
        css {
            paddingTop = 10.em
            paddingBottom = 5.em
            backgroundColor = Color.kodein.cute
        }

        styledP {
            css {
                +kodein.body
                textAlign = TextAlign.left

                "a" {
                    color = Color.kodein.purple
                }
            }
            b { +"Technical credits:" }
            br {}
            +"This web site is written in "
            a(href = "https://kotlinlang.org/docs/reference/js-overview.html", target = "_blank") { +"Kotlin/JS" }
            +" using "
            a(href = "https://github.com/JetBrains/kotlin-wrappers", target = "_blank") {
                +"Kotlin/React wrappers"
            }
            +", which uses "
            a(href = "https://reactjs.org", target = "_blank") {
                +"ReactJS"
            }
            +" and "
            a(href = "https://styled-components.com", target = "_blank") {
                +"Styled Components"
            }
            +"."
            br {}
            +"It is hosted on "
            a(href = "https://pages.github.com", target = "_blank") {
                +"Github pages"
            }
            +"."
            br {}
            +"The contact form uses "
            a(href = "https://cloud.google.com/functions", target = "_blank") {
                +"Google Cloud Functions"
            }
            +" and "
            a(href = "https://sendgrid.com", target = "_blank") {
                +"Sendgrid"
            }
            +"."
        }
    }

    child(Footer)

}