package com.flux4j;

import java.util.Observable;

public class Store extends Observable {
    // TODO actions modify stores

    public void addView(View v) {
        addObserver(v);
    }
}
