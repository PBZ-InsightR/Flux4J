package com.flux4j;

public class Dispatcher {
    private boolean ready;

    public Dispatcher() {
        this.ready = true;
    }

    public boolean isReady() {
        return ready;
    }

    public void dispatch() {
        this.ready = false;
    }



}
