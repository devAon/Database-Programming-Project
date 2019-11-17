package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Customer;
import model.dao.CustomerDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * CustomerDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class CustomerManager {
	private static CustomerManager customerMan = new CustomerManager();
	private CustomerDAO customerDAO;
	private CustomerAnalysis customerAanlysis;

	private CustomerManager() {
		try {
			customerDAO = new CustomerDAO();
			customerAanlysis = new CustomerAnalysis(customerDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CustomerManager getInstance() {
		return customerMan;
	}
	
	public int create(Customer customer) throws SQLException, ExistingCustomerException {
		if (customerDAO.existingCustomer(customer.getCustomerId()) == true) {
			throw new ExistingCustomerException(customer.getCustomerId() + "는 존재하는 아이디입니다.");
		}
		return customerDAO.create(customer);
	}

	public int update(Customer customer) throws SQLException {
		return customerDAO.update(customer);
	}	

	public int remove(String customerId) throws SQLException {
		return customerDAO.remove(customerId);
	}

	public Customer findCustomer(String customerId)
		throws SQLException, CustomerNotFoundException {
		Customer customer = customerDAO.findCustomer(customerId);
		
		if (customer == null) {
			throw new CustomerNotFoundException(customerId + "는 존재하지 않는 아이디입니다.");
		}		
		return customer;
	}

	public List<Customer> findCustomerList() throws SQLException {
			return customerDAO.findCustomerList();
	}
	
	public List<Customer> findCustomerList(int currentPage, int countPerPage)
		throws SQLException {
		return customerDAO.findCustomerList(currentPage, countPerPage);
	}

	public boolean login(String customerId, String password)
		throws SQLException, CustomerNotFoundException, PasswordMismatchException {
		Customer customer = findCustomer(customerId);

		if (!customer.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public List<Customer> makeFriends(String customerId) throws Exception {
		return customerAanlysis.recommendFriends(customerId);
	}
	
	public CustomerDAO getCustomerDAO() {
		return this.customerDAO;
	}
}

