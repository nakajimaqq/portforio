package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable{
/*
 * データベースコネクション（接続）クラス
 * AutoCloseableについては、APIのドキュメントの「インタフェースAutoCloseable」の項を参照。
 * @see	https://docs.oracle.com/javase/jp/8/docs/api/java/lang/AutoCloseable.html
 * */
	
	/*データベースのコネクション*/
	private Connection connection;
	
	/*
	 * コンストラクタ
	 * @throws	ClassNotFoundException
	 * @throws	SQLException
	 * */
	public DBConnection() throws ClassNotFoundException,SQLException{
		// JDBCドライバを読み込む。
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost/library_card?connectionCollation=utf8mb4_general_ci","root","root");
		
	}
	
	/*
	 * データベースコネクションのインスタンスを返却します。
	 * 
	 * @return	データベースコネクションのインスタンス
	 * @throws	ClassNotFoundException
	 * @throws	SQLException
	 * */
	
	public Connection getInstance() throws ClassNotFoundException,SQLException{
		//データベースコネクションの返却
		return this.connection;
	}
	
	/*
	 * データベースコネクションを閉じる
	 * @throws	Exception
	 * */
	@Override
	public void close() {
		try {
			this.connection.close();
		}catch(Exception e) {
			//何もしない
		}
	}
}
