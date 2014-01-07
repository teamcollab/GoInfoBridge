package goinfo.filter;

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
        response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.100:8080");
        response.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers","Authorization, Content-Type, Origin, Accept");
        response.addHeader("Access-Control-Max-Age", "1800");
        response.addHeader("Access-Control-Allow-Credentials", "true");


        filterChain.doFilter(request, response);
    }
}
