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
import javax.servlet.http.HttpServletResponse;

/*
 * 追加のヘッダ情報を設定するフィルタ。
 * */

/**
 * Servlet Filter implementation class AddHeaderFilter
 */
@WebFilter(filterName="AddHeaderFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する。
public class AddHeaderFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AddHeaderFilter() {
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
		
		// 文字コードをUTF-8に設定する。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// ServletResponseクラスではsetHeaderメソッドが未定義のため、HttpServletResponseオブジェクトにキャストする。
		HttpServletResponse res = (HttpServletResponse) response;
		
		// キャッシュされないようにヘッダ情報を埋め込む。
		res.setHeader("Cache-Control", "no-cache,private");
		
		// キャッシュの有効期限に過去の日時を設定する。
		res.setHeader("Expires", "Thu, 20 Jan 2022 00:00:00 GMT");
		
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
