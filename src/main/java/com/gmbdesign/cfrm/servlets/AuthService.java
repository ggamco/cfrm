package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.utils.AuthCodeGenerator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gustavogamboa
 */
public class AuthService extends HttpServlet {

    private static final long serialVersionUID = -5634011574248327214L;
	private static final String PUBLIC_KEY = new AuthCodeGenerator().generateAuthCode();
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                
        HttpSession session = req.getSession();
        session.setAttribute("publicKey", PUBLIC_KEY);
        session.setAttribute("systemCode", new AuthCodeGenerator().generateAuthCode());
        
    }
}
