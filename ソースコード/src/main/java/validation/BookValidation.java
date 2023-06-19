package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;

public class BookValidation extends Validation {
	
	/*コンストラクタ
	 * @param	request
	 * */
	public BookValidation(HttpServletRequest request) {
		super(request);
	}
	
	/*バリデーションを行う
	 * @return	バリデーションエラーのMap<項目名、内容>
	 * */
	@Override
	public Map<String, String> validate() {
		// 書籍データ・読書記録のバリデーション
		
		// titleの入力確認
		if(!ValidationUtil.isMinLength(this.request.getParameter("title"), 1)) {
			this.errors.put("title", String.format(MessageSettings.MSG_REQUIRED, "タイトル"));
		}
		// titleの文字数制限
		if(!ValidationUtil.isMaxLength(this.request.getParameter("title"), 255)) {
			this.errors.put("title", String.format(MessageSettings.MSG_LENGTH_LONG,"タイトル",255));
		}
		
		// 読書期間（開始）の入力確認
		if(!ValidationUtil.isMinLength(this.request.getParameter("start"), 1)) {
			this.errors.put("start", String.format(MessageSettings.MSG_REQUIRED,"読書期間（開始）"));
		}
		
		return errors;
	}

}
