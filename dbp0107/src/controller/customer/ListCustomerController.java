package controller.customer;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Customer;
import model.service.CustomerManager;

public class ListCustomerController implements Controller {
   // private static final int countPerPage = 100;   // 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
      // 로그인 여부 확인
       if (!CustomerSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/customer/login/form";      // login form 요청으로 redirect
        }
       
       /*
       String currentPageStr = request.getParameter("currentPage");   
      int currentPage = 1;
      if (currentPageStr != null && !currentPageStr.equals("")) {
         currentPage = Integer.parseInt(currentPageStr);
      }      
       */
       
      CustomerManager manager = CustomerManager.getInstance();
      List<Customer> customerList = manager.findCustomerList();
      // List<Customer> customerList = manager.findCustomerList(currentPage, countPerPage);

      // customerList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
      request.setAttribute("customerList", customerList);            
      request.setAttribute("curCustomerId", 
            CustomerSessionUtils.getLoginCustomerId(request.getSession()));      

      // 사용자 리스트 화면으로 이동(forwarding)
      return "/customer/list.jsp";        
    }
}