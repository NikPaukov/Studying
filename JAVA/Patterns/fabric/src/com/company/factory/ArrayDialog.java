package com.company.factory;

import com.company.Button.ArraySort;
import com.company.Button.Sort;

public class ArrayDialog extends Dialog {
    @Override
    public Sort chooseSort() {
        return new ArraySort();
    }
}
