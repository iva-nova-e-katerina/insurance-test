/*
 Copyright (c) 2016  Ekaterina Alexeevna Ivanova. All rights reserved.
 PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
 use.
 */
package net.ekaterina.alexeevna.ivanova;

import java.time.LocalDate;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ekaterina A. Ivanova (iceja.net)
 * date 10 February 2016
 */
@ManagedBean(name = "drivers")
@SessionScoped
public class DriverJSFManagedBean {

    private SortedSet<Driver> allDrivers;
    private SortedSet<Driver> selectedDrivers;
    private TreeMap<String, String> categories = new TreeMap<String, String>();
    private SortedSet<Driver> result;
    private Driver newone = new Driver("", "", "", LocalDate.now(), Driver.Gender.FEMALE, "");

    {
        //getCategories().put("A", "A");
        getCategories().put("B", "B");
        getCategories().put("C", "C");
        getCategories().put("D", "D");
        getCategories().put("E", "E");
    }

    public String[] genderValues() {
        return new String[]{"Ж", "М"};
    }

    /**
     * Creates a new instance of DriverJSFManagedBean
     */
    public DriverJSFManagedBean() {
        System.out.println("DriverJSFManagedBean");
    }

    public void doRemove(Driver foRemove) {
        System.out.println("doRemove " + newone.getFirstname());
        this.selectedDrivers.remove(foRemove);
        this.result = new TreeSet<Driver>(new DefaultComparator());
        newone = new Driver("", "", "", LocalDate.now(), Driver.Gender.FEMALE, "");
    }

//    public void doClear() {
//        System.out.println("doClear " + newone.getFirstname());
//        this.result = new TreeSet<Driver>(new DefaultComparator());
//    }
    public void doSave() {
        System.out.println("doSave " + newone.getFirstname());
    }

    public void doEdit(Driver drv) {
        if (drv != null) {
            this.newone = drv;
        } else {
            newone = new Driver("", "", "", LocalDate.now(), Driver.Gender.FEMALE, "");
        }

    }

    public void doSearch(String text) {
        System.out.println("ajax " + text);
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        this.allDrivers = (SortedSet<Driver>) session.get("ddrivers");
        SortedSet<Driver> res = new TreeSet<Driver>(new DefaultComparator());
        if (!"".equals(text)) {
            for (Driver drv : this.allDrivers) {
                if (drv.getFullName().toLowerCase().trim().contains(text.trim().toLowerCase())) {
                    res.add(drv);
                }
            }
        }

        if (res.size() == 0) {
            res.add(new Driver("совпадений", "найдено", "не", LocalDate.now(), Driver.Gender.FEMALE, "D"));
        }

//        int rnd = (int) (Math.random() * 10);
//        for (int i = 0; i < rnd; i++) {
//            res.add(new Driver("test " + (int) (Math.random() * 10), "test" + i, "test", LocalDate.now(), Driver.Gender.FEMALE, "D"));
//        }
        setResult(res);
        return;
    }

    public void doAdd(Driver text) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        this.selectedDrivers = (SortedSet<Driver>) session.get("selecteddrivers");
        System.out.println("doAdd " + text + " this.selectedDrivers  " + this.selectedDrivers);
        this.selectedDrivers.add(text);
        this.result = new TreeSet<Driver>(new DefaultComparator());
        return;
    }

    /**
     * @return the result
     */
    public SortedSet<Driver> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(SortedSet<Driver> result) {
        this.result = result;
    }

    /**
     * @return the allDrivers
     */
    public SortedSet<Driver> getAllDrivers() {
        return allDrivers;
    }

    /**
     * @param allDrivers the allDrivers to set
     */
    public void setAllDrivers(SortedSet<Driver> allDrivers) {
        this.allDrivers = allDrivers;
    }

    /**
     * @return the selectedDrivers
     */
    public SortedSet<Driver> getSelectedDrivers() {
        return selectedDrivers;
    }

    /**
     * @param selectedDrivers the selectedDrivers to set
     */
    public void setSelectedDrivers(SortedSet<Driver> selectedDrivers) {
        this.selectedDrivers = selectedDrivers;
    }

    /**
     * @return the newone
     */
    public Driver getNewone() {
        return newone;
    }

    /**
     * @param newone the newone to set
     */
    public void setNewone(Driver newone) {
        this.newone = newone;
    }

    /**
     * @return the categories
     */
    public TreeMap<String, String> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(TreeMap<String, String> categories) {
        this.categories = categories;
    }

}
