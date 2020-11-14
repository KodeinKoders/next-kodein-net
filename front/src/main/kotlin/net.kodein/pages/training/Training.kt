package net.kodein.pages.training

import kotlinx.css.*
import kotlinx.css.properties.border
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.training.fragment.Description
import net.kodein.pages.training.fragment.Trainings
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent
import styled.css
import styled.styledImg

val Training = functionalComponent<RProps>("Training") {
        child(MenuTop) {
            attrs{
                animated = true
                topMargin = 1.5.rem
                backgroundColor = Color.kodein.cute
            }
        }

        child(Cover) {
            attrs {
                colors = CoverPalette(
                    backgroundColor = Color.kodein.cute,
                    text = Color.kodein.korail,
                    title = Color.kodein.kyzantium,
                    layers = listOf(Color.kodein.korail, Color.kodein.kaumon, Color.kodein.korail)
                )
                overTitle = "Let's share knowledge!"
                title = {
                    +"Yes we are"
                    br {}
                    +""" "JetBrains Certified Trainer" """
                    br {}
                    styledImg(src = "imgs/badge-orange.svg") {
                        css {
                            width = 10.rem
                            height = 10.rem
                            marginTop = 1.rem
                        }
                    }
                }
            }

            +"""Our Kotlin expertise goes way beyond its first objective, 
                    |for the JVM world. We have the ability to give training and workshops 
                    |for companies and world events, as we already did for  Kotlin/Everywhere Paris 
                    |and KotlinConf'19. We can provide training upon the different level of Kotlin.""".trimMargin()
        }

        child(Description)

        child(Trainings)

        child(ContactUs)

        child(Footer)
}