package com.aleksandrov.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.ArrayList;

public class BaseFile implements Base, Serializable {

    private static final Logger log = LogManager.getLogger(BaseFile.class.getName());
    private ArrayList<Test> tests = new ArrayList<>();
    private String filename = "src/main/resources/fileBase.txt";

    /**
     * BaseFile и BasePostgre должны только возвращать базу без методов работы с базой,
     * эти методы нужно реализовать в классе Base
     */
    public BaseFile(){
        openBase();
        log.debug("end constructor FileBase");
    }

    /**
     * добавляем тест для изучения
     * @param test
     */
    @Override
    public void setTest(Test test){
        tests.add(test);

    }

    /**
     * возвращаем случайный тест
     * @return Test тест для проверки
     */
    @Override
    public Test getTest(){
        int index = (int) (Math.random()*tests.size());
        return tests.get(index);
    }

    @Override
    public void closeBase(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(tests);
            oos.flush();
            oos.close();
        }catch (IOException e){
            log.error("file base not write");
        }
    }

    @Override
    public boolean isEmpty(){
        return (tests.size() > 0) ? true : false;
    }

    private void openBase(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            tests = (ArrayList<Test>) ois.readObject();

        }catch (Exception e){
            log.error("file base not found");
        }
    }

}
