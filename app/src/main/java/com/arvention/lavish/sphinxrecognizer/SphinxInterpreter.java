package com.arvention.lavish.sphinxrecognizer;

/**
 * Created by NeilJustin on 2/3/2017.
 */

public interface SphinxInterpreter {
    public void resultReceived(String result);

    public void onRecognizerReady();
}