package kamath.panchami.service;

import java.util.List;

import kamath.panchami.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers(int sortField);

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);
}
