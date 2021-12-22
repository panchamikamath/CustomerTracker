package kamath.panchami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kamath.panchami.entity.Customer;
import kamath.panchami.service.CustomerService;
import kamath.panchami.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	/*@GetMapping("/getCustomers")
	public String getCusotmerList(Model theModel) {
		
		//get customers from service
		List<Customer> theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		
		//add customers to model
		theModel.addAttribute("customers", theCustomers);
		
		return "customer-list";
	}*/
	
	@GetMapping("/showFormForAdd")
	public String saveCustomer(Model theModel) {
		
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		return "add-customer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/getCustomers";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//get customer from attribute
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		
		return "add-customer";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel) {
		
		//get customer from attribute
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/getCustomers";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		// add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "customer-list";
	}
	
	@GetMapping("/getCustomers")
	public String sortCustomer(@RequestParam(required=false) String sort, Model theModel) {
		//get customers from service
		List<Customer> theCustomers = null;
		
		//check for sort field
		if(sort!=null) {
			int sortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(sortField);
		}
		else {
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
				
		//add customers to model
		theModel.addAttribute("customers", theCustomers);
				
		return "customer-list";
	}
}
