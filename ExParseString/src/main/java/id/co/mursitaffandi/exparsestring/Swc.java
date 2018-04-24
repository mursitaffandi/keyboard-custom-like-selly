package id.co.mursitaffandi.exparsestring;

/**
 * Created by mursitaffandi on 4/11/18.
 */

public class Swc {
    public static void main(String[] args){
        int n = 90;
        int q = (n+7)/8;
        switch (n%8) {
            case 0: foo();    // Great C hack, Tom,
                case 7:      foo();    // but it's not valid here.
                case 6:      foo();
                case 5:      foo();
                case 4:      foo();
                case 3:      foo();
                case 2:      foo();
                case 1:      foo();
            }
        }


    private static void foo() {

    }
}
