/**
 *
 */
package core;

import java.io.File;
import java.util.HashSet;

import buffer.PointBuffer;

import output.ConsolePrinter;
import output.FilePrinter;
import output.Printer;
import data.CurryPair;

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
     * サンプルブレンドファイルへのパス
     */
    private static final String SAMPLE_BLENDLIST_PATH
            = "C:\\Users\\tanabe\\Documents\\CodeIQ\\curry\\sample_blendlist.txt";

    /**
     * 情報の出力先
     */
    private static final Printer PRINTER = new ConsolePrinter();

    /**
     * 答えの出力先パス
     */
    private static final String ANSWER_FILE
            = "C:\\Users\\tanabe\\Documents\\CodeIQ\\curry\\MyAnswer.txt";

    /**
     * @param args
     */
    public static void main(String[] args) {
        PRINTER.println( "program start" );

        String path = BLENDLIST_PATH;
//        String path = SAMPLE_BLENDLIST_PATH;

        File file = new File(ANSWER_FILE );
        FilePrinter filePrinter = new FilePrinter( file );

        PRINTER.println( "blendlist path => " + path );
        try {
            Solver             solver = new Solver( path, PRINTER );
            HashSet<CurryPair> answer = new HashSet<CurryPair>( solver.solve() );
            PointBuffer        buffer = solver.getBuffer();

            for( CurryPair pair : answer ) {
                int p1 = buffer.getPoint( pair.getCurry1() );
                int p2 = buffer.getPoint( pair.getCurry2() );

                filePrinter.println( (p1 > p2) ? pair.getCurry1() : pair.getCurry2() );
            }
        }
        catch( Exception e ) {
            PRINTER.println( "読み込み失敗" );
            e.printStackTrace();
        }

        PRINTER.println("program end");
    }

}
