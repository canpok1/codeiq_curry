/**
 *
 */
package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * カレーを表すクラスです。
 * @author tanabe
 *
 */
public class Curry {

    /**
     * カレーを構成するスパイス
     */
    private ArrayList<Spice> spices;

    /**
     * toStringで返す値
     */
    private String stringInfo;

    /**
     * 指定の複数スパイスを持つカレーを生成します。
     * @param spices 複数のスパイス
     */
    private Curry( SortedSet<Spice> spices ) {
        if( spices == null ) {
            throw new NullPointerException();
        }
        if( spices.size() == 0 ) {
            throw new IllegalArgumentException();
        }

        this.spices = new ArrayList<Spice>( spices );
        StringBuffer buffer = new StringBuffer();
        for( Spice spice : this.spices ) {
            buffer.append( spice + " ");
        }
        this.stringInfo = buffer.toString();
    }

    /**
     * スパイスの一覧を取得します。
     * @return スパイス一覧
     */
    public List<Spice> getSpiceList() {
        return new ArrayList<Spice>( this.spices );
    }

    /**
     * 指定スパイスを含むかを判定します。
     * @param spice スパイス
     * @return true:含む false:含まない
     */
    public boolean isContain( Spice spice ) {
        if( spice == null ) {
            throw new NullPointerException();
        }

        return this.spices.contains( spice );
    }

    @Override
    public String toString() {
        return this.stringInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((spices == null) ? 0 : spices.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Curry))
            return false;
        Curry other = (Curry) obj;

        if( this.toString().equals( other.toString() ) ) {
            return true;
        }

        return false;
    }

    /**
     * カレーを組み立てるクラスです。
     * @author tanabe
     *
     */
    public static class CurryBuilder {

        /**
         * 含まれるスパイス
         */
        private TreeSet<Spice> builderSpices;

        /**
         * 指定スパイスを持つカレービルダーを生成します。
         * @param spice スパイス
         */
        public CurryBuilder( Spice spice ) {
            if( spice == null ) {
                throw new NullPointerException();
            }

            this.builderSpices = new TreeSet<Spice>();
            this.builderSpices.add( spice );
        }

        /**
         * 指定の複数スパイスを持つカレービルダーを生成します。
         * @param spices 複数スパイス
         */
        public CurryBuilder( Collection<Spice> spices ) {
            if( spices == null ) {
                throw new NullPointerException();
            }
            if( spices.size() == 0 ) {
                throw new IllegalArgumentException();
            }

            this.builderSpices = new TreeSet<Spice>( spices );
        }

        /**
         * 同じスパイスを持つ新しいカレーを生成します。
         * @param original 元になるカレー
         */
        public CurryBuilder( Curry original ) {
            if( original == null ) {
                throw new NullPointerException();
            }

            this.builderSpices = new TreeSet<Spice>( original.spices );
        }

        /**
         * スパイスを追加します。
         * すでに存在するスパイスを追加すると失敗します。
         * @param spice スパイス
         * @return true:追加成功 false:追加失敗
         */
        public boolean addSpice( Spice spice ) {
            if( spice == null ) {
                throw new NullPointerException();
            }

            return this.builderSpices.add( spice );
        }

        /**
         * スパイスを減らします。
         * 存在しないスパイスを指定するか、残りスパイスが1つのとき失敗します。
         * @param spice スパイス
         * @return true:成功 false:失敗
         */
        public boolean removeSpice( Spice spice ) {
            if( spice == null ) {
                throw new NullPointerException();
            }

            if( this.builderSpices.size() <= 1 ) {
                return false;
            }

            return this.builderSpices.remove( spice );
        }

        /**
         * スパイスを生成します。
         * @return スパイス
         */
        public Curry build() {
            return new Curry( this.builderSpices );
        }
    }
}
