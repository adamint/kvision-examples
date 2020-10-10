package com.example

import pl.treksoft.kvision.core.Container
import pl.treksoft.kvision.core.CssSize
import pl.treksoft.kvision.core.UNIT
import pl.treksoft.kvision.form.text.RichTextInput
import pl.treksoft.kvision.utils.px

class TextEditor : DesktopWindow("Text Editor", "fas fa-edit", 700, 400) {

    override var height: CssSize?
        get() = super.height
        set(value) {
            super.height = value
            if (value?.second == UNIT.px) {
                richText.height = (value.first.toInt() - 93).px
            }
        }

    val richText = RichTextInput()

    init {
        minWidth = 500.px
        minHeight = 180.px
        padding = 3.px
        richText.height = 370.px
        add(richText)
        height = 457.px
    }

    override fun focus() {
        super.focus()
        richText.focus()
    }

    companion object {
        fun run(container: Container) {
            container.add(TextEditor())
        }
    }
}
