package dev.megaport.sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Sorter<E> implements ISorter<E> {

    private final List<E> dataList = new ArrayList<>();

    @Override
    public void input(E data) {
        dataList.add(data);
    }

    @Override
    public void sort(Comparator<E> comparator) {
        dataList.sort(comparator);
    }

    public List<E> output() {
        return dataList;
    }

}
