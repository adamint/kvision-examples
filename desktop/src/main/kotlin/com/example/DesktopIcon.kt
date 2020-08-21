/*
 * Copyright (c) 2018. Robert Jaros
 */

package com.example

import pl.treksoft.kvision.core.AlignItems
import pl.treksoft.kvision.core.WhiteSpace
import pl.treksoft.kvision.html.Icon
import pl.treksoft.kvision.html.Span
import pl.treksoft.kvision.panel.VPanel
import pl.treksoft.kvision.utils.px

class DesktopIcon(icon: String, content: String) : VPanel(alignItems = AlignItems.CENTER) {
    init {
        width = 64.px
        height = 64.px
        add(Icon(icon).addCssClass("fa-3x"))
        add(Span(content) {
            whiteSpace = WhiteSpace.NOWRAP
        })
    }
}
