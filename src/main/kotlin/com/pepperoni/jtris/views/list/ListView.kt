package com.pepperoni.jtris.views

import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.html.Paragraph
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle

@PageTitle("list")
@com.vaadin.flow.router.Route(value = "")
class ListView : VerticalLayout() {
    init {
        setSpacing(false)
        val img: com.vaadin.flow.component.html.Image =
            com.vaadin.flow.component.html.Image("images/empty-plant.png", "placeholder plant")
        img.setWidth("200px")
        add(img)
        add(H2("This place intentionally left empty"))
        add(Paragraph("It’s a place where you can grow your own UI 🤗"))
        setSizeFull()
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER)
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
        getStyle().set("text-align", "center")
    }
}