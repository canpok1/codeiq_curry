/**
 *
 */
package output;

/**
 * コンソールへメッセージを出力するクラスです。
 * @author tanabe
 *
 */
public class ConsolePrinter implements Printer {

    /* (非 Javadoc)
     * @see output.Printer#print(java.lang.String)
     */
    @Override
    public void print( Object obj ) {
        System.out.print( obj );
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println( Object obj) {
        System.out.println( obj );
    }

}
