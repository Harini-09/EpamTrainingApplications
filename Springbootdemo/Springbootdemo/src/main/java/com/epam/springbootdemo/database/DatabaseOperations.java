package com.epam.springbootdemo.database;

public interface DatabaseOperations {
   Object viewData(Object object);
   Object addData(Object object);
   Object updateData(Object object,int questionID,int choice);
   boolean deleteData(Object object);
   Object getAllData();
}
