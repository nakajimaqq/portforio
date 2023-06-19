package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.UserLogic;
import model.UserModel;
import settings.MessageSettings;
import settings.PageSettings;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページへフォワードする。
		RequestDispatcher d = request.getRequestDispatcher(PageSettings.LOGIN_JSP);
		d.forward(request, response);

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// registerがパラメータでnull出なかったら、UserRegisterへリダイレクトする。
		if(request.getParameter("register") != null) {

			response.sendRedirect(request.getContextPath() + "/UserRegister");
			return;
		}
		
		// フォームからのリクエストパラメータを取得する。
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		
		try {
			//リクエストパラメータを元にユーザ検索する。
			UserLogic logic;
			logic = new UserLogic();
			UserModel user = logic.find(user_name,password);
			
			if(user == null) {
				//ユーザが見つからなければ、エラーメッセージをリクエストスコープに設定する。
				request.setAttribute("error", MessageSettings.MSG_LOGIN_FAILURE);
				
				//ログインに使用した情報を再表示するために、リクエストスコープに保存する。
				user = new UserModel();
				user.setUser_name(request.getParameter("user_name"));
				user.setPassword(request.getParameter("password"));
				request.setAttribute("user",user);
				
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.LOGIN_JSP);
				d.forward(request,response);
				
				return;	
			}
			
			//ユーザが見つかった時は、UserModelをSessionに保存し、メインページへリダイレクト。
			//UserModelがSessionに保存されていることでログイン状態を保持。
			//SessionからUserModelを削除することでログアウトとする。
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			
			//Mainへリダイレクトする。
			response.sendRedirect(request.getContextPath() + "/Main");
			return;
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
			//エラーページへフォワード。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			d.forward(request, response);
			
			return;
		}
	}

}
