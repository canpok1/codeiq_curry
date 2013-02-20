/**
 *
 */
package data;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * カレーを表すクラスです。
 * @author tanabe
 *
 */
public class Curry {

	/**
	 * カレーを構成するスパイス
	 */
	private SortedSet<Spice> spices;

	/**
	 * 指定スパイスを持つカレーを生成します。
	 * @param spice スパイス
	 */
	public Curry( Spice spice ) {
		if( spice == null ) {
			throw new NullPointerException();
		}

		this.spices = new TreeSet<Spice>();
		this.spices.add( spice );
	}

	/**
	 * 同じスパイスを持つ新しいカレーを生成します。
	 * @param original 元になるカレー
	 */
	public Curry( Curry original ) {
		if( original == null ) {
			throw new NullPointerException();
		}

		this.spices = new TreeSet<Spice>( original.spices );
	}

	/**
	 * スパイスを追加します。
	 * @param spice スパイス
	 */
	public void addSpice( Spice spice ) {
		if( spice == null ) {
			throw new NullPointerException();
		}

		this.spices.add( spice );
	}

	/**
	 * スパイスの一覧を取得します。
	 * @return スパイス一覧
	 */
	public List<Spice> getSpiceList() {
		List<Spice> list = new ArrayList<Spice>();
		list.addAll( this.spices );
		return list;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		for( Spice spice : this.spices ) {
			buffer.append( spice.toString() + "");
		}

		return buffer.toString();
	}


}
