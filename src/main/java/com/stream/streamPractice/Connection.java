package com.stream.streamPractice;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class Connection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.stream.streamConnection");
		Datastore datastore = morphia.createDatastore(new MongoClient(), "ABCPvtLtd");
		EmployeeList.createEmployees(datastore);
		EmployeeList.groupByDept(datastore);
		EmployeeList.salaryAbove50k(datastore);
		EmployeeList.salaryInc(datastore);
		EmployeeList.toUpperCase(datastore);
		EmployeeList.sumOfSalary(datastore);
		EmployeeList.changeName(datastore);
	}
	
	

}
