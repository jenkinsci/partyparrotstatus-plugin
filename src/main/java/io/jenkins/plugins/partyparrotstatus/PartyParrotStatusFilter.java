package io.jenkins.plugins.partyparrotstatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PartyParrotStatusFilter implements Filter {

    final Pattern pattern = Pattern.compile("/(\\d{2}x\\d{2})/(blue|red|yellow|nobuilt|aborted|folder|grey|edit-delete|clock|disabled)(_anime|)\\.(gif|png)");
    final Pattern anotherpattern = Pattern.compile("/(headshot)\\.(png)");
    final Pattern yetanotherpattern = Pattern.compile("/(favicon.ico)");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) req;
            final HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            final String uri = httpServletRequest.getRequestURI();
            String newImageUrl = mapImage(uri);
            if (newImageUrl != null) {
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(newImageUrl);
                dispatcher.forward(httpServletRequest, httpServletResponse);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    private String mapImage(String uri) {
        if (!uri.contains("plugin/partyparrotstatus/")) {
            Matcher m = pattern.matcher(uri);
            if (m.find()) {
                return "/plugin/partyparrotstatus/" + m.group(1) + "/" + m.group(2) + m.group(3) + "." + m.group(4);
            }
            Matcher anotherm = anotherpattern.matcher(uri);
            if (anotherm.find()) {
                return "/plugin/partyparrotstatus/origin/" + anotherm.group(1) + "." + anotherm.group(2);
            }
            Matcher yetanotherm = yetanotherpattern.matcher(uri);
            if (yetanotherm.find()) {
                return "/plugin/partyparrotstatus/origin/" + yetanotherm.group(1);
            }
        }
        return null;
    }

}