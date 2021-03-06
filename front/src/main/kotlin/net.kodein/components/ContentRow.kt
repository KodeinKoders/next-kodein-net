package net.kodein.components

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.utils.*
import net.kodein.withBasePath
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledDiv
import styled.styledImg


interface ContentRowProps : RProps {
    var backgroundColor: Color
    var indexPosition: Int?
    var illustration: Illus?
    var bottomLayers: List<Color>
    var additionalStyle: RuleSet?
    var noPadding: Boolean?
}

fun RBuilder.contentRow(
    backgroundColor: Color,
    indexPosition: Int = 1,
    illustration: Illus? = null,
    bottomLayers: List<Color> = emptyList(),
    additionalStyle: RuleSet? = null,
    noPadding: Boolean? = false,
    body: RBuilder.() -> Unit = {}
) = child(ContentRow) {
    attrs {
        this.indexPosition = indexPosition
        this.backgroundColor = backgroundColor
        this.illustration = illustration
        this.bottomLayers = bottomLayers
        this.additionalStyle = additionalStyle
        this.noPadding = noPadding
    }

    body()
}

fun RBuilder.imageRow(
    backgroundColor: Color,
    indexPosition: Int = 1,
    illustration: Illus? = null,
    bottomLayers: List<Color> = emptyList(),
    additionalStyle: RuleSet? = null
) = child(ContentRow) {
    attrs {
        this.indexPosition = indexPosition
        this.backgroundColor = backgroundColor
        this.illustration = illustration
        this.bottomLayers = bottomLayers
        this.additionalStyle = additionalStyle
    }
}

val ContentRow = functionalComponent<ContentRowProps>("ContentRow") { props ->

    val illusGoesLeft by useState { props.illustration?.position == Illus.Position.LEFT }
    val illusGoesRight by useState { props.illustration?.position == Illus.Position.RIGHT }
    val indexPosition by useState { (props.indexPosition ?: 1) * 2 } // 2 is the number of stacked items in the component

    val bottomLayers by useState { LayerParam.generate(listOf(Color.transparent) + props.bottomLayers) }

    flexColumn {
        css { props.additionalStyle?.invoke(this) }

       styledDiv {
           css {
               zIndex = 90 - indexPosition
               +kodein.dropShadow
           }

            flexRow {
                css {
                    width = 100.pct
                    backgroundColor = props.backgroundColor
                    val r = 100.pct - ((bottomLayers[0].coords.first - bottomLayers[0].coords.second) / 10.0).rem
                    put("clip-path", "polygon(0% 0%, 100% 0%, 100% $r, 0% 100%)")
                }

                when (props.illustration?.position) {
                    Illus.Position.CENTER ->
                        child(ContentRowIllustration) {
                            attrs.illustration = props.illustration!!
                        }
                    else -> {
                        if (illusGoesLeft) child(ContentRowIllustration) { attrs.illustration = props.illustration!! }

                        styledDiv {
                            css {
                                flexGrow = if (props.illustration != null) 70.0 else 1.0
                                flexBasis = FlexBasis.zero
                                if (props.noPadding != true) {
                                    padding(10.rem, 2.rem, 6.rem, 2.rem)
                                    maxWidth(980) { padding(8.rem, 2.rem, 6.rem, 2.rem) }
                                    minWidth(1024) { margin(LinearDimension.auto) }
                                }
                            }

                            props.children()
                        }

                        if (illusGoesRight) child(ContentRowIllustration) { attrs.illustration = props.illustration!! }
                    }
                }
            }
        }

        if (props.bottomLayers.isNotEmpty()) {
            styledDiv {
                css {
                    width = 100.pct
                    zIndex = 89 - indexPosition
//                    zIndex = 99 - indexPosition
                    marginTop = (-(bottomLayers[0].coords.first / 10.0)).rem
                }
                layerSeparator(Position.absolute, bottomLayers)
            }
        }

    }
}

private interface ContentRowIllustrationProps : RProps {
    var illustration: Illus
}

private val ContentRowIllustration = functionalComponent<ContentRowIllustrationProps>("ContentRowIllustration") { props ->
    val div = useRef<HTMLDivElement?>(null)

    var image: String? by useState(null)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = illustrationWidths.firstOrNull { it >= (divWidth * 1.2) } ?: illustrationWidths.last()
            image = "${props.illustration.title}_${imgWidth}"
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    styledDiv {
        ref = div
        attrs.classes += "illustration"
        css {
            flexBasis = FlexBasis.zero
            minHeight = 30.rem

            when {
                props.illustration.position != Illus.Position.CENTER -> {
                    flexGrow = 30.0
                }
                else -> {
                    flexGrow = 1.0
                }
            }

            backgroundSize = "cover"
            backgroundPosition = "center"

            minWidth(1080) {
                minHeight = 35.rem
            }
            minWidth(1280) {
                minHeight = 40.rem
            }
            minWidth(1440) {
                minHeight = 45.rem
            }
            minWidth(1920) {
                minHeight = 50.rem
            }
            minWidth(2500) {
                minHeight = 55.rem
            }
            maxWidth(980) {
                if (props.illustration.position != Illus.Position.CENTER)
                    display = Display.none
                else {
                    flexGrow = 1.0
                }
            }

            "body.webp &" {
                backgroundColor = Color.kodein.orange
            }

            "img" {
                objectPosition = props.illustration.alignment.name.toLowerCase()
            }
        }

        if (image != null) {
            withBasePath { path ->
                picture {
                    source("image/webp", "$path/imgs/illus/$image.webp" to null)
                    source("image/jpeg", "$path/imgs/illus/$image.jpg" to null)

                    styledImg(src = "$path/imgs/illus/$image.jpg", alt = image) {
                        attrs {
                            width = "100%"
                            height = "100%"
                        }
                        css {
                            width = 100.pct
                            height = 100.pct
                            objectFit = ObjectFit.cover
                        }
                    }
                }
            }
        }
    }
}
