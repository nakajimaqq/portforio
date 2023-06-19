package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 書籍データのリスト(BOOKリスト）を取得するためのLogicインスタンスを生成する。
			BookLogic logic;
			logic = new BookLogic();
			
			// セッションスコープに保存されてるユーザ情報を取得する。
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			
			List<BookModel> books = null;
			
			// 指定ユーザIDのBookアイテム（リスト）を取得して、リクエストスコープに保存する。
			books = logic.find(user.getId());
			request.setAttribute("books",books);
			
			// メインページへフォワードする。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.Main_JSP);
			d.forward(request, response);

			return;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワード。
			RequestDispatcher d = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			d.forward(request, response);

			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
