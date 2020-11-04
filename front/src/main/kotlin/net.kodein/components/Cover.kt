package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.lh
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RBuilder
import react.RProps
import react.functionalComponent
import styled.css
import styled.styledH1
import styled.styledP
import styled.styledSpan

data class CoverPalette(
    val backgroundColor: Color,
    val primary: Color = Color.kodein.korail,
    val secondary: Color = Color.kodein.kaumon,
    val layers: List<Color> = listOf(Color.kodein.kyzantium, Color.kodein.orange)
)

interface CoverProps : RProps {
    var colors  : CoverPalette
    var overTitle: String
    var title: RBuilder.() -> Unit
}

val Cover = functionalComponent<CoverProps>("Cover") { props ->
    flexColumn {
        css {
            backgroundColor = props.colors.backgroundColor
            "a" {
                display = Display.inlineBlock

                borderBottom(0.15.rem, BorderStyle.solid, Color.currentColor)
                lineHeight = 0.85.em.lh
                put("text-shadow", "0.03em 0.03em ${Color.kodein.dark}, 0.03em -0.03em ${Color.kodein.dark}, -0.03em 0.03em ${Color.kodein.dark}, -0.03em -0.03em ${Color.kodein.dark}")
            }
        }

        styledP {
            css {
                +kodein.display1
                alignSelf = Align.center
                textAlign = TextAlign.center
                color = props.colors.primary
                paddingTop = 6.rem
                marginBottom = 2.rem
                maxSize(768) {
                    paddingTop = 2.rem
                    marginBottom = 1.rem
                }
                borderBottom(0.05.rem, BorderStyle.solid, Color.kodein.korail)
            }
            +props.overTitle
        }

        styledH1 {
            css {
                +kodein.display3
                specific {
                    fontWeight = FontWeight.hairline
                    textAlign = TextAlign.center

                    maxSize(480) {
                        fontSize = 1.8.rem
                    }
                    maxSize(400) {
                        fontSize = 1.6.rem
                    }
                }
                color = props.colors.secondary
                margin(1.rem, 2.rem)
            }
            props.title(this)
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 5.rem
                opacity = .7
                backgroundColor = props.colors.primary
                margin(1.rem, LinearDimension.auto)
                maxSize(768) {
                    height = 3.rem
                    margin(0.rem, LinearDimension.auto)
                }
                landscapeMobile {
                    margin(1.rem, LinearDimension.auto)
                }
            }
        }

        styledP {
            css {
                +kodein.chapo
                specific {
                    fontWeight = FontWeight.light
                    textAlign = TextAlign.center
                }
                color = props.colors.primary
                width = 65.pct
                padding(2.rem)
                alignSelf = Align.center

                maxSize(768) {
                    width = 85.pct
                    padding(1.rem)
                }
            }

            props.children()
        }
    }
    layerSeparator(Position.absolute, props.colors.backgroundColor, *props.colors.layers.toTypedArray())
}