package com.aleksandrov.Model;

import com.aleksandrov.View.Observer;

public interface Observable {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}
