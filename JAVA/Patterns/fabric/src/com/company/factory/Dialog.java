package com.company.factory;

import com.company.Button.Sort;

public abstract class Dialog {
    public void start(){
        Sort sort =chooseSort();
    }
    public abstract Sort chooseSort();
}
