package ika.test.runningapp;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeCycleAwareLogger implements LifecycleObserver {

    private final String LOG_TAG;
    private final String lifeCycleOwner;

    public LifeCycleAwareLogger(String logTag, String lifeCycleOwner) {
        LOG_TAG = logTag;
        this.lifeCycleOwner = lifeCycleOwner;
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    public void myOnCreate(){
        Log.d(LOG_TAG, "myOnCreate() called by " + lifeCycleOwner);
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    public void myOnStart(){
        Log.d(LOG_TAG, "myOnStart() called by " + lifeCycleOwner);
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    public void myOnResume(){
        Log.d(LOG_TAG, "myOnResume() called by" + lifeCycleOwner);
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    public void myOnPause(){
        Log.d(LOG_TAG, "myOnPause() called by" + lifeCycleOwner);
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    public void myOnStop(){
        Log.d(LOG_TAG, "myOnStop() called by" + lifeCycleOwner);
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    public void myOnDestroy(){
        Log.d(LOG_TAG, "myOnDestroy() called by " + lifeCycleOwner);
    }

}
