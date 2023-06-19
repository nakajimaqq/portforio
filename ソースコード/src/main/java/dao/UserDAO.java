package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserModel;

public class UserDAO {

	/*
	 * 1 ユーザを1件登録
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * @param model UserModel
	 * 
	 * @return 実行結果（1:成功,その他:エラーコード）
	 */
	public int create(Connection connection, UserModel model) {
		try {
			// SQL文を設定
			String sql = "INSERT INTO users(user_name,email,password,is_deleted) VALUES(?,?,?,?)";

			// SQLを実行する準備
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定
				stmt.setString(1, model.getUser_name());
				stmt.setString(2, model.getEmail());
				stmt.setString(3, model.getPassword());
				stmt.setInt(4, model.getIs_deleted());
				

				// SQLを実行
				stmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}
		return 1;
	}

	/*
	 * 2 指定のuser_nameとpasswordが一致するユーザを1件取得
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * @param user_name ユーザ名
	 * 
	 * @param passwors パスワード
	 * 
	 * @return 検索結果（UserModel）
	 */
	public UserModel findOne(Connection connection, String user_name, String password) {
		// レコードを格納するUserModelのインスタンスを生成する。
		UserModel model = new UserModel();

		try {
			// SQL文を設定する。
			String sql = "SELECT * FROM users WHERE is_deleted = 0 AND user_name = ? AND password = ? ";

			// SQLを実行する準備。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setString(1, user_name);
				stmt.setString(2, password);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果を取得する。
					if (rs.next()) {
						model.setId(rs.getInt("id"));
						model.setEmail(rs.getString("email"));
						model.setPassword(rs.getString("password"));
						model.setUser_name(rs.getString("user_name"));
						model.setIs_deleted(rs.getInt("is_deleted"));
						model.setCreate_date_time(rs.getTimestamp("create_date_time"));
						model.setUpdate_date_time(rs.getTimestamp("update_date_time"));

					} else {
						model = null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return model;
	}

	/*
	 * 3 指定のユーザIDのユーザを1件更新
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * @param model UserModel
	 * 
	 * @return 実行結果（成功:1,その他:エラーコード）
	 */
	public int update(Connection connection, UserModel model) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE users SET user_name = ?,password = ?,email = ?,is_deleted = ? WHERE id = ?";

			// SQLを実行する準備する。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setString(1, model.getUser_name());
				stmt.setString(2, model.getPassword());
				stmt.setString(3, model.getEmail());
				stmt.setInt(4, model.getIs_deleted());
				stmt.setInt(5, model.getId());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}

		return 1;
	}

	/*
	 * 4 指定のユーザIDのユーザを削除（論理削除）
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * @param user UserModel
	 * 
	 * @return 実行結果（成功:1,その他:エラーコード）
	 */
	public int delete(Connection connection, UserModel user) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE users " +
						"SET is_deleted = ? " +
						"WHERE id = ? ";
		
			// SQLの実行準備をする。
			try(PreparedStatement stmt = connection.prepareStatement(sql)){
				// パラメータを設定する。
				stmt.setInt(1, user.getIs_deleted());
				stmt.setInt(2, user.getId());
				
				// SQLを実行する。
				stmt.executeUpdate();
				
			}
		} catch (SQLException e){
		e.printStackTrace();
		return e.getErrorCode();
		
		}
		return 1;
	}
	
	/*
	 * 5 登録されているユーザのemailを全件、リストにして取得する。
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * 
	 * @return 	emailList
	 
	public List<String> emailList(Connection connection) {
		List<String> emailList = new ArrayList<String>();
		try {
			// SQL文を設定する。
			String sql = "SELECT email " +
						"FROM users ";
			// SQLの実行準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				
				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						
					emailList.add(rs.getString("email"));
					}
				}
			
			}
		} catch (SQLException e){
			e.printStackTrace();
	
			return null;
			
		}
		return emailList;
	}
	*/
	
}
