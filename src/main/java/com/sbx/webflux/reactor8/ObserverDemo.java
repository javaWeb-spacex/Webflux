package com.sbx.webflux.reactor8;

import java.util.Observable;

/**
 * @author :sbx
 * @date :2022/3/3 21:32
 * @description :
 * @version: :1.0.0
 */
public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        ObserverDemo observer=new ObserverDemo();
        //添加观察者
        observer.addObserver((o,arg)->{
            System.out.println("发生变化");
        });

        observer.addObserver((o,arg)->{
            System.out.println("手动被观察者通知，准备改变");
        });
        observer.setChanged(); //数据变化
        observer.notifyObservers();  //通知
    }
}
