package Storage;

import Model.Aircraft;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStorage {
    private static int size = 0;
    private List<Aircraft> list = new ArrayList<Aircraft>();

    public List<Aircraft> getList() {
        return list;
    }

    public void setList(List<Aircraft> list) {
        this.list = list;
    }

    public void add(Aircraft a) {
        list.add(a);
        size++;
    }

    public void remove(int id) {
        list.remove(get(id));
    }

    public Aircraft get(int id) {
        if (id > size - 1) {
            System.out.println("No such aircraft!");
        }
        return list.get(id);
    }
}
