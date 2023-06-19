package filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import settings.MessageSettings;
import settings.PageSettings;


/**
 * セッションに保存されているトークンとPOSTされてきたトークンを比較する（ワンタイムトークンのチェックを行う）。
 */

/**
 * Servlet Filter implementation class OneTimeTokenCheckFilter
 */
@WebFilter(filterName = "OneTimeTokenCheckFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する。
public class OneTimeTokenCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public OneTimeTokenCheckFilter() {
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

		// getSession()メソッド・getRequestDispatcher()メソッドを使えるようにするために、
		// ServletRequestクラスをHttpServletRequestクラスにキャストする。
		HttpServletRequest req = (HttpServletRequest) request;
		
		//　メソッドが、POSTの時のみ処理する。
		if(req.getMethod().equals("POST")) {
			// HttpSessionインスタンスを取得する。
			HttpSession session = req.getSession(true);
			
			// POSTされてきたトークンの値とセッションスコープに保存されているトークンの値を比較する。
			// Objects.equals(a,b)は、abそれぞれがnullであっても、NullPointerExceptionが発生しない。
			if(!Objects.equals(request.getParameter("token"),session.getAttribute("token"))) {
				// ログインしている場合は、ログアウトさせる。
				session.removeAttribute("user");
				
				//エラーメッセージを設定し、ログインページにフォワード。
				req.setAttribute("error", MessageSettings.MSG_INVALID_PROCESS);
				RequestDispatcher d = req.getRequestDispatcher(PageSettings.LOGIN_JSP);
				d.forward(request, response);
				
				return;
			
			}
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
