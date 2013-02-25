/**
 *
 */
package output;

/**
 * 情報を出力する機能を提供するインターフェースです。
 * @author tanabe
 *
 */
public interface Printer {

    /**
     * オブジェクト情報を出力します。
     * @param obj オブジェクト
     */
    void print( Object obj );

    /**
     * 改行を出力します。
     */
    void println();

    /**
     * 行末に改行を付加してオブジェクト情報を出力します。
     * @param obj オブジェクト
     */
    void println( Object obj );
}
