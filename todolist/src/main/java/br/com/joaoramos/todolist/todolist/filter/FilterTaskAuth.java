package br.com.joaoramos.todolist.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.joaoramos.todolist.todolist.user.IUserRepository;
import br.com.joaoramos.todolist.todolist.user.UserModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {


    @Autowired
    private IUserRepository iUserRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")){
            String authorization = request.getHeader("Authorization");
            String authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            String authString = new String(authDecode);
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            UserModel user = this.iUserRepository.findByUsername(username);

            if (user == null){
                response.sendError(401);
            }

            else{
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (result.verified){
                    request.setAttribute("idUser",user.getId());
                    filterChain.doFilter(request,response);
                }
                else{
                    response.sendError(401);
                }
            }
        }
        else{
            filterChain.doFilter(request,response);
        }
    }
}
