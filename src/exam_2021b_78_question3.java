import java.util.ArrayList;
import java.util.Random;

interface Codeable {
    public int code();
}

class XXX implements Codeable {

    private int num;

    @Override
    public int code() {
        return this.num;
    }

    public XXX(int n) {
        this.num = n;
    }

}

class YYY implements Codeable {

    private int num;

    @Override
    public int code() {
        return this.num;
    }

    public YYY(int n) {
        this.num = n;
    }
}

public class exam_2021b_78_question3 {
    public static <T extends Codeable> T maxCode(ArrayList<T> list) {
        int max = 0;
        T element = null;
        for (T item : list) {
            if (item.code() > max){
                max = item.code();
                element = item;
            }
        }
        return element;
    }

    public static Codeable maxCodeWild(ArrayList<? extends Codeable> list) {
        int max = 0;
        Codeable element = null;
        for (Codeable item : list) {
            if (item.code() > max){
                max = item.code();
                element = item;
            }
        }
        return element;
    }

    public void main(){
        ArrayList<Codeable> list = new ArrayList<Codeable>();
        for(int i = 0;i<96;i++){
            if (i%2==0){
                list.add((Codeable)new XXX(i));
            }else{
                list.add((Codeable)new YYY(i));
            }
        }
        /*** סעיף א ***/
        Codeable a = maxCodeWild(list);
        System.out.println("Class: " + a.getClass() + " Index: " + a.code());
        /*** סעיף ב ***/
        Codeable b = maxCodeWild(list);
        System.out.println("Class: " + b.getClass() + " Index: " + b.code());
        /*** 1 - סעיף ד ***/
        ArrayList< Codeable > arr1 = new ArrayList< Codeable >(10);
        ArrayList<XXX> arr2 = new ArrayList<XXX>(10);
        arr1.add(new XXX(1));
        arr1.add(new YYY(1));
        // arr2.add(arr1.get(0)); - לא תקינה השורה
        /*** 2 - סעיף ד ***/
        //arr1=arr2; - לא חוקי
        //arr2=arr1; - לא חוקי
    }
}
