/**
 *
 */
package core;

import data.Blend;
import data.Spice;

/**
 * @author tanabe
 *
 */
public class CurryProblem {

	/**
	 * ブレンドファイルへのパス
	 */
	private static final String BLENDLIST_PATH = "C:\\Users\\tanabe\\Documents\\CodeIQ\\curry\\blendlist.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("program start");

		System.out.println( "blendlist path => " + BLENDLIST_PATH );
		try {
			BlendlistImporter importer = new BlendlistImporter( BLENDLIST_PATH );

			// スパイスを表示
			for( Spice spice : importer.getSpiceList() ) {
				System.out.println( spice );
			}

			System.out.println();

			// ブレンドを表示
			for( Blend blend : importer.getBlendList() ) {
				System.out.println( blend );
			}
		}
		catch( Exception e ) {
			System.out.println( "読み込み失敗" );
			e.printStackTrace();
		}

		System.out.println("program end");
	}

}
