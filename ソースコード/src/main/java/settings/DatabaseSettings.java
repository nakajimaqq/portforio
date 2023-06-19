package settings;

public class DatabaseSettings {
	
	//データーベース操作成功
	public static final int  DB_EXECUTION_SUCCESS = 1;
	
	//データベース操作失敗
	public static final int DB_EXECUTION_FAILURE = 0;
	
	//ユニークkeyの重複
	public static final int DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME = 1062;
}
