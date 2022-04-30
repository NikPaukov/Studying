package com.company.factory;

import com.company.Button.BubbleSort;
import com.company.Button.Sort;

public class BubbleDialog  extends Dialog{
    @Override
    public Sort chooseSort() {
        return new BubbleSort();
    }
}
