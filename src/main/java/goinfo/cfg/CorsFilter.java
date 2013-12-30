package goinfo.cfg;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        System.out.println("request.getMethod() = " + request.getMethod());
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod()))
        {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-Requested-With, Origin, Content-Type, Accept, user");
            response.addHeader("Access-Control-Max-Age", "1800");
        }

        filterChain.doFilter(request, response);
    }
}
