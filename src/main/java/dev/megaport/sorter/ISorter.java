package dev.megaport.sorter;

import java.util.Comparator;

public interface ISorter<E> {

    void input(E data);

    void sort(Comparator<E> comparator);

}
