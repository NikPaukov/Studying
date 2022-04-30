package com.company;

import com.company.Button.Sort;
import com.company.factory.ArrayDialog;
import com.company.factory.BubbleDialog;
import com.company.factory.Dialog;

import java.util.Scanner;

public class Main {
    static Dialog dialog;
    public static void main(String[] args) {
        configure();
        Sort sort =dialog.chooseSort();
        sort.sort();
    }
    public static void configure(){
        try(Scanner x = new Scanner(System.in)){
            String inp = x.nextLine();
            if(inp.equals("arraysort")) dialog= new ArrayDialog();
            if(inp.equals("bubblesort")) dialog= new BubbleDialog();
            else dialog= new ArrayDialog();
        }
    }
}
