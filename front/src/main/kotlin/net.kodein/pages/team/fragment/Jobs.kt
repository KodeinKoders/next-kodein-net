package net.kodein.pages.team.fragment

import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.html.id
import net.kodein.charter.kodein
import net.kodein.components.AccordionElement
import net.kodein.components.ContactUsProps
import net.kodein.components.contentRow
import net.kodein.components.imageRow
import net.kodein.useText
import net.kodein.utils.Illus
import net.kodein.utils.maxWidth
import react.child
import react.dom.div
import react.functionalComponent


val Jobs = functionalComponent<ContactUsProps>("Members") {
    val strings = useText().team

    imageRow(
        backgroundColor = Color.kodein.kinzolin, indexPosition = 1,
        illustration = Illus(Illus.Title.TEAM, Illus.Position.CENTER, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.kuivre)
    ) {
        display = Display.none
        maxWidth(980) { display = Display.flex }
    }

    div { attrs.id = "jobs" }

    contentRow(
        backgroundColor = Color.kodein.kyzantium, indexPosition = 2,
        illustration = Illus(Illus.Title.TEAM, Illus.Position.RIGHT, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kaumon)
    ) {
        strings.jobs.forEachIndexed { index, job ->
            child(AccordionElement) {
                attrs {
                    title = job.title
                    last = (index == strings.jobs.size - 1)
                    borderColor = Color.kodein.orange
                    fontColor = Color.kodein.orange
                }

                job.content(this)
            }
        }
    }
}