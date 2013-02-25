/**
 *
 */
package data;

/**
 * カレーの組を表すクラスです。
 * @author tanabe
 *
 */
public class CurryPair {

    /**
     * カレーのペア1
     */
    private Curry curry1;

    /**
     * カレーのペア2
     */
    private Curry curry2;

    /**
     * 得点
     */
    private int point;

    /**
     * カレーの組を生成します。
     * @param c1 カレー1
     * @param c2 カレー2
     */
    public CurryPair( Curry c1, Curry c2, int point ) {
        if( c1 == null || c2 == null ) {
            throw new NullPointerException();
        }

        this.curry1 = c1;
        this.curry2 = c2;
        this.point  =point;
    }

    /**
     * カレー1を取得します。
     * @return カレー1
     */
    public Curry getCurry1() {
        return this.curry1;
    }

    /**
     * カレー2を取得します。
     * @return カレー2
     */
    public Curry getCurry2() {
        return this.curry2;
    }

    /**
     * 得点を取得します。
     * @return 得点
     */
    public int getPoint() {
        return this.point;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((curry1 == null) ? 0 : curry1.hashCode());
        result = prime * result + ((curry2 == null) ? 0 : curry2.hashCode());
        result = prime * result + point;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if ( !( obj instanceof CurryPair ) )
            return false;

        CurryPair other = (CurryPair) obj;

        if( this.curry1.equals( other.curry1 )
            && this.curry2.equals( other.curry2 ) ) {
            return true;
        }

        if( this.curry1.equals( other.curry2 )
            && this.curry2.equals( other.curry1 ) ) {
            return true;
        }

        return false;
    }
}
