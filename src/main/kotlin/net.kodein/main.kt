package net.kodein

import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.*
import net.kodein.utils.*
import react.dom.render
import styled.injectGlobal


fun main() {
    render(document.getElementById("app")) {
        injectGlobal {
            universal {
                margin(0.px)
                padding(0.px)

                minSize(min = 280) { fontSize = 5.px }
                minSize(min = 360) { fontSize = 6.px }
                minSize(min = 440) { fontSize = 7.px }
                minSize(min = 520) { fontSize = 8.px }
                minSize(min = 600) { fontSize = 9.px }
                minSize(min = 680) { fontSize = 10.px }
                minSize(min = 840) { fontSize = 11.px }
                minSize(min = 920) { fontSize = 12.px }
                minSize(min = 1024) { fontSize = 14.px }
                minSize(min = 1280) { fontSize = 16.px }
                minSize(min = 2000) { fontSize = 18.px }
                minSize(min = 2500) { fontSize = 20.px }
                minSize(min = 3000) { fontSize = 22.px }
            }

            body {
                fontFamily = "Picon, sans-serif"
//                minWidth = 380.px
            }

            a {
                textDecoration = TextDecoration.none
                color = Color.inherit
                transition(::color, 0.2.s)

                hover {
                    color = Color.kodein.purple
                }

            }
        }

        child(App::class) {}
    }
}
