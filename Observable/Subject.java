package Observable;

import java.util.*;

//Observer pattern dependent/subject/observable
public abstract class Subject {

       List<Observer> observers;
       
       public Subject() {
              observers = new ArrayList<>();
       }

       public void addObserver(Observer observer) {
              observers.add(observer);
       }

       public void removeObserver(Observer observer) {
              observers.remove(observer);
       }

       public void notifyObservers() {
              for(Observer observer : observers) {
                     observer.update();
              }
       }
}
