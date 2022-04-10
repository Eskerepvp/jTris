package com.pepperoni.jtris.views

import com.vaadin.flow.component.button.*
import com.vaadin.flow.component.html.*
import com.vaadin.flow.component.notification.*
import com.vaadin.flow.component.orderedlayout.*
import com.vaadin.flow.component.textfield.*
import com.vaadin.flow.router.*

@PageTitle("JTris")
@Route(value = "home")
class Home : VerticalLayout() {
    init {
        // variables
        val button = Button("Click Me")
        val name = TextField("Name")

        val hl = HorizontalLayout(name, button)
        hl.defaultVerticalComponentAlignment = FlexComponent.Alignment.BASELINE

        button.addClickListener { click -> Notification.show("Hello, " + name.value) }

        add(H1("Hello World!"))

        add(hl)
        
    }
}