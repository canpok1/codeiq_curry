/**
 *
 */
package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import data.Blend;
import data.Spice;

/**
 * @author tanabe
 *
 */
public class BlendlistImporter {

	private ArrayList<Blend> blendList;

	private TreeSet<Spice> spiceSet;

	/**
	 * 指定ファイルからブレンドリストを読み込みます。
	 * @param path 読み込むファイル
	 * @throws NullPointerException 引数が{@code null}の場合
	 * @throws IOException 読み込みに失敗した場合
	 */
	public BlendlistImporter( String path ) throws IOException {
		if( path == null) {
			throw new NullPointerException();
		}

		this.blendList = new ArrayList<Blend>();
		this.spiceSet = new TreeSet<Spice>();

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

				line =bReader.readLine();
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
}
