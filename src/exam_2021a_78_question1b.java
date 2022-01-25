import java.util.ArrayList;
import java.util.Iterator;


class boundedGroup<T extends Comparable<T>> {
    Group groups;
    T upperBound;

    public Group<T> boundedGroup(Group _groups, T upperBound) {
        this.upperBound = upperBound;
        Group<T> retGroup = new Group<T>();
        this.groups = _groups;
        Iterator<T> it = this.groups.iterator();

        while(it.hasNext()){
            T val = it.next();
            if  (val.compareTo(upperBound)==-1){
                    retGroup.add(val);
            }
        }
        return retGroup;
    }
}

class Group<T> {
    ArrayList<T> list;

    public Group() {
        this.list = new ArrayList<T>();
    }

    public boolean add(T item) {
        for (T var : list) {
            if (var.equals(item))
                return false;
        }

        this.list.add(item);
        return true;
    }

    public boolean remove(T item) {
        return this.list.remove(item);
    }

    public Iterator<T> iterator() {
        return this.list.iterator();
    }

}


public class exam_2021a_78_question1b {

    public void main() {

    }
}
