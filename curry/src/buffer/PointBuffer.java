/**
 *
 */
package buffer;

import java.util.Collection;
import java.util.HashMap;

import util.PointCalculator;
import data.Blend;
import data.Curry;

/**
 * カレーの得点を保持するクラスです。
 * @author tanabe
 *
 */
public class PointBuffer {

    /**
     * 得点を保持できる最大件数
     */
    private static final int MAX_BUFFER_SIZE = 5000;

    /**
     * カレーの得点の保持領域
     */
    private HashMap<String, Integer> buffer;

    /**
     * 複数回取得された得点の保持領域
     */
    private HashMap<String, Integer> subBuffer;

    /**
     * カレー得点計算機
     */
    private PointCalculator calculator;

    /**
     * カレーの得点保持領域を生成します。
     * @param blends ブレンドリスト
     */
    public PointBuffer( Collection<Blend> blends ) {
        this.calculator = new PointCalculator( blends );
        this.buffer = new HashMap<String, Integer>();
        this.subBuffer = new HashMap<String, Integer>();
    }

    /**
     * カレーの得点を取得します。
     * 得点がバッファ内にない場合は計算を行います。
     * 保持している得点情報が最大件数の場合は、バッファクリア後に追加されます。
     * @param curry カレー
     */
    public int getPoint( Curry curry ) {
        if( this.buffer.containsKey( curry ) ) {
            int point = this.buffer.get( curry ).intValue();
            this.subBuffer.put( curry.toString(), point );
            return point;
        }

        if( this.buffer.size() == MAX_BUFFER_SIZE ) {
            this.buffer.clear();
            this.buffer.putAll( this.subBuffer );
            this.subBuffer.clear();
        }

        int point = this.calculator.calculate( curry );
        this.buffer.put( curry.toString(), point );

        return point;
    }
}
