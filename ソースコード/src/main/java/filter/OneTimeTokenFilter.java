package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import security.SecurityUtil;


/**
 * ワンタイムトークンを生成してセッションに保存するフィルタ。
 */

/**
 * Servlet Filter implementation class OneTimeTokenFilter
 */
@WebFilter(filterName = "OneTimeTokenFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する。
public class OneTimeTokenFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public OneTimeTokenFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// getMethod()メソッド・getSession()メソッドが使えるように、
		// ServletRequestクラスをHttpServletRequestクラスにキャストする。
		HttpServletRequest req = (HttpServletRequest) request;
		
		// ワンタイムトークンを生成してセッションに保存する。
		// メソッドがGETの時のみ処理を行う。
		if(req.getMethod().equals("GET")) {
			// トークンを生成する。
			String token = SecurityUtil.generateToken();
			
			// HttpSessionインスタンスを取得する。
			HttpSession session = req.getSession(true);
			
			// トークンをセッションスコープに保存する。
			session.setAttribute("token", token);
		}
				
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
