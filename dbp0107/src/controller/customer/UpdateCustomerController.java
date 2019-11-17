package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.CustomerManager;
import model.Customer;

public class UpdateCustomerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateCustomerController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	Customer updateCustomer = new Customer(
    		request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("name"),
    		request.getParameter("email"),
    		request.getParameter("phone"));    
    	
    	log.debug("Update User : {}", updateCustomer);

		CustomerManager manager = CustomerManager.getInstance();
		manager.update(updateCustomer);			
        return "redirect:/customer/list";			
    }
}
