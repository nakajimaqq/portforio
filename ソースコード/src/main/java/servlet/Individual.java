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
 * Servlet implementation class Individual
 */
@WebServlet("/Individual")
public class Individual extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Individual() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Bookアイテムを取得するため、BookLogicとBookModelを生成する。
			BookLogic logic = new BookLogic();
			BookModel book;

			// ログインしているuser情報を取得する。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			// <a>タグでbook_idを受取る：詳しく見る（トップページから送られてきたパラメータ）
			if (request.getParameter("book_id") != null) {
				int book_id = Integer.parseInt(request.getParameter("book_id"));
				// Bookアイテムを取得する。
				book = logic.findOne(user.getId(), book_id);
				session.setAttribute("book", book);

			}
			RequestDispatcher d;
			if (request.getParameter("is_deleted") != null) {

				// 削除確認画面を表示する操作。（DeletingConfirmation_Book.jspへ）
				d = request.getRequestDispatcher(PageSettings.DeletingConfirmationBook_JSP);
				
			} else if (request.getParameter("update") != null) {

				// 編集画面を表示する操作。（bookUpdate.jspへ）
				// 編集ページへフォワード。
				d = request.getRequestDispatcher(PageSettings.BookUpdate_JSP);
				
			} else {

				// 個別表示をする操作。（individual.jspへ）
				// 個別表示ページへフォワード。
				d = request.getRequestDispatcher(PageSettings.Individual_JSP);
				
			}

			d.forward(request, response);

			return;

			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			// エラーページへフォワード。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			d.forward(request, response);

			return;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 書籍データ編集の更新処理。

			// sessionからidを取得するための該当モデルを取得する。
			HttpSession session = request.getSession();
			BookModel forBookId = (BookModel) session.getAttribute("book");

			// リクエストパラメータを取得する。
			String title = request.getParameter("title");
			String author = request.getParameter("author");

			int publication = 0;
			if (request.getParameter("publication") != "" && request.getParameter("publication") != null) {
				publication = Integer.parseInt(request.getParameter("publication"));
			}
			String impressions = request.getParameter("impressions");

			Date start = null;
			if (request.getParameter("start") != "" && request.getParameter("start") != null) {
				start = java.sql.Date.valueOf(request.getParameter("start"));
			}

			Date finish = null;
			if (request.getParameter("finish") != "" && request.getParameter("finish") != null) {
				finish = java.sql.Date.valueOf(request.getParameter("finish"));
			}

			// リクエストパラメータをBookModelに設定する。
			BookModel book = new BookModel();

			book.setTitle(title);
			book.setAuthor(author);
			book.setPublication(publication);
			book.setImpressions(impressions);
			book.setStart(start);
			book.setFinish((finish));
			book.setId(forBookId.getId());

			// Sessionに保存したユーザ情報を取得する。
			// ユーザIDを取得し、BookModelに設定する。
			UserModel user = (UserModel) session.getAttribute("user");

			int user_id = user.getId();
			book.setUsers_id(user_id);

			// 書籍データを処理するためのlogicを生成する。
			BookLogic logic = new BookLogic();

			// 削除の場合
			int is_deleted = 0;
			if (request.getParameter("is_deleted") != null) {
				is_deleted = 1;
				book.setIs_deleted(is_deleted);
				logic.delete(book);
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.Deleted_JSP);
				d.forward(request, response);

				return;
			}

			// バリデーションチェックを行う。
			BookValidation validate = new BookValidation(request);
			Map<String, String> errors = validate.validate();

			// バリデーションエラーがあった場合。
			if (validate.hasErrors()) {
				request.setAttribute("errors", errors);

				// 書籍データ更新ページへフォワードして終了。
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.BookUpdate_JSP);
				d.forward(request, response);

				return;
			}
			
			// 書籍データを更新する。
			if (!logic.update(book)) {
				// エラーがあった時
				request.setAttribute("book", book);
				RequestDispatcher d = request.getRequestDispatcher(PageSettings.BookUpdate_JSP);
				d.forward(request, response);

				return;
			}
			// 更新直後の表示に必要な（book_idを取得するための）modelをセッションに上書き保存する。
			session.setAttribute("book", book);

			// 個別表示画面に戻る。
			response.sendRedirect(request.getContextPath() + "/Individual");
			return;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			// エラーページへフォワード。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			d.forward(request, response);

			return;
		}

	}

}
