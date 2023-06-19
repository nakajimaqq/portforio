package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookModel;

public class BookDAO {

	/* 基本となるselect文 */

	private final String BASE_SQL = "SELECT b.id,b.users_id,b.title,b.author,b.publication,b.start,b.finish,b.impressions,b.is_deleted,b.create_date_time,b.update_date_time,"
			+ "u.user_name,u.email,u.password,u.is_deleted,u.create_date_time,u.update_date_time " + "FROM books AS b "
			+ "INNER JOIN users AS u " + "ON b.users_id = u.id ";

	/*
	 * 1 指定ユーザIDのBookリストを取得
	 * 
	 * @param user_id(UserModel id)
	 * 
	 * @param limit 取得するレコード数（リミット値）
	 * 
	 * @param offset 取得開始する行数（オフセット値）
	 * 
	 * @return BookModelのArrayList
	 */
	public List<BookModel> findByUserId(Connection connection, int user_id, int limit, int offset) {
		List<BookModel> list = new ArrayList<BookModel>();
		try {
			// SQL文を設定する。
			String sql = BASE_SQL + "WHERE b.is_deleted = 0 " + "AND u.id = ? " + "ORDER BY b.finish IS NULL DESC , b.start DESC , b.finish DESC "
					+ "LIMIT ? OFFSET ?";

			// SQLの実行準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, user_id);
				stmt.setInt(2, limit);
				stmt.setInt(3, offset);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						BookModel model = new BookModel();

						model.setId(rs.getInt("id"));
						model.setUsers_id(rs.getInt("users_id"));
						model.setTitle(rs.getString("title"));
						model.setAuthor(rs.getString("author"));
						model.setPublication(rs.getInt("publication"));
						model.setStart(rs.getDate("start"));
						model.setFinish(rs.getDate("finish"));
						model.setImpressions(rs.getString("impressions"));
						model.setIs_deleted(rs.getInt("is_deleted"));
						model.setCreate_date_time(rs.getTimestamp("update_date_time"));
						model.setUpdate_date_time(rs.getTimestamp("update_date_time"));

						list.add(model);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
		return list;
	}

	/*
	 * 2 指定ユーザIDのBookリストを全件取得
	 * 
	 * @param Connection onnection データベースコネクションのインスタンス
	 * 
	 * @param user_id(UserModel id)
	 * 
	 * @return BookModelのArrayList
	 */

	public List<BookModel> findByUserId(Connection connection, int user_id) {
		return findByUserId(connection, user_id, Integer.MAX_VALUE, 0);
	}

	/*
	 * 3 指定ユーザIDのBookアイテムを1件取得（個別表示）
	 * 
	 * @param Connection onnection データベースコネクションのインスタンス
	 * 
	 * @param user_id(UserModel id)
	 * 
	 * @param book_id(BookModel id)
	 * 
	 * @return BookModel
	 */
	public BookModel findOne(Connection connection, int user_id, int book_id) {

		BookModel model = new BookModel();

		// SQL文を設定する。
		try {
			String sql = BASE_SQL + "WHERE b.is_deleted = 0 " + "AND u.id = ? " + "AND b.id = ? ";

			// SQLの実行準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, user_id);
				stmt.setInt(2, book_id);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// 実行結果を取得する。
					if (rs.next()) {
						model.setId(rs.getInt("id"));
						model.setUsers_id(rs.getInt("users_id"));
						model.setTitle(rs.getString("title"));
						model.setAuthor(rs.getString("author"));
						model.setPublication(rs.getInt("publication"));
						model.setStart(rs.getDate("start"));
						model.setFinish(rs.getDate("finish"));
						model.setImpressions(rs.getString("impressions"));
						model.setUpdate_date_time(rs.getTimestamp("update_date_time"));
						model.setCreate_date_time(rs.getTimestamp("create_date_time"));

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
	 * 4 指定ユーザIDのBookアイテムを1件更新
	 * 
	 * @param Connection onnection データベースコネクションのインスタンス
	 * 
	 * @param BookModel model
	 * 
	 * @return 結果(true:成功,false:失敗)
	 */
	public boolean update(Connection connection, BookModel model) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE books " + "SET " + "title=?," + "author=?," + "publication=?," + "impressions=?,"
					+ "is_deleted=?," + "start=?," + "finish=? " + "WHERE id=?";

			// SQLの実行準備を行う。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setString(1, model.getTitle());
				stmt.setString(2, model.getAuthor());
				stmt.setInt(3, model.getPublication());
				stmt.setString(4, model.getImpressions());
				stmt.setInt(5, model.getIs_deleted());
				stmt.setDate(6, model.getStart());
				stmt.setDate(7, model.getFinish());
				stmt.setInt(8, model.getId());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	/*
	 * 5 指定ユーザIDのBookアイテムを新規登録
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * @param BookModel model
	 * 
	 * @return 結果(true:成功,false:失敗)
	 */
	public boolean create(Connection connection, BookModel model) {
		try {
			// SQL文を設定する。
			String sql = "INSERT INTO books(" + "users_id," + "title," + "start," + "finish," + "author,"
					+ "publication," + "impressions," + "is_deleted) " + "VALUES(" + "?," + // users_id
					"?," + // title
					"?," + // start
					"?," + // finish
					"?," + // author
					"?," + // publication
					"?," + // impression
					"?)"; // is_deleted
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, model.getUsers_id());
				stmt.setString(2, model.getTitle());
				stmt.setDate(3, model.getStart());
				stmt.setDate(4, model.getFinish());
				stmt.setString(5, model.getAuthor());
				stmt.setInt(6, model.getPublication());
				stmt.setString(7, model.getImpressions());
				stmt.setInt(8, model.getIs_deleted());

				// SQLを実行する。
				stmt.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	/*
	 * 6 指定ユーザIDのBookアイテムを削除（論理削除）
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * 
	 * @param BookModel model
	 * 
	 * @return 結果(1:成功,その他:エラーコード)
	 */
	public int delete(Connection connection, BookModel model) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE books " +
					 	"SET is_deleted = ? " +
					 	"WHERE id = ? ";
			
			// SQL文の実行準備をする。
			try(PreparedStatement stmt = connection.prepareStatement(sql)){
				// パラメータを設定する。
				stmt.setInt(1, model.getIs_deleted());
				stmt.setInt(2, model.getId());
				
				// SQLを実行する。
				stmt.executeUpdate();
			}
		}catch (SQLException e){
			e.printStackTrace();
			return e.getErrorCode();
		
		}
		return 1;
	}

}
