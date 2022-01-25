



class ComputeTask extends Thread{
    private int arr[];
    private int sum=0;
    private int timesComputed;
    public ComputeTask(int arr[]){
        this.arr = arr;
    }

    public synchronized void sum(){
        this.timesComputed = 0;
            for(int i =0 ; i < this.arr.length ; i++){
                this.sum += this.arr[i];
                this.timesComputed++;
            }

            notifyAll();
    }

    public synchronized int getSum() {
        while(timesComputed < arr.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    @Override
    public void run(){
        super.run();
        this.sum();
    }
}



public class exam_2021b_78_question4 {


    public void main(){
        int arr[] = {1,2,2,4,6,65,5,2,3,5};

        ComputeTask[] c = new ComputeTask[10];
        for (int i =0 ; i < c.length ; i++){
            c[i] = new ComputeTask(arr);
        }
        int sum = 0;

        for (int i =0 ; i < c.length ; i++){
            c[i].start();
        }

        for (int i =0 ; i < c.length ; i++){
            sum += c[i].getSum();
        }

        System.out.println("Sum Of 10 Arrays is: " + sum);


    }
}
