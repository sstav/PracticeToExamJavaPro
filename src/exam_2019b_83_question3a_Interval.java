import java.util.ArrayList;

class OutOfBOundsExeption extends Exception {
    public OutOfBOundsExeption() {
        super("Error: OutOfBOundsExeption");
    }
}


class Interval2<T extends Comparable<T>> {
    T lower;
    T upper;
    ArrayList<T> list = new ArrayList<T>();

    public Interval2(T lower, T upper) throws OutOfBOundsExeption{
        if (upper.compareTo(lower) <= 0) {
            throw new OutOfBOundsExeption();
        }

        this.lower = lower;
        this.upper = upper;
    }

    public void add(T item) throws OutOfBOundsExeption{
        if (item.compareTo(lower) == -1 || item.compareTo(upper) == 1){
            throw new OutOfBOundsExeption();
        }

        boolean addFlag = true;
        for (T var :  list) {
            if (var.equals(item)){
                addFlag = false;
                break;
            }
        }

        if (addFlag){
            this.list.add(item);
        }
    }

    public ArrayList<T> subInterval(T newLower, T newUpper) throws OutOfBOundsExeption{
        if (newLower.compareTo(this.lower) == -1 || newUpper.compareTo(upper) == 1){
            throw new OutOfBOundsExeption();
        }
        ArrayList<T> subList = new ArrayList<T>();

        for (T var: this.list) {
            if (var.compareTo(newLower)>=0 && var.compareTo(newUpper) <= 0){
                subList.add(var);
            }
        }

        return subList;
    }



}

public class exam_2019b_83_question3a_Interval {

    public void main() throws OutOfBOundsExeption {
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
        Interval2<Integer> interval = new Interval2<Integer>(1,10);

        System.out.println("\nFull List:");
        for(int i = 0 ; i < arr.length ; i++){
            interval.add(arr[i]);
            System.out.print(arr[i] + " ,");
        }

        ArrayList<Integer> subList = interval.subInterval(4,8);
        System.out.println("\nSub List:");
        for (Integer val: subList) {
            System.out.print(val + " ,");
        }

    }
}
