package security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/*
 * セキュリティ対策クラス
 * */

public class SecurityUtil {

	/*
	 * ワンタイムトークン用の文字列を生成。
	 * @return	ワンタイムトークン用の文字列
	 * */
	public static String generateToken() {
		// 指定の要素数を持つ配列（byte型）を生成する。
		byte token[] = new byte[16];
		
		// 文字列を操作するためのStringBufferクラスのインスタンスを生成する。
		StringBuffer buf = new StringBuffer();
		
		// 乱数を生成するためのSecureRandomクラス。
		SecureRandom random = null;
		
		try {
			// 指定の暗号化アルゴリズム(SHA1PRNG)を使って、SecureRandomクラスのインスタンスを取得する。
			random = SecureRandom.getInstance("SHA1PRNG");
			
			// 乱数を生成する。
			random.nextBytes(token);
			
			for(int i = 0; i < token.length; i++) {
				// formatメソッドを使って16進数に変換する。("%02x"は16進数の意味)
				//　16進数に変換した文字列を後ろへ追加していく。
				buf.append(String.format("%02x", token[i]));
			}
			
			// StrngBufferクラスを文字列に変換して返却する。
			return buf.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
