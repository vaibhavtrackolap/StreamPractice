package com.stream.streamPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.stream.model.Employee;

public class EmployeeList {
	static void createEmployees(Datastore datastore) {
		List<Employee> ls = new ArrayList<Employee>();
		ls.add(new Employee(101, "Virat Kohli", "IT", 20000));
		ls.add(new Employee(103, "Jasprit Bumrah", "Sales", 35000));
		ls.add(new Employee(145, "Rohit Sharma", "Operations", 78000));
		ls.add(new Employee(178, "Amitabh", "IT", 90000));
		ls.add(new Employee(160, "Umesh Yadav", "Sales", 56000));
		ls.add(new Employee(136, "Hanuma Vihari", "HR", 28000));
		ls.add(new Employee(146, "Dwayne Johnson", "Operations", 38000));
		ls.add(new Employee(156, "Will Smith", "HR", 48000));
		ls.add(new Employee(166, "Lionel Messi", "Sales", 58000));
		ls.add(new Employee(176, "Shardul Thakur", "HR", 68000));
		
		datastore.save(ls);
		System.out.println("Data added to database successfully");
		System.out.println("-----------");
	}

	//Number of employees in each department using stream(), filter(), count()
	static void groupByDept(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		
		List<Employee> ls = qr.asList();
		
		long countIT = ls.stream()
			.filter(obj -> "IT".equals(obj.getDepartment()))
			.count();
		System.out.println("Employees in IT department is "+ countIT);
		
		long countHR = ls.stream()
			.filter(obj -> "HR".equals(obj.getDepartment()))
			.count();
		System.out.println("Employees in HR department is "+ countHR);
			
		long countSales = ls.stream()
			.filter(obj -> "Sales".equals(obj.getDepartment()))
			.count();
		System.out.println("Employees in Sales department is "+ countSales);
				
		long countOperations = ls.stream()
			.filter(obj -> "Operations".equals(obj.getDepartment()))
			.count();
		System.out.println("Employees in Operations department is "+ countOperations);
		System.out.println("-----------");
	}
	
	//List of employees having salary more than 50k using filter, stream, collect
	static void salaryAbove50k(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		
		List<Employee> ls = qr.asList();
		
		List<Employee> list = ls.stream()
			.filter(obj -> obj.getSalary() > 50000)
			.collect(Collectors.toList());
		
		System.out.println("List of employees whose salary is above 50 thousand ");
		System.out.println(list);
		System.out.println("-----------");
	}
	
	//Incrementing salary by 10% of sales department using filter and map
	static void salaryInc(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		UpdateOperations<Employee> updates = datastore.createUpdateOperations(Employee.class);
		
		List<Employee> ls = qr.asList();
		
		List<Employee> list = ls.stream()
				.filter(obj->"Sales".equals(obj.getDepartment()))
				.map(obj->{
					Employee o = new Employee(obj.getId(),obj.getEmpName(),obj.getDepartment(),obj.getSalary());
					o.setSalary(o.getSalary()+(o.getSalary()*0.1));
					return o;
				})
				.collect(Collectors.toList());
		System.out.println(list.toString());
		datastore.save(list);
		
		
		System.out.println("-----------");
	}
	
	//Coverting to uppercase using stream map()
	static void toUpperCase(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);

		List<Employee> ls = qr.asList();
		
		ls.stream()
		.map(obj->obj.getEmpName().toUpperCase())
		.forEach(System.out::println);
		System.out.println("-----------");
	}
	
	//sorting employees on Id
	static void sumOfSalary(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);

		List<Employee> ls = qr.asList();
		
		List<Employee> sortedList = ls.stream()
				.sorted((e1,e2)-> e1.getId() - e2.getId())
				.collect(Collectors.toList());
	 
		System.out.println(sortedList);
	}
	
	//changing name of an employee
	static void changeName(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);

		List<Employee> ls = qr.asList();
		
		List<Employee> sortedList = ls.stream()
				.filter(obj->"166".equals(obj.getId()))
				.map(obj->{
					Employee o = new Employee(obj.getId(),obj.getEmpName(),obj.getDepartment(),obj.getSalary());
					o.setEmpName("Dinesh Kartik");
					return o;
				})
				.collect(Collectors.toList());
		System.out.println(sortedList.toString());
		
	}
}
