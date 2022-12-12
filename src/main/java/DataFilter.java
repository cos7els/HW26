import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/login")
public class DataFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uname = servletRequest.getParameter("uname");
        String psw = servletRequest.getParameter("psw");
        if (!uname.equals("") && !psw.equals("")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ServletContext servletContext = filterConfig.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.html");
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }
}
