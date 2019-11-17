package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.CustomerManager;
import model.service.CustomerNotFoundException;
import model.Customer;

public class ViewCustomerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!CustomerSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/customer/login/form";		// login form 요청으로 redirect
        }
    	
		CustomerManager manager = CustomerManager.getInstance();
		String customerId = request.getParameter("customerId");
		
		Customer customer = null;
		try {
			customer = manager.findCustomer(customerId);	// 사용자 정보 검색
		} catch (CustomerNotFoundException e) {				
	        return "redirect:/customer/list";
		}	
		
		request.setAttribute("customer", customer);		// 사용자 정보 저장				
		return "/customer/view.jsp";				// 사용자 보기 화면으로 이동
    }
}
