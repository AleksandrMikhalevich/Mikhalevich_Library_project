package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
