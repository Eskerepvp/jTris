package com.pepperoni.jtris.views.security;

import com.pepperoni.jtris.views.login.LoginView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.*;

public class ConfigureUIServiceList implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            final UI ui = uiInitEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter);
        });
    }

    /**
     * Reroutes the user if they are not authorized to access the view
     * @param beforeEnterEvent
     *          before navigation event with event details
     */

    private void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!LoginView.class.equals(beforeEnterEvent.getNavigationTarget()) &&
                !SecurityUtils.isUserLoggedIn()) {
            beforeEnterEvent.rerouteTo(LoginView.class);
        }
    }
}
