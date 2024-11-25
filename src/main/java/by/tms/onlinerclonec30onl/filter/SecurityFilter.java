package by.tms.onlinerclonec30onl.filter;

import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Cart;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SecurityFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        Account account = (Account) req.getSession().getAttribute("currentUser");
        String url = req.getRequestURL().toString();

        if (req.getSession().getAttribute("cart") == null) {
            Cart cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        if ((req.getSession().getAttribute("currentUser") == null) &&
                (url.contains("/shop") || url.contains("/admin") || url.contains("/profile"))) {
            res.sendRedirect("/error?message=You are not logged in!");
            return;
        }

        if (req.getSession().getAttribute("currentUser") != null) {
            if (url.contains("/login") || url.contains("/reg")) {
                res.sendRedirect("/error?message=You are already logged in!");
                return;
            } else if (account.getRole().name().equals("USER") &&
                    (url.contains("/admin") || url.contains("/shop"))) {
                res.sendRedirect("/error?message=Access denied!");
                return;
            } else if (account.getRole().name().equals("BUSINESS") && url.contains("/admin")) {
                res.sendRedirect("/error?message=Access denied!");
                return;
            }
        }

        chain.doFilter(req, res);

    }
}
