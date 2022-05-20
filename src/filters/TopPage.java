package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.ForwardConst;

/**
 * Servlet Filter implementation class TopPage
 */
@WebFilter("/*")
public class TopPage implements Filter {

    /**
     * Default constructor.
     */
    public TopPage() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String servletPath = ((HttpServletRequest) request).getServletPath();
        String contextPath = ((HttpServletRequest) request).getContextPath();
        String queryString = ((HttpServletRequest) request).getQueryString();

        if (servletPath.matches("/css.*")) {
            // CSSフォルダ内は認証処理から除外する
            chain.doFilter(request, response);
        } else if (servletPath.matches("([^\\s]+(\\.(?i)(jpg|JPG|png|gif|bmp))$)")) {
            //画像形式全てスルーさせる
            chain.doFilter(request, response);

        } else if (queryString == null || queryString.equals("")) {
            ((HttpServletResponse) response).sendRedirect(
                    contextPath
                            + "?action=" + ForwardConst.ACT_BASE.getValue()
                            + "&command=" + ForwardConst.CMD_INDEX.getValue());
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
