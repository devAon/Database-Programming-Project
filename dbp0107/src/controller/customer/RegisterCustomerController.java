package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Customer;
import model.service.ExistingCustomerException;
import model.service.CustomerManager;

public class RegisterCustomerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterCustomerController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      Customer customer = new Customer(
         request.getParameter("customerId"),
         request.getParameter("password"),
         request.getParameter("name"),
         request.getParameter("email"),
         request.getParameter("phone"));
      
        log.debug("Create Customer : {}", customer);

      try {
         CustomerManager manager = CustomerManager.getInstance();
         manager.create(customer);
           return "redirect:/customer/list";      // 성공 시 사용자 리스트 화면으로 redirect
           
      } catch (ExistingCustomerException e) {      // 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("customer", customer);
         return "/customer/registerForm.jsp";
      }
    }
}