package settings;

public class MessageSettings {

	//UserRegisterで使用(UserUpdateでも使用)
	public static final String MSG_ERROR_DUP_KEYNAME = "%sはすでに登録されているので使用できません。";
	
	public static final String MSG_ERROR_OCCURRED = "申し訳ございません。エラーが発生しました。";
	
	//LoginSercletで使用
	public static final String MSG_LOGIN_FAILURE = "ユーザ名、または、パスワードが間違っています。";
	
	//UserValidationで使用（）
	public static final String MSG_REQUIRED = "%sは必ず入力してください。";

	public static final String MSG_LENGTH_LONG = "%sは%d文字以下で入力してください。";

	public static final String MSG_EMAIL_FAILURE = "正しいemailアドレスを入力してください。";

	public static final String MSG_PASSWORD_FAILURE = "パスワードは、半角英数文字8字以上で入力してください。";
	
	//OneTimeTokenCheckFilterで使用
	public static final String MSG_INVALID_PROCESS = "不正な処理が行われました。";

}
