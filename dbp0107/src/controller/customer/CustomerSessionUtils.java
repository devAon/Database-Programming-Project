package controller.customer;

import javax.servlet.http.HttpSession;

public class CustomerSessionUtils {
    public static final String USER_SESSION_KEY = "customerId";

    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginCustomerId(HttpSession session) {
        String customerId = (String)session.getAttribute(USER_SESSION_KEY);
        return customerId;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginCustomerId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 customerId인지 검사 */
    public static boolean isLoginCustomer(String customerId, HttpSession session) {
        String loginCustomer = getLoginCustomerId(session);
        if (loginCustomer == null) {
            return false;
        }
        return loginCustomer.equals(customerId);
    }
}