package com.pepperoni.jtris.views.login

import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.login.LoginForm
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route("login")
@PageTitle("Login | Vaadin CRM")
class LoginView : VerticalLayout(), BeforeEnterObserver {
    var loginForm = LoginForm()

    init {
        addClassName("login-view")
        setSizeFull()
        justifyContentMode = JustifyContentMode.CENTER
        alignItems = FlexComponent.Alignment.CENTER
        loginForm.action = "login"
        add(
            H1("Vaadin Login Screen"),
            loginForm
        )
    }

    override fun beforeEnter(beforeEnterEvent: BeforeEnterEvent) {
        if (!beforeEnterEvent.location.queryParameters
                .parameters.getOrDefault("error", emptyList()).isEmpty()
        ) {
            loginForm.isError = true
        }
    }
}