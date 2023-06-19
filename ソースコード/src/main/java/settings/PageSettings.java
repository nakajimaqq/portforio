package settings;

public class PageSettings {
	
	//エラー発生時のフォワード先（JSPファイル）
	public static final String PAGE_ERROR = "/WEB-INF/jsp/error.jsp";
	
	//登録完了時のファワード先　使用先（UserRegisterServlet、）
	public static final String Registered_JSP = "/WEB-INF/jsp/registered.jsp";
	
	//LoginServletで使用
	public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
	
	//LogoutServletで使用
	public static final String LOGOUT_JSP = "/WEB-INF/jsp/logout.jsp";
	
	//UserRegisterServletで使用
	public static final String UserRegister_JSP = "/WEB-INF/jsp/userRegister.jsp";
	
	//UserUpdateServletで使用
	public static final String UserUpdate_JSP = "/WEB-INF/jsp/userUpdate.jsp";
		
	public static final String DeletingConfirmationUser_JSP = "/WEB-INF/jsp/deletingConfirmation_user.jsp";

	//BookRegisterServletで使用
	public static final String BookRegister_JSP = "/WEB-INF/jsp/bookRegister.jsp";
	
	//IndividualServletで使用
	public static final String BookUpdate_JSP = "/WEB-INF/jsp/bookUpdate.jsp";
	
	public static final String Individual_JSP = "/WEB-INF/jsp/individual.jsp";
	
	public static final String DeletingConfirmationBook_JSP = "/WEB-INF/jsp/deletingConfirmation_book.jsp";
	

	public static final String Deleted_JSP = "/WEB-INF/jsp/deleted.jsp";
	//MainServletで使用
	public static final String Main_JSP = "/WEB-INF/jsp/main.jsp";
		

}
