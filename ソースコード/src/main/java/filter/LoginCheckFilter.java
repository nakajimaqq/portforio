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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import settings.PageSettings;

/**
 * ログインチェックを行う。
 * セッションにユーザー情報が登録されているかを確認して、登録されていなければログイン画面にリダイレクトする。
 */

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName = "LoginCheckFilter") //フィルタを実行するURLは、/WEB-INF/web.xmlで指定。
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
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
		
		// ServletRequestクラスでは、getSession()メソッド・getContextPath()メソッドが使えないので、
		// HttpServletRequestクラスにキャストする。
		HttpServletRequest req = (HttpServletRequest) request;
		
		// ServletResponseクラスでは、sendRedirect()メソッドが使えないので、
		// HttpServletResponseクラスにキャストする。
		HttpServletResponse res = (HttpServletResponse) response;

		// セッションスコープを取得する。
		HttpSession session = req.getSession(true);
		
		// セッションスコープに保存されているユーザー情報がないとき
		if(session.getAttribute("user") == null) {
			// ログインページへリダイレクトする。
			res.sendRedirect(req.getContextPath() + PageSettings.LOGIN_JSP);
			
			//ここでreturnしないと「レスポンスをコミットした後でフォワードできません」と例外が発生する。
			return;
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
