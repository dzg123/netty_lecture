package com.dzg.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-07 15:24
 **/
public class AtomicUpdaterTest {
    public static void main(String[] args) {

        Person person = new Person();
//        for (int i = 0; i < 50; i++) {
//            Thread thread = new Thread(() -> System.out.println(person.age++));
//            thread.start();
//        }
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater
                .newUpdater(Person.class, "age");
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
            });
            thread.start();
            
        }

    }
}

class Person {
    volatile int age = 1;
}
