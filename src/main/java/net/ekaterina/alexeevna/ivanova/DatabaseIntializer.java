/*
 Copyright (c) 2016 Ekaterina Alexeevna Ivanova. All rights reserved.
 PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
 use.
 */
package net.ekaterina.alexeevna.ivanova;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.ekaterina.alexeevna.ivanova.Driver.Gender;

/**
 *
 * @author Ekaterina A. Ivanova (iceja.net)
 * date 10 February 2016
 */
public class DatabaseIntializer implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public DatabaseIntializer() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DatabaseIntializer:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DatabaseIntializer:DoAfterProcessing");
        }

    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("DatabaseIntializer:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        SortedSet<Driver> allDrivers = (SortedSet<Driver>)session.getAttribute("ddrivers");
        //System.out.println("ddrivers" + allDrivers  );
        if(allDrivers == null){
            allDrivers = initializePseudoDatabase();
            session.setAttribute("ddrivers", allDrivers);
        }
        SortedSet<Driver> selectedDrivers = (SortedSet<Driver>)session.getAttribute("selecteddrivers");
        //System.out.println("selecteddrivers" + selectedDrivers );
        if(selectedDrivers == null){
            session.setAttribute("selecteddrivers", new TreeSet<Driver>(new DefaultComparator()) );
        }
 
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }
    

    
    private SortedSet<Driver> initializePseudoDatabase(){
        SortedSet<Driver> drivers = new TreeSet<Driver>(new DefaultComparator()) ;
        drivers.add(new Driver ("Екатерина", "Иванова", "Алексеевна", LocalDate.of(1978, 10,11), Gender.FEMALE, "E"));
        drivers.add(new Driver ("Марина", "Иванова", "Сергеевна", LocalDate.of(1989, 10,11), Gender.FEMALE, "E"));
        drivers.add(new Driver ("Ирина", "Иванова", "Алексеевна", LocalDate.of(1978, 12,11), Gender.FEMALE, "E"));
        drivers.add(new Driver ("Екатерина", "Иванова", "Васильевна", LocalDate.of(1988, 10,11), Gender.FEMALE, "E"));
        drivers.add(new Driver ("Андрей", "Иванов", "Николаевич", LocalDate.of(1979, 10,11), Gender.MALE, "C"));
        drivers.add(new Driver ("Емельян", "Иванников", "Алексеевич", LocalDate.of(1980, 10,11),Gender.MALE, "D"));
        drivers.add(new Driver ("Семён", "Ивашкин", "Анатольевич", LocalDate.of(1981, 10,21),Gender.MALE, "D"));
        drivers.add(new Driver ("Евгений", "Ивашкин", "Алексеевич", LocalDate.of(1982, 10,11), Gender.MALE, "D"));
        drivers.add(new Driver ("Юрий", "Смирнов", "Петрович", LocalDate.of(1983, 10,11), Gender.MALE, "C"));
        drivers.add(new Driver ("Илья", "Петров", "Дмитриевич", LocalDate.of(1984, 11,11), Gender.MALE, "D"));
        drivers.add(new Driver ("Глеб", "Иванченко", "Артурович", LocalDate.of(1985, 10,11), Gender.MALE, "D"));
        drivers.add(new Driver ("Александра", "Иванованникова", "Александровна", LocalDate.of(1976, 10,01), Gender.FEMALE, "E"));
        drivers.add(new Driver ("Эдуард", "Ивакин", "Евгеньевич", LocalDate.of(1986, 10,11), Gender.MALE, "D"));
        drivers.add(new Driver ("Роман", "Киселёв", "Владимирович", LocalDate.of(1978, 1,11), Gender.MALE, "B"));
        drivers.add(new Driver ("Дмитро", "Иваненко", "Данилович", LocalDate.of(1987, 10,19), Gender.MALE, "D"));
        drivers.add(new Driver ("Елизавета", "Иванкина", "Андреевна", LocalDate.of(1978, 10,11), Gender.FEMALE, "E"));
        
        return drivers;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("DatabaseIntializer:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DatabaseIntializer()");
        }
        StringBuffer sb = new StringBuffer("DatabaseIntializer(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
