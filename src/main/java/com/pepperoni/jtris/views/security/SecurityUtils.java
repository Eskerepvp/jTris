// Shoutout to that random Indian Guy : https://www.youtube.com/channel/UCLL-YGtjPgLxyZpl3xEjmHg

package com.pepperoni.jtris.views.security;

import com.vaadin.flow.server.*;
import com.vaadin.flow.shared.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;

import javax.servlet.http.*;
import java.util.stream.Stream;

public class SecurityUtils {

    private SecurityUtils() {

    }

    /**
     * Tests if the request is an internal framework request. The test consists of
     * checking if the request parameter is present and if its value is consistent
     * with any of the request types known
     *
     * @param request {@link HttpServletRequest}
     * @return true if it is an internalFramework request. False otherwise....!
     */
    static boolean isFrameworkInternalRequest(HttpServletRequest request) {
        final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue != null && Stream.of(HandlerHelper.RequestType.values())
                .anyMatch(r -> r.getIdentifier().equals(parameterValue));
    }

    /**
     * Test is the user is authenticated. As Spring Security always will create an
     * {@link AnonymousAuthenticationToken} we have to ignore those tokens explicitly...!
     *
     * @return
     */

    static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) &&
                authentication.isAuthenticated();
    }
}
