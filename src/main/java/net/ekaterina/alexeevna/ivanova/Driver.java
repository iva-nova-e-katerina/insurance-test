/*
Copyright (c) 2016  Ekaterina Alexeevna Ivanova. All rights reserved.
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package net.ekaterina.alexeevna.ivanova;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ekaterina A. Ivanova (iceja.net)
 * date 10 February 2016
 */
public class Driver {
    
    
    private String firstname, lastname, patronymic;
    private LocalDate dateOfBirth;
    private int Age;
    private String gender;
    private String category;
    
    private Driver(){
        
    }

    public Driver (String firstname, String lastname, String patronymic, LocalDate dateOfBirth, Gender gender, String category){
           this.firstname = firstname;
           this.lastname = lastname;
           this.patronymic = patronymic;
           this.dateOfBirth = dateOfBirth;
           this.gender = gender.name;
           this.category = category;
           this.Age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public int hashCode(){
        if(dateOfBirth == null || lastname == null || category == null )
            return 0;
        int code = dateOfBirth.getYear()  + (dateOfBirth.getMonthValue()  * 10000) 
        + (dateOfBirth.getDayOfMonth() * 1000000) + (lastname.charAt(0) * 100000000) + 
                (category.charAt(0)  * 1000000000);
                
        return code;
        
    } 

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Driver other = (Driver) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.patronymic, other.patronymic)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
    
    
    @Override
     public String toString(){
         System.out.println("ping");
         return getFullName();
     }
    /**
     * @return the fullName
     */
    public String getFullName() {
        return this.getFirstname() + " " + this.getPatronymic() + " " + this.getLastname();
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        return;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        Instant ins = dateOfBirth.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date oldstyleDate = Date.from(ins);
        return oldstyleDate;
    }
    
    public String getDateAsString() {
    
        return dateOfBirth.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
     public String getDateAsStringExcludeNow() {
        if(LocalDate.now().isEqual(dateOfBirth))
            return "";
        return dateOfBirth.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        Instant ins = Instant.ofEpochMilli(dateOfBirth.getTime());
        LocalDate local = LocalDateTime.ofInstant(ins, ZoneId.systemDefault()).toLocalDate();
        this.Age = Period.between(local, LocalDate.now()).getYears();
        this.dateOfBirth = local;
    }

    /**
     * @return the Age
     */
    public int getAge() {
        return Age;
    }

    /**
     * @param Age the Age to set
     */
    public void setAge(int Age) {
        this.Age = Age;
    }

    /**
     * @return the genger
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param genger the genger to set
     */
    public void setGender(String genger) {
        this.gender = genger;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * @param patronymic the patronymic to set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
            
    public enum Gender {
        FEMALE("Ж"),
        MALE("М");
        String name ;
        Gender(String n){
            this.name = n;
        }
    
    }
            
    
}
