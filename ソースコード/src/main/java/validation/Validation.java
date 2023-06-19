package validation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Validation {
	
	/*リクエストオブジェクト*/
	protected HttpServletRequest request;
	
	/*エラーを格納するMap（項目名、内容）*/
	protected Map<String,String> errors;
	
	/*コンストラクタ
	 * ＠param	request
	 * */
	public Validation(HttpServletRequest request) {
		this.request = request;
		this.errors = new HashMap<String,String>();
	}
	
	/*バリデーションエラーの有無を判定
	 * @return	true:エラー有り,false:エラー無し*/
	public boolean hasErrors() {
		if(this.errors.size() > 0) {
			return true;
		}
		return false;
	}
	
	/*バリデーションを行う（抽象メソッド）
	 * 実装は派生先クラス
	 * 
	 * @return	バリデーションエラーのMap(項目名、内容)*/
	public abstract Map<String,String> validate();

}
