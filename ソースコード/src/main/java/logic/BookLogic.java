package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BookDAO;
import database.DBConnection;
import model.BookModel;

public class BookLogic {
	
	/*1
	 * 指定ユーザIDのBookリストを取得
	 * 
	 * @param	user_id(UserModel id)
	 * @return	BookModelのArrayList
	 * @throws 	SQLException
	 * @throws 	ClassNotFoundException
	 * */
	public List<BookModel> find(int user_id)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			BookDAO dao = new BookDAO();
			
			return dao.findByUserId(conn,user_id);
		}
	}
	

	/*2
	 * 指定ユーザIDのBookアイテムを1件取得(個別表示)
	 * 
	 * @param 	user_id(UserModel id)
	 * @param 	book_id(BookModel id)
	 * @return	BookModel
	 */
	public BookModel findOne(int user_id,int book_id)throws ClassNotFoundException,SQLException {
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			BookDAO dao = new BookDAO();
			
		return dao.findOne(conn,user_id,book_id);
	}


}
	/*3
	 * 指定ユーザIDのBookアイテムを更新
	 * 
	 * @param 	BookModel model
	 * @return	結果(true:成功,false:失敗)
	 */
	public boolean update(BookModel model) throws ClassNotFoundException,SQLException {
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			BookDAO dao = new BookDAO();
			
			return dao.update(conn, model);
		}
	}

	/*4
	 * 指定ユーザIDのBookアイテムを新規登録
	 * 
	 * @param 	BookModel model
	 * @return	結果(true:成功,false:失敗)
	 */
	public boolean create(BookModel model) throws ClassNotFoundException, SQLException {
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			BookDAO dao = new BookDAO();
			
			return dao.create(conn,model);
		}
	}

	/*5
	 * 指定ユーザIDのBookアイテムを削除
	 * 
	 * @param 	BookModel model
	 * @return	結果(1:成功,その他:エラーコード)
	 */
	public int delete(BookModel model) throws ClassNotFoundException, SQLException {
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			BookDAO dao = new BookDAO();
			
			return dao.delete(conn,model);
		}
	}
	
	
	
	
}