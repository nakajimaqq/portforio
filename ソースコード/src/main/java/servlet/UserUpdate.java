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
import javax.servlet.http.HttpSession;

import logic.UserLogic;
import model.UserModel;
import settings.DatabaseSettings;
import settings.MessageSettings;
import settings.PageSettings;
import validation.UserValidation;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//削除ボタンをクリックされたら削除確認ページへフォワードする。
				if(request.getParameter("is_deleted") != null) {
					
					RequestDispatcher d = request.getRequestDispatcher(PageSettings.DeletingConfirmationUser_JSP);
					d.forward(request, response);
					
					return;
					
				}
		
		//ユーザ更新ページへフォワード。
		RequestDispatcher d = request.getRequestDispatcher(PageSettings.UserUpdate_JSP);
		d.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// ユーザIDをSessionから取得する。(ログイン中のIDを取得している。)
			HttpSession session = request.getSession();
			UserModel forUserId = (UserModel)session.getAttribute("user");
			int id = forUserId.getId();
			
			//リクエストパラメータをUserModelに設定する(削除で使用するパラメータ)。
			UserModel userModel = new UserModel();
			userModel.setId(id);
			
			UserLogic logic = new UserLogic();
			int ret;
			
			int is_deleted = 0;
						
			//ユーザ削除の場合
			if(request.getParameter("is_deleted") != null) {
				is_deleted = 1;
				userModel.setIs_deleted(is_deleted);
				ret = logic.delete(userModel);
				
				switch(ret) {
				case DatabaseSettings.DB_EXECUTION_SUCCESS:
					//データベース操作が成功の時、registered.jspへフォワードする。
					RequestDispatcher d = request.getRequestDispatcher(PageSettings.Registered_JSP);
					d.forward(request, response);
					
					return;
					
				default:
					//その他のエラーの時、エラーメッセージをリクエストスコープに保存。
					request.setAttribute("db_error",MessageSettings.MSG_ERROR_OCCURRED);
					break;
				}
				
			}
		
			// リクエストパラメータを取得する。
			String user_name = request.getParameter("user_name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			
			// バリデーションチェックを行う。
			UserValidation validate = new UserValidation(request);
			Map<String,String> errors = validate.validate();
			
			// バリデーションエラーがあった場合。
			if(validate.hasErrors()) {
				request.setAttribute("errors",errors);
				
				// JSP<input value値>の表示に使用するためのリクエストパラメータをMapに保存。
				Map<String,String> user = new HashMap<String,String>();
				user.put("user_name", user_name);
				user.put("email", email);
				user.put("password", password);
				request.setAttribute("user", user);
				
				// ユーザ情報更新ページへフォワードする。
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.UserUpdate_JSP);
				d.forward(request, response);
				
				return;
				
			}
			
			
			//リクエストパラメータをUserModelに設定する。(更新で使用するパラメータ)
			userModel.setUser_name(user_name);
			userModel.setPassword(password);
			userModel.setEmail(email);
			
			//ユーザ登録する。
			ret = logic.update(userModel);
			
			//実行結果によって処理を切り替える。
			switch(ret) {
			case DatabaseSettings.DB_EXECUTION_SUCCESS:
				// データベース操作が成功の時、registered.jspへフォワードする。
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.Registered_JSP);
				d.forward(request, response);
				
				return;
			
			case DatabaseSettings.DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME:
				// ユニークkey（email）が重複している時、エラーメッセージをリクエストスコープに保存。
				request.setAttribute("db_error", String.format(MessageSettings.MSG_ERROR_DUP_KEYNAME, userModel.getEmail()));
				break;
				
			default:
				//その他のエラーの時、エラーメッセージをリクエストスコープに保存。
				request.setAttribute("db_error",MessageSettings.MSG_ERROR_OCCURRED);
				break;
			}
			
			//リクエストスコープに、user(UserModel)を保存する。
			request.setAttribute("user", userModel);
			
			//ユーザ情報更新ページへフォワードする。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.UserUpdate_JSP);
			d.forward(request, response);
			
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
