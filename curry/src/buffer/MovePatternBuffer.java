/**
 *
 */
package buffer;

import java.util.HashSet;

import data.Curry;
import data.Spice;

/**
 * スパイスの移動パターンを保持するクラスです。
 * @author tanabe
 *
 */
public class MovePatternBuffer {

    /**
     * バッファに保持できる最大件数
     */
    private static final int MAX_BUFFER_SIZE = 5000;

    /**
     * スパイスの移動パターンの保持領域
     */
    private HashSet<String> buffer;

    /**
     * 複数回取得された要素の保持領域
     */
    private HashSet<String> subBuffer;

    /**
     * 空のスパイス移動パターン保持領域を生成します。
     */
    public MovePatternBuffer() {
        this.buffer = new HashSet<String>();
        this.subBuffer = new HashSet<String>();
    }

    /**
     * 移動パターンを追加します。
     * バッファの登録数が最大件数に達している場合は、バッファクリア後に追加します。
     * @param c1 スパイスが少ないカレー(スパイス移動前の状態)
     * @param c2 スパイスが多いカレー(スパイス移動前の状態)
     * @param moveTarget c2からc1に移動させるスパイス
     * @return true:パターンを追加 false:パターンがすでに登録済み
     */
    public boolean add( Curry c1, Curry c2, Spice moveTarget ) {
        if( c1 == null || c2 == null || moveTarget == null ) {
            throw new NullPointerException();
        }

        if( this.buffer.size() >= MAX_BUFFER_SIZE ) {
            this.buffer.clear();
            this.buffer.addAll( this.subBuffer );
            this.subBuffer.clear();
        }

        String value = "[" + c1 + "][" + c2 + "][" + moveTarget + "]";

        if( this.buffer.add( value ) ) {
            return true;
        } else {
            this.subBuffer.add( value );
            return false;
        }
    }
}
