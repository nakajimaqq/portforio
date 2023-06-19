package logic;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDAO;
import database.DBConnection;
import model.UserModel;

public class UserLogic {

	
	/*1
	 * ユーザを1件登録
	 * 
	 * @param	model	UserModel
	 * @return	実行結果　(1:成功,0:失敗,その他:エラーコード(SQLSTATE))
	 * @throws	SQLException
	 * @throws	ClassNotFoundException
	 * */
	public int create(UserModel model)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.create(conn, model);
		}
	}
	
	/*2
	 * 指定のuser_nameとpasswordが一致するユーザを1件取得
	 * 
	 * @param	user_name	ユーザ名
	 * @param	passwors	パスワード
	 * @return	検索結果（UserModel）
	 * @throws	SQLException
	 * @throws	ClassNotFoundException
	 * */
	public UserModel find(String user_name,String password)throws SQLException,ClassNotFoundException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.findOne(conn,user_name,password);
					
		}
	}
	
	/**3
	 * 指定ユーザIDのユーザを1件更新
	 * 
	 * @param	model	UserModel
	 * @return	実行結果	(1:成功、0:失敗、その他:エラーコード)
	 * @throws	SQLException
	 * @throws	ClassNotFoundException
	 */
	public int update(UserModel model)throws SQLException,ClassNotFoundException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.update(conn,model);
		}
	}
	

	/**4
	 * 指定ユーザIDのユーザを削除
	 * 
	 * @param	user	UserModel
	 * @return	実行結果	(1:成功、0:失敗、その他:エラーコード)
	 * @throws	SQLException
	 * @throws	ClassNotFoundException
	 */

	public int delete(UserModel user)throws SQLException,ClassNotFoundException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.delete(conn,user);
		}
	}
	

	/**5
	 * 登録されているユーザのemailを全件、リストにして取得する。
	 * 
	 * @return	検索結果	emailList
	 * 
	 * @throws	SQLException
	 * @throws	ClassNotFoundException
	 

	public List<String> emailList()throws SQLException,ClassNotFoundException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.emailList(conn);
		}
	}
	*/
}
