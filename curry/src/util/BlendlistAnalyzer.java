/**
 *
 */
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import data.Blend;
import data.Spice;

/**
 * @author tanabe
 *
 */
public class BlendlistAnalyzer {

	/**
	 * ブレンドリスト
	 */
	private ArrayList<Blend> blendList;

	/**
	 * スパイスリスト
	 */
	private TreeSet<Spice> spiceSet;

	/**
	 * 次に移動可能なスパイス
	 */
	private HashMap<Spice, ArrayList<Spice>> nextMovableSpiceList;

	/**
	 * 指定ファイルからブレンドリストを読み込みます。
	 * @param path 読み込むファイル
	 * @throws NullPointerException 引数が{@code null}の場合
	 * @throws IOException 読み込みに失敗した場合
	 */
	public BlendlistAnalyzer( String path ) throws IOException {
		if( path == null) {
			throw new NullPointerException();
		}

		this.blendList = new ArrayList<Blend>();
		this.spiceSet = new TreeSet<Spice>();
		this.nextMovableSpiceList = new HashMap<Spice, ArrayList<Spice>>();

		HashMap<Spice, TreeSet<Spice>> nextMovableSpiceListTemp
									= new HashMap<Spice, TreeSet<Spice>>();

		FileReader fReader = null;
		BufferedReader bReader = null;
		try {
			fReader = new FileReader( path );
			bReader = new BufferedReader( fReader );

			String line = bReader.readLine();
			while( line != null ) {
				String[] spices = line.split( " " );

				if( spices.length != 2 ) {
					throw new IOException( "ブレンドリストのフォーマットエラー" );
				}

				Spice sp1 = new Spice( spices[0] );
				Spice sp2 = new Spice( spices[1] );
				Blend blend = new Blend( sp1, sp2 );

				this.spiceSet.add( sp1 );
				this.spiceSet.add( sp2 );
				this.blendList.add( blend );

				if( !nextMovableSpiceListTemp.containsKey( sp1 ) ) {
					nextMovableSpiceListTemp.put( sp1, new TreeSet<Spice>() );
				}
				nextMovableSpiceListTemp.get(sp1).add( sp2 );

				if( !nextMovableSpiceListTemp.containsKey( sp2 ) ) {
					nextMovableSpiceListTemp.put( sp2, new TreeSet<Spice>() );
				}
				nextMovableSpiceListTemp.get( sp2 ).add( sp1 );

				line =bReader.readLine();
			}

			for( Spice key : nextMovableSpiceListTemp.keySet() ) {
				ArrayList<Spice> list = new ArrayList<Spice>();
				list.addAll( nextMovableSpiceListTemp.get( key ) );
				this.nextMovableSpiceList.put( key, list);
			}

		}
		catch( Exception e ) {
			throw new IOException( "読み込み失敗[" + path + "]", e );
		}
		finally {
			if( bReader != null ) {
				try {
					bReader.close();
				}
				catch( Exception e ) {

				}
			}
			if( fReader != null ) {
				try {
					fReader.close();
				}
				catch( Exception e ) {

				}
			}
		}
	}

	/**
	 * ブレンドリストを取得します。
	 * @return ブレンドリスト
	 */
	public List<Blend> getBlendList() {
		return new ArrayList<Blend>( this.blendList );
	}

	/**
	 * スパイスリストを取得します。
	 * @return スパイスリスト
	 */
	public List<Spice> getSpiceList() {
		ArrayList<Spice> list = new ArrayList<Spice>();
		list.addAll( this.spiceSet );
		return list;
	}

	/**
	 * 指定スパイスの次に移動可能なスパイスのリストを取得します。
	 * @param spice 指定スパイス
	 * @return 移動可能なスパイスリスト
	 */
	public List<Spice> getMovableSpiceList( Spice spice ) {
		if( spice == null ) {
			throw new NullPointerException();
		}

		ArrayList<Spice> list = new ArrayList<Spice>( this.nextMovableSpiceList.get( spice ) );
		return list;
	}
}
