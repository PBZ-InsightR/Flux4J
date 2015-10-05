package com.flux4j;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DispatcherTest {

    @Test
    public void dispatch() {
        Dispatcher dispatcher = new Dispatcher();

        Assert.assertTrue(dispatcher.isReady());

        dispatcher.dispatch();

        Assert.assertFalse(dispatcher.isReady());
    }
}