package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.UserLogic;
import model.UserModel;
import settings.DatabaseSettings;
import settings.MessageSettings;
import settings.PageSettings;
import validation.UserValidation;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ユーザの新規登録フォームへフォワードする。
		RequestDispatcher d = request.getRequestDispatcher(PageSettings.UserRegister_JSP);
		d.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// リクエストパラメータを取得する。
		String user_name = request.getParameter("user_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			// バリデーションチェックを行う。
			UserValidation validate = new UserValidation(request);
			Map<String,String> errors = validate.validate();
			
			// バリデーションエラーがあった場合。
			if(validate.hasErrors()) {
				request.setAttribute("errors", errors);
				
				// jsp<input value値>の表示に使用するリクエストパラメータ値をMapに保存する。
				Map<String,String> user = new HashMap<String,String>();
				user.put("user_name", user_name);
				user.put("email", email);
				user.put("password", password);
				request.setAttribute("user", user);
				
				// ユーザ登録ページへフォワードして終了する。
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.UserRegister_JSP);
				d.forward(request, response);
				
				return;
			}

			// リクエストパラメータをUserModelに設定する。
			UserModel user = new UserModel();
			user.setUser_name(user_name);
			user.setEmail(email);
			user.setPassword(password);

		
			// ユーザを登録する。
			UserLogic logic;
			logic = new UserLogic();
			int ret = logic.create(user);

			// ロジックの実行結果によって処理を切り替える。
			switch (ret) {
			case DatabaseSettings.DB_EXECUTION_SUCCESS:
				// データベース操作成功の時、registered.jspへフォワードして終了。
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.Registered_JSP);
				d.forward(request, response);
				return;

			case DatabaseSettings.DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME:
				// ユニークkey（email）が重複している時、エラーメッセージをリクエストスコープに保存。
				request.setAttribute("db_error", String.format(MessageSettings.MSG_ERROR_DUP_KEYNAME, user.getEmail()));
				break;

			default:
				// その他のエラー時は、エラーメッセージをリクエストスコープへ保存。
				request.setAttribute("db_error", MessageSettings.MSG_ERROR_OCCURRED);
				break;
			}

			// リクエストスコープに、user(UserModel)に設定したリクエストパラメータを保存する。
			request.setAttribute("user", user);

			// userRegister.jspへフォワードする。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.UserRegister_JSP);
			d.forward(request, response);

			return;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			d.forward(request, response);

			return;
		}

	}
}
