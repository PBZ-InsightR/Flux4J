package com.flux4j;

import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertTrue;

public class DispatcherTest {

    @Test
    public void test_registerUnRegister() {
        Dispatcher dispatcher = Dispatcher.getInstance();
        String id = dispatcher.register(new Function<Action, Object>() {
            @Override
            public Object apply(Action o) {
                return null;
            }
        });
        Assert.assertFalse(dispatcher.callbacks.isEmpty());
        dispatcher.unRegister(id);
        Assert.assertTrue(dispatcher.callbacks.isEmpty());
    }

    @Test
    public void test_dispatch() {
        Dispatcher dispatcher = Dispatcher.getInstance();
        Function<Action, Object> callback = new Function<Action, Object>() {
            public Integer nbCall = 0;

            @Override
            public Object apply(Action action) {
                ++nbCall;
                return nbCall;
            }
        };
        dispatcher.register(callback);
        dispatcher.dispatch(new Action("action.test"));
        assertTrue((Integer) callback.apply(new Action("action.test")) == 2);
    }
}