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

@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        Account account = (Account) req.getSession().getAttribute("account");
        String url = req.getRequestURL().toString();

        if (req.getSession().getAttribute("cart") == null) {
            Cart cart = new Cart();
            req.setAttribute("cart", cart);
        }

        if (req.getSession().getAttribute("currentUser") != null) {

        }

        chain.doFilter(req, res);

    }
}
