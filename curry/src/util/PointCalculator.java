/**
 *
 */
package util;

import java.util.ArrayList;
import java.util.Collection;

import data.Blend;
import data.Curry;

/**
 * カレーの点数を計算するクラスです。
 * @author tanabe
 *
 */
public class PointCalculator {

	/**
	 * ブレンド一覧
	 */
	private ArrayList<Blend> blendList;

	/**
	 * 指定のブレンド一覧を用いて点数を計算するクラスを生成します。
	 * @param blends ブレンド一覧
	 */
	public PointCalculator( Collection<Blend> blends ) {
		if( blends == null ) {
			throw new NullPointerException();
		}

		this.blendList = new ArrayList<Blend>( blends );
	}

	/**
	 * カレーの得点を計算します。
	 * @param curry カレー
	 * @return 得点
	 */
	public int calculate( Curry curry ) {
		if( curry == null ) {
			throw new NullPointerException();
		}

		int point = 0;
		for( Blend blend : this.blendList ) {
			if( !curry.isContain( blend.getSpice1() ) ) {
				continue;
			}
			if( !curry.isContain( blend.getSpice2() ) ) {
				continue;
			}

			point++;
		}

		return point;
	}
}
