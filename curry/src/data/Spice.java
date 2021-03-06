/**
 *
 */
package data;


/**
 * スパイスを表すクラスです。
 * @author tanabe
 *
 */
public class Spice implements Comparable<Spice> {

	/**
	 * スパイスの種類です。
	 */
	private String type;

	/**
	 * 指定種類のスパイスを生成します。
	 * @param type 種類
	 * @throws NullPointerException 引数が{@code null}
	 */
	public Spice( String type )	{
		if( type == null ) {
			throw new NullPointerException();
		}

		this.type = type;
	}

	/**
	 * 種類を取得します。
	 * @return 種類
	 */
	public String getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		return this.type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if( obj == null ) {
			return false;
		}
		if( this == obj ) {
			return true;
		}
		if( !(obj instanceof Spice) ) {
			return false;
		}

		Spice other = (Spice)obj;
		return this.type.equals( other.type );
	}

	@Override
	public String toString() {
		return this.type;
	}

	@Override
	public int compareTo(Spice o) {

		// 種類の辞書順で順序付け
		return this.type.compareTo( o.type );
	}

}
