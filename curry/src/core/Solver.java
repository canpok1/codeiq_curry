/**
 *
 */
package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import buffer.MovePatternBuffer;
import buffer.PointBuffer;

import output.Printer;
import util.BlendlistAnalyzer;
import data.Curry;
import data.Curry.CurryBuilder;
import data.CurryPair;
import data.Spice;

/**
 * 問題を解くクラスです。
 * @author tanabe
 *
 */
public class Solver {

    /**
     * 情報の出力先
     */
    private Printer logPrinter;

    /**
     * ブレンドリストから取得できる情報
     */
    private BlendlistAnalyzer analyzer;

    /**
     * ポイントバッファ
     */
    private PointBuffer pointBuffer;

    /**
     * 移動パターンバッファ
     */
    private MovePatternBuffer movePatternBuffer;

    /**
     * 問題を解く準備を行います。
     * @param path ブレンドリストファイルのパス
     * @param logPrinter ログの出力先
     * @throws IOException ブレンドリストの読み込みに失敗した場合
     */
    public Solver( String path, Printer logPrinter ) throws IOException {
        if( path == null || logPrinter == null) {
            throw new NullPointerException();
        }

        this.logPrinter = logPrinter;
        this.analyzer 	= new BlendlistAnalyzer( path );
        this.pointBuffer 	= new PointBuffer( this.analyzer.getBlendList() );
        this.movePatternBuffer = new MovePatternBuffer();
    }

    /**
     * 最高得点のカレーペアを取得します。
     * @return 最高得点のカレーペア
     */
    public List<CurryPair> solve() {

        ArrayList<CurryPair> bestCurrys = new ArrayList<CurryPair>();

        List<Spice> spiceList = this.analyzer.getSpiceList();
        for( Spice spice : spiceList ) {
            CurryBuilder builder1 = new Curry.CurryBuilder( spice );
            Curry c1 = builder1.build();

            CurryBuilder builder2 = new Curry.CurryBuilder( this.analyzer.getSpiceList() );
            builder2.removeSpice( spice );
            Curry c2 = builder2.build();

            bestCurrys = this.subProcess( c1, c2, spice, bestCurrys );
        }

        return bestCurrys;
    }

    /**
     * カレー間でスパイスを移動させ、点数が高い組み合わせを取得します。
     * @param c1 使用しているスパイスが少ないカレー
     * @param c2 使用しているスパイスが多いカレー
     * @param moveTarget カレー2からカレー1へ移動させるスパイス
     * @param bestCurrys スパイス移動前で最も点数が高い組み合わせ
     * @return スパイス移動後で最も点数が高い組み合わせ
     */
    private ArrayList<CurryPair> subProcess(
            Curry c1,
            Curry c2,
            Spice moveTarget,
            List<CurryPair> bestCurrys ){
        if( c1 == null || c2 == null || moveTarget == null || bestCurrys == null ) {
            throw new NullPointerException();
        }

        String printInfo = "move[" + moveTarget +"] c1[" + c1 + "] c2[" + c2 + "]";

        boolean contain = !this.movePatternBuffer.add( c1, c2, moveTarget );
        if( contain ) {
            this.logPrinter.println( "確認済みなのでスキップ => " + printInfo );
            return new ArrayList<CurryPair>( bestCurrys );
        }

        this.logPrinter.println( printInfo );

        // スパイス移動前の最高得点
        int beforeMax = 0;
        if( bestCurrys.size() != 0 ) {
            beforeMax = bestCurrys.get(0).getPoint();
        }

        // スパイスを移動
        CurryBuilder builder1 = new Curry.CurryBuilder( c1 );
        CurryBuilder builder2 = new Curry.CurryBuilder( c2 );
        builder1.addSpice( moveTarget );
        builder2.removeSpice( moveTarget );
        Curry newCurry1 = builder1.build();
        Curry newCurry2 = builder2.build();

        int p1 = this.pointBuffer.getPoint( newCurry1 );
        int p2 = this.pointBuffer.getPoint( newCurry2 );
        int afterMax = p1 + p2;

        ArrayList<CurryPair> newBestCurrys = new ArrayList<CurryPair>();
        if( beforeMax >= afterMax ) {
            newBestCurrys.addAll( bestCurrys );
        }
        if( beforeMax <= afterMax ) {
            CurryPair newPair = new CurryPair( newCurry1, newCurry2, afterMax );
            newBestCurrys.add( newPair );
        }

        // 終了かを判定
        if( newCurry1.getSpiceList().size() > newCurry2.getSpiceList().size() ) {
            // 半分以上のスパイスを動かしたら終わり
            return newBestCurrys;
        }
        if( afterMax == this.analyzer.getBlendList().size() ) {
            // 最大得点に達したら終わり
            return newBestCurrys;
        }

        List<Spice> movableSpiceList = this.analyzer.getMovableSpiceList( moveTarget );
        movableSpiceList.removeAll( newCurry1.getSpiceList() );

        // 動かせるスパイスを全パターン動かしてみる
        for( Spice movableSpice : movableSpiceList ) {
            newBestCurrys = this.subProcess( newCurry1, newCurry2, movableSpice, newBestCurrys );
        }

        return newBestCurrys;
    }

    /**
     * ポイントバッファを取得します。
     * @return ポイントバッファ
     */
    public PointBuffer getBuffer() {
        return this.pointBuffer;
    }
}
