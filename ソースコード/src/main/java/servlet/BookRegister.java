package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.BookLogic;
import model.BookModel;
import model.UserModel;
import settings.PageSettings;
import validation.BookValidation;

/**
 * Servlet implementation class BookRegister
 */
@WebServlet("/BookRegister")
public class BookRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 書籍データの新規登録フォームへフォワードする。
		RequestDispatcher d = request.getRequestDispatcher(PageSettings.BookRegister_JSP);
		d.forward(request, response);

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する。
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		
		int publication = 0;
		if(request.getParameter("publication") != "" && request.getParameter("publication") != null) {
				publication = Integer.parseInt(request.getParameter("publication"));
		}
		String impressions = request.getParameter("impressions");
		
		Date start = null;
		if (request.getParameter("start") != "" && request.getParameter("start") != null) {
			start = java.sql.Date.valueOf(request.getParameter("start"));
			}
		
		Date finish = null;
		if(request.getParameter("finish") != "" && request.getParameter("finish") != null) {
			finish = java.sql.Date.valueOf(request.getParameter("finish"));
		}
		
		try {
			// バリデーションチェックを行う。
			BookValidation validate = new BookValidation(request);
			Map<String,String> errors = validate.validate();
			
			// バリデーションエラーがあった場合。
			if(validate.hasErrors()) {
				request.setAttribute("errors", errors);
				
				// 書籍データ登録ページへフォワードして終了。
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.BookRegister_JSP);
				d.forward(request, response);
				
				return;
			}
		
			// セッションからUserModelを取得する。
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
		
			// リクエストパラメータをBookModelに設定する。
			BookModel book = new BookModel();
			book.setTitle(title);
			book.setStart(start);
			book.setFinish(finish);
			book.setAuthor(author);
			book.setPublication(publication);
			book.setImpressions(impressions);	
			book.setIs_deleted(0);
		
			// ユーザID（UserModel　id）を取得し、BookModel(BookModel users_id)に設定する。
			book.setUsers_id(user.getId());
		
			// 書籍データの新規登録をする。
			BookLogic logic = new BookLogic();
		
			if(!logic.create(book)) {
				// エラーがあった時、へフォワードする。
				 // request.setAttribute("book", book); 登録できなかった時の表示に使う
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.BookRegister_JSP);
				d.forward(request, response);
				
				return;
				
			}
			// 個別表示するときに必要なbook_idを取得するためのmodelをセッションに保存する。
			session.setAttribute("book", book);
			
			// 新規登録した書籍データの個別表示へ戻るためindividual.javaへリダイレクトする。
			response.sendRedirect(request.getContextPath() + "/Individual");
			
			return;
		
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
			// エラーページへフォワードする。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			d.forward(request, response);

			return;
		}
	}

}
