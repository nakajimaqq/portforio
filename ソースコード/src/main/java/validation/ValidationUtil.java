package validation;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

// 外部プログラム（commons-validator-1.7.jar）を使用して作ってる。
public class ValidationUtil {
	/*1
	 * 文字列を調べる。（指定最大文字数以下か）
	 * 
	 * @param	str	文字列
	 * @param	length	文字数
	 *
	 * @return	true:指定最大文字数以下,false:指定最大文字数超過
	 * */
	public static final boolean isMaxLength(String str,int length) {
		return str.length() <= length;
	}
	
	/*2
	 * 文字列を調べる。（指定最小文字数以上か）
	 * 
	 * @param	str	文字列
	 * @param	length	文字数
	 * 
	 * @return	true:指定最小文字数以上,false:指定最小文字数以下
	 * */
	public static final boolean isMinLength(String str,int length) {
		return str.length() >= length;
	}
	
	/*3
	 * 文字列がemail形式として正しいか調べる。
	 * 
	 * @param	email	文字列
	 * 
	 * @return	true:正しい,false:正しくない
	 * 
	 * 
	 * @see		https://blog.mailtrap.io/java-email-validation/#Simple_Email_Validation_in_Java
	 * @see 	https://commons.apache.org/proper/commons-validator/download_validator.cgi
	 * */
	public static final boolean isEmail(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(email);
	}
	
	/*4
	 * 文字列がパスワードとして正しいか（半角英数大文字小文字を取り混ぜて8文字以上20文字以下）
	 * 
	 * @param	password	文字列
	 * 
	 * @return	true:正しい,false:正しくない
	 * */
	public static final boolean isPassword(String password) {
		RegexValidator regex = new RegexValidator("^[0-9a-zA-z]{8,}$");
		return regex.isValid(password);
	}
	
	
			
	
}
