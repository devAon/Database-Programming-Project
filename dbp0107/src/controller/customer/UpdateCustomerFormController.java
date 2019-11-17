package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.CustomerManager;
import model.Customer;

public class UpdateCustomerFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateCustomerController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String updateId = request.getParameter("customerId");
		
		log.debug("UpdateForm Request : {}", updateId);

		CustomerManager manager = CustomerManager.getInstance();
		Customer customer = manager.findCustomer(updateId);	// 사용자 정보 검색
		request.setAttribute("customer", customer);						
		
		HttpSession session = request.getSession();
		if (CustomerSessionUtils.isLoginCustomer(updateId, session) ||
			CustomerSessionUtils.isLoginCustomer("admin", session)) {
			// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
			
			return "/customer/updateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
		}
		// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
			new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
		return "/customer/view.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
    }
}


