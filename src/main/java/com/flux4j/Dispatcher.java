package com.flux4j;

import java.util.*;
import java.util.function.Function;

/**
 * @see <a href="https://github.com/facebook/flux/blob/master/src/Dispatcher.js>github.com/facebook/flux/blob/master/src/Dispatcher.js</a>
 * for more information about basic implementation.
 *
 * There is some warning left due to the error case managment not done for the moment. See TODO for more information.
 *
 */
public class Dispatcher {
    private boolean isDispatching;

    private static Dispatcher INSTANCE = null;

    private static final String PREFIX = "ID_";

    private static int ID = 0;

    private Map<String, Function> callbacks;
    private Map<String, Boolean> isPending;
    private Map<String, Boolean> isHandled;

    private Object pendingPayLoad;

    private Dispatcher()
    {
        this.isDispatching = true;
        callbacks = new HashMap<>();
        isPending = new HashMap<>();
        isHandled = new HashMap<>();
    }

    /**
     * Get the instance of the unique dispatcher.
     * @return the unique instance.
     */
    public static Dispatcher getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized(Dispatcher.class)
            {
                if (INSTANCE == null)
                {	INSTANCE = new Dispatcher();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Is this Dispatcher currently dispatching.
     * @return a boolean.
     */
    public boolean isDispatching(){
        return isDispatching;
    }

    /**
     * Dispatches a payload to all registered callbacks.
     */
    public void dispatch(Object payload) {
        if(!isDispatching){
            startDispatchning(payload);
            try{
                for(Map.Entry<String,Function> id : callbacks.entrySet()){
                    if(isPending.get(id.getKey())){
                        continue;
                    }
                    //can throw any exception due to the use of a callback.
                    invokeCallBack(id.getKey());
                }
            }
            finally {
                stopDispatching();
            }
        }
        else
        {
            //TODO error cannont dispatch in the middle of a dispatch
        }
    }

    /**
     * Clear bookkeeping used for dispatching.
     *
     */
    private void stopDispatching() {
        pendingPayLoad = null;
        isDispatching = false;
    }

    /**
     * Set up bookkeeping needed when dispatching.
     *
     * @param payload a payload in accordance with the callback.
     */
    private void startDispatchning(Object payload) {
        for(Map.Entry<String,Function> id : callbacks.entrySet())
        {
            isPending.put(id.getKey(), false);
            isHandled.put(id.getKey(), false);
        }
        pendingPayLoad = payload;
        isDispatching = true;
    }

    /**
     * Waits for the callbacks specified to be invoked before continuing execution of the current callback.
     * This method should only be used by a callback in response to a dispatched payload.
     * @param ids list of id.
     */
    public void waitFor(List<String> ids){
        if(isDispatching){
            for(String id : ids){
                if(isPending.get(id) != null){
                    if(isHandled.get(id) == null){
                        //TODO notify error circular dependency.
                    }
                    continue;
                }
                if(callbacks.get(id)== null){
                    //TODO notify callback not register
                }

            }
        }
    }

    /**
     * Call the callback stored with the given id. Also do some internal
     * bookkeeping.
     * @param id the id of the callback to use.
     */
    private void invokeCallBack(String id){
        isPending.put(id,true);
        callbacks.get(id).apply(pendingPayLoad);
        isHandled.put(id,true);
    }

    /**
     * Registers a callback to be invoked with every dispatched payload. Returns a token that can be used with waitFor().
     * @param callback a specified function.
     * @return id for the registered callback.
     */
    public String register(Function callback){
        String id = PREFIX + ID++;
        callbacks.put(id,callback);
        return id;
    }

    /**
     * Removes a callback based on its token.
     * @param id the id of the callback to remove.
     */
    public void unregister(String id)throws IllegalArgumentException{
        if(callbacks.get(id) != null)
            callbacks.remove(id);
        else
            throw new IllegalArgumentException("Id doesn't exists");
    }
}
