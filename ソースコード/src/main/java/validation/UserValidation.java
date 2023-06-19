package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;

public class UserValidation extends Validation {
	
	/*コンストラクタ
	 * @param	request
	 * */
	public UserValidation(HttpServletRequest request) {
		super(request);
	}
	
	/*バリデーションを行う。
	 * @return	バリデーションエラーのMap<項目名、内容>
	 * */
	@Override
	public Map<String, String> validate(){
		// アカウント名の入力確認
		if(!ValidationUtil.isMinLength(this.request.getParameter("user_name"),1)) {
			this.errors.put("user_name", String.format(MessageSettings.MSG_REQUIRED, "アカウント名"));
		}
		// アカウント名の文字数制限
		if(!ValidationUtil.isMaxLength(this.request.getParameter("user_name"), 50)) {
			this.errors.put("user_name", String.format(MessageSettings.MSG_LENGTH_LONG,"アカウント名",50));
		}
		
		// email形式の確認
		if(!ValidationUtil.isEmail(this.request.getParameter("email"))) {
			this.errors.put("email", MessageSettings.MSG_EMAIL_FAILURE);
		}		
		
		// passwordの形式確認
		if(!ValidationUtil.isPassword(this.request.getParameter("password"))) {
			this.errors.put("password", MessageSettings.MSG_PASSWORD_FAILURE);
		}
	
		return errors;
	}

}
