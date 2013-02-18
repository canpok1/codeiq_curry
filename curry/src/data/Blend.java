/**
 *
 */
package data;

/**
 * ブレンドを表すクラスです。
 * @author tanabe
 *
 */
public class Blend {

	/**
	 * スパイス1
	 */
	private Spice spice1;

	/**
	 * スパイス2
	 */
	private Spice spice2;

	/**
	 * 二つのスパイスからなるブレンドを生成します。
	 * @param sp1 スパイス1
	 * @param sp2 スパイス2
	 * @throws NullPointerException 引数が{@code null}
	 */
	public Blend( Spice sp1, Spice sp2 ) {
		if( sp1 == null || sp2 == null ) {
			throw new NullPointerException();
		}

		if( sp1.getType().compareTo( sp2.getType() ) <= 0 )
		{
			this.spice1 = sp1;
			this.spice2 = sp2;
		}
		else
		{
			this.spice1 = sp2;
			this.spice2 = sp1;
		}
	}

	/**
	 * スパイス1を取得します。
	 * @return スパイス1
	 */
	public Spice getSpice1() {
		return this.spice1;
	}

	/**
	 * スパイス2を取得します。
	 * @return スパイス2
	 */
	public Spice getSpice2() {
		return this.spice2;
	}

	/**
	 * このブレンドに指定スパイスが含まれるかを判定します。
	 * @param sp スパイス
	 * @return true:含まれる false:含まれない
	 * @throws NullPointerException 引数が{@code null}
	 */
	public boolean isContain( Spice sp ) {
		if( sp == null ) {
			throw new NullPointerException();
		}

		if( sp.equals( this.spice1 ) || sp.equals( this.spice2 ) ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "[" + this.spice1 + ", " + this.spice2 + "]";
	}

}
