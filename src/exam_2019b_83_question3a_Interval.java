import java.util.*;

class OutOfBOundsExeption extends Exception {
    public OutOfBOundsExeption() {
        super("Error: OutOfBOundsExeption");
    }
}


class NonCodeable extends Exception {
    public NonCodeable() {
        super("Error: OutOfBOundsExeption");
    }
}

class Interval2<T extends Comparable<T>> {
    T lower;
    T upper;
    ArrayList<T> list = new ArrayList<T>();

    public Interval2(T lower, T upper) throws OutOfBOundsExeption {
        if (upper.compareTo(lower) <= 0) {
            throw new OutOfBOundsExeption();
        }

        this.lower = lower;
        this.upper = upper;
    }

    public void add(T item) throws OutOfBOundsExeption {
        if (item.compareTo(lower) == -1 || item.compareTo(upper) == 1) {
            throw new OutOfBOundsExeption();
        }

        boolean addFlag = true;
        for (T var : list) {
            if (var.equals(item)) {
                addFlag = false;
                break;
            }
        }

        if (addFlag) {
            this.list.add(item);
        }
    }

    public ArrayList<T> subInterval(T newLower, T newUpper) throws OutOfBOundsExeption {
        if (newLower.compareTo(this.lower) == -1 || newUpper.compareTo(upper) == 1) {
            throw new OutOfBOundsExeption();
        }
        ArrayList<T> subList = new ArrayList<T>();

        for (T var : this.list) {
            if (var.compareTo(newLower) >= 0 && var.compareTo(newUpper) <= 0) {
                subList.add(var);
            }
        }

        return subList;
    }


}


class A {
    public static void stat() {
        System.out.println("A.stat() ");
    }

    public void foo() {
        stat();
    }
}

class B extends A {
    public static void stat() {
        System.out.println("B.stat() ");
    }

    public void main() {
        B b = new B();
        b.foo();
        stat();
    }
}


interface Computeable2 {
    public void comp();

}

class Data2 implements Computeable2 {
    private int num;

    public Data2(int n) {
        num = n;
    }

    public void comp() {
        if ((num % 2) > 0) num--;

    }

    public boolean equals(Data o) {
        return o.num == num;
    }

    public int getNum() {
        return num;
    }

    public String toString() {
        return " " + num;
    }

    public static void main() {
        Computeable2 a = new Data2(2);
        Data2 b = new Data2(3);
        a.comp();
        b.comp();
        System.out.println("" + a + b + b.equals(a));
    }
}


abstract class Dish {
    public Dish(String name, int price) {

    }

    public String getName() {
        return "";
    }

    public int getPrice() {
        return 0;
    }
}

class Menu<T extends Dish> {
    ArrayList<T> list;

    public Menu() {
        this.list = new ArrayList<T>();
    }

    public void add(T item) {
        this.list.add(item);
    }

    public T get(String name) {
        for (T var : this.list) {
            if (var.getName().contains(name)){
                return var;
            }
        }
        return null;
    }

    public Iterator<T> iterator(){
        return this.list.iterator();
    }

}

class Desert extends Dish{
    public Desert(String name, int price) {
        super(name, price);
    }
}

class Cake extends Desert{
    public Cake(String name, int price) {
        super(name, price);
    }
}

class Drink extends Dish{
    public Drink(String name, int price) {
        super(name, price);
    }
}

class getCakesClass<T extends Dish>{
    ArrayList<Cake> list = new ArrayList<Cake>();
    getCakesClass(ArrayList<T> list){
        for (T item: list) {
            if (item instanceof Cake)
                list.add(item);
        }
    }
}




/*** Threads Question 4 - 2019b-93 ***/
class ParkingLot{
    int[] parks;

    public ParkingLot(int num){
        this.parks = new int[num];
        Arrays.fill(parks, 0);
    }

    private int findFreeEntry(){
        for(int i=0;i<parks.length;i++){
            if (parks[i]==0){
                return i;
            }
        }
        return -1;
    }

    public synchronized int enter(){
        boolean waitForEntry = true;
        int num=-1;
        while(waitForEntry){
           num = this.findFreeEntry();
           if (num==-1){
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           } else {
               this.parks[num]=1;
               waitForEntry = false;
           }
        }

        return num;
    }

    public synchronized void exit(int i){
        this.parks[i] = 0;
        notifyAll();
    }
}


class Car extends Thread{
    int park=-1;
    int car=-1;
    ParkingLot monitor;
    public Car(ParkingLot monitor,int car){
        this.monitor = monitor;
        this.car = car;
    }

    @Override
    public void run() {
        super.run();
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(3000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.park = this.monitor.enter();
        System.out.println("Car("+ this.car + ") Entered On Park: " + this.park);
        try {
            Thread.sleep(rand.nextInt(3000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Car("+ this.car + ") Leave Park: " + this.park);
        this.monitor.exit(this.park);

    }

    public static void main(){
        Car[] cars = new Car[10];
        ParkingLot park = new ParkingLot(5);

        for (int i = 0 ; i < 10 ; i++){
            cars[i] = new Car(park,i+1);
        }

        for (int i = 0 ; i < 10 ; i++){
            cars[i].start();
        }
    }
}






class C {
    protected int num;
    static int number;

    public C(int i){
        System.out.println("In C");
    }
}


class D extends C {

    public D(int i){
           super(i);
    }

    public D(){
      super(0);
      System.out.println("In D");
    }
}




/*** Question 3 ***/

interface Codeable2<T>{
    public T code() throws NonCodeable;
}

class Text implements Codeable2<Text>{
    private String st="";  //טקסט כלשהו
    public Text(String st){
        this.st = st;
    }

    public Text code() throws NonCodeable {
        if (this.st.length() < 2) throw new NonCodeable();
        String shiftStr = this.st.charAt(st.length()-1)+"";
        for(int i = 0 ; i < st.length()-1 ; i++){
            shiftStr += this.st.charAt(i);
        }
        return new Text(shiftStr);
    }

    public <T extends Codeable2> ArrayList<T> coder(ArrayList<T> list){

        ArrayList<T> retList = new ArrayList<T>();
        for (T var: list) {
            try {
                retList.add((T)var.code());
            } catch (NonCodeable e) {
                e.printStackTrace();
                return null;
            }
        }

        return retList;
    }
}




class Parent {
    public void func(Parent a){
        System.out.println("123");
    }

}

class Child extends Parent{

    public  void main(String[] args){
        Child b = new Child();
        Parent a = new Parent();
        b.func(b);
        b.func(a);
    }

    public void func(Object o) {
        System.out.print("456 ");
    }





}



class One {
    public One(){
        aa();
        bb();
    }

    private void aa(){
        System.out.println("aa One");
    }

    void bb(){
        System.out.println("bb One");
    }
}


class Two extends One{
    public void aa(){
        System.out.println("aa Two");
    }
    public void bb(){
        System.out.println("bb Two");
    }
}


class Foo {
    private  int data;
    class Foo1 {
        private int d = data++;
        public  int getD() { return d; }
    }
    public Object f(){
        Foo1 foo1 = new Foo1();
        return foo1;
    }
}

class FooTest {
    public static void main(){
        Object o = new Foo().f();
        Foo.Foo1 foo1 = (Foo.Foo1)o;
        System.out.println(foo1.getD());
    }
}


class TestA {
    public static void test(List<Integer> lst){
        for (Integer i : lst) {
            try{
                System.out.print(i);
            }catch (NullPointerException e){
                System.out.print("1");
            }finally {
                System.out.print("0");
            }
        }
        System.out.println("\n");
    }
}

class WorkThread extends Thread{
    private int[] vec;
    private  int id;
    private int result;
    private static int tasks;

    public WorkThread(int[] vec, int id){
        this.vec = vec;
        this.id = id;
        tasks = 0;
    }

    public synchronized int process(int[] vec, int id){
        int result = 0;

        while (id > tasks){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < vec.length; i++) {
            vec[i] = vec[i] + 1;
            result = result + vec[i];
        }

        tasks++;

        return result;
    }

    private synchronized void realeaseWorker(){
        notifyAll();
    }


    public void run(){
        result = process(vec,id);
        System.out.println("task" + id + " result=" + result);
        realeaseWorker();
    }
}

	class TestWorkThread{
	   public static void main(String [] args){
        	      int[] vec = {1,2,3,4};
        	      WorkThread[] workers = new WorkThread[3];
        	      for (int i=0; i<3; i++)
            	            workers[i] = new WorkThread(vec,i);
        	      for (int i=0; i<3; i++)
            	           workers[i].start();

        	      System.out.println("**main**");
       }
	}


/** Algorithem Course **/
class algorithemCitys{
    int N = 5;
    int[][] table = new int[N+1][N];

    public int DPI(int i){
        if (i==0) return 1;
        if (i==1) return 2;
        if (i==2) return 0;
        if (i==3) return 3;
        if (i==4) return 4;
        else return -1;
    }

    public int opt(int i,int j){
        if (i > 4) return 0;

        if(this.DPI(i)>j){
            return Math.max(1+opt(i+1,DPI(i)),opt(i+1,j));
        } else {
            return opt(i+1,j);
        }
    }

    public void dynamicOpt(){

        for(int row = N-1; row >= 0; row--){
            for(int col = 0 ; col < N ; col++){
                if(this.DPI(row)>col){
                    table[row][col] = Math.max(1+table[row+1][DPI(row)],table[row+1][row]);
                } else {
                    table[row][col] = table[row+1][row];
                }
            }
        }

        System.out.println("Done Set OPT Table.");

        System.out.println("C Connections!");

        int row = 0;
        int col = 0;
        ArrayList<Integer> C = new ArrayList<Integer>();
        int current = table[row][col];
        while(row < N){
            if (current == 1+table[row+1][DPI(row)]){
                System.out.println("Row: " + row + " Connected To: " + DPI(row));
                col = DPI(row);
                row = row +1;

            } else {
                row = row+1;
            }
            current = table[row][col];
        }

        System.out.println("\nDone C Connections!");
    }


    public static void main(){
        algorithemCitys s = new algorithemCitys();
        System.out.println(s.opt(0,0));
        s.dynamicOpt();
    }
}


class Polindrom{

    public  static int kk = 1;
    public static int[][] k_opt;


    public static boolean kontzertan_opt(int[] arr,int i, int num){
        if (i >= arr.length) return false;
        if (num == 0) return true;
        System.out.println(kk++);
        boolean t =  kontzertan_opt(arr,i+1, num);
        boolean f = kontzertan_opt(arr,i+1, num-arr[i]);
        return f || t;
    }



    public static void dynamic_M(int[][] arr){
        k_opt = new int[arr[0].length][arr[0].length];

        for(int j = 0 ; j < arr[0].length ; j++){
            k_opt[arr[0].length-1][j] = arr[arr[0].length-1][j];
        }

        for (int i = arr[0].length-2  ; i >= 0 ; i--){
            for(int j = 0 ; j < arr[0].length-1 ; j++){
                if (j==0){
                    k_opt[i][j] = Math.min(k_opt[i+1][j+1],k_opt[i+1][j]);
                } else {
                    k_opt[i][j] = Math.min(Math.min(k_opt[i+1][j-1],k_opt[i+1][j+1]),k_opt[i+1][j]);
                }

            }
        }

        int i = 0;
        int j = 0;
        System.out.println("Table");
        System.out.println(arr[0][0]);
        int print=999;
        while(i <  arr[0].length-1){
            try{
                if (k_opt[i][j] == k_opt[i+1][j-1]){
                    print = arr[i+1][j-1];
                    i = i+1;
                    j = j-1;
                }
            }catch (Exception is){}

            try{
                if (k_opt[i][j] == k_opt[i+1][j+1]){
                    print = arr[i+1][j+1];
                    i = i+1;
                    j = j+1;
                }
            }catch (Exception is){}

            try{
                if (k_opt[i][j] == k_opt[i+1][j]){
                    print = arr[i+1][j];
                    i = i+1;
                }
            }catch (Exception is){}
            System.out.println(print);

        }


    }

    public static int opt_M(int[][] arr , int i , int j){
        if (i == arr[0].length-1 && j >= 0 && j <= arr[0].length-1) return arr[i][j];
        if (j < 0 || j >= arr[0].length || i >= arr[0].length) return 10000;

        System.out.println(kk++);
        return arr[i][j] + Math.min(Math.min(opt_M(arr , i + 1 , j - 1),opt_M(arr , i + 1 , j)),opt_M(arr , i + 1 , j + 1));
    }

    public static void main(){

        int[] arr = {5,3,2,1,4,1};
        int[][] CI = {{ 1 , 5 , 4 , 3},
                      { 3 , 2 , 4 , 1},
                      { 4 , 5 , 3 , 9},
                      { 2 , 3 , 8 , 1}};

        int size = (5+3+2+1+4+1)/2;
        System.out.println(opt_M(CI,0,0));
        dynamic_M(CI);
    }
}

public class exam_2019b_83_question3a_Interval {

    public void main() throws OutOfBOundsExeption, NonCodeable {

        Polindrom.main();
        /*TestWorkThread.main(null);*/

        /*List<Integer> lst = new ArrayList<Integer>();
        lst.add(7);
        lst.add(null);
        lst.add(8);
        TestA.test(lst);*/
       // FooTest.main();
        //One one = new Two();
        /*Child c = new Child();
        c.main(null);*/

       /* Text tx = new Text("abcdefgh");
        System.out.println(tx.code());*/
        //Car.main();

        //Data2.main();


        /*B b= new B();
        b.main();*/
        /*Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
        Interval2<Integer> interval = new Interval2<Integer>(1,10);

        System.out.println("\nFull List:");
        for(int i = 0 ; i < arr.length ; i++){
            interval.add(arr[i]);
            System.out.print(arr[i] + " ");
        }

        ArrayList<Integer> subList = interval.subInterval(4,8);
        System.out.println("\nSub List:");
        for (Integer val: subList) {
            System.out.print(val + " ");
        }*/

    }
}
