/**
 *
 */
package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tanabe
 *
 */
public class FilePrinter implements Printer {

    // TODO ファイルの新規作成ができない

    /**
     * ファイルライター
     */
    private File file;

    /**
     * 指定のファイルに出力するプリンターを生成します。
     * @param file ファイル
     */
    public FilePrinter( File file ) {
        if( file == null ) {
            throw new NullPointerException();
        }

        this.file = file;
    }

    /* (非 Javadoc)
     * @see output.Printer#print(java.lang.Object)
     */
    @Override
    public void print(Object obj) {
        try {
            if( this.canWrite( this.file ) ) {
                PrintWriter pw = this.createPrintWriter( this.file );

                pw.print( obj.toString() );

                pw.close();
            }
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
    }

    /* (非 Javadoc)
     * @see output.Printer#println()
     */
    @Override
    public void println() {
        try {
            if( this.canWrite( this.file ) ) {
                PrintWriter pw = this.createPrintWriter( this.file );

                pw.println();

                pw.close();
            }
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
    }

    /* (非 Javadoc)
     * @see output.Printer#println(java.lang.Object)
     */
    @Override
    public void println(Object obj) {
        try {
            if( this.canWrite( this.file ) ) {
                PrintWriter pw = this.createPrintWriter( this.file );

                pw.println( obj.toString() );

                pw.close();
            }
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * ファイルに書き込みができるかを確認します。
     * @param file ファイル
     * @return true:書き込み可 false:書き込み不可
     */
    private boolean canWrite( File file ) {
        if( file == null ) {
            return false;
        }

        if( file.exists() ) {
            if( file.isFile() && file.canWrite() ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 指定ファイルに書き込むライターを生成します。
     * @param file ファイル
     * @return ライター
     * @throws IOException 生成に失敗した場合
     */
    private PrintWriter createPrintWriter( File file ) throws IOException {
        return new PrintWriter( new BufferedWriter( new FileWriter( file ) ) );
    }
}
