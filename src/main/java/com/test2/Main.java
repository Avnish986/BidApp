package com.test2;

import com.test2.app.BidBlitz;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        BidBlitz bidBlitz = new BidBlitz();

        // Example commands
        System.out.println(bidBlitz.addMember(1, "Akshay", 10000));
        System.out.println(bidBlitz.addMember(2, "Chris", 5000));
        System.out.println(bidBlitz.addEvent("BBD", "IPHONE-14"));
        System.out.println(bidBlitz.registerMember(1, 1));
        System.out.println(bidBlitz.submitBid(1, 1, new int[]{100, 200, 400, 500, 600}));
        System.out.println(bidBlitz.declareWinner(1));
        System.out.println(bidBlitz.listWinners("asc"));
    }
}