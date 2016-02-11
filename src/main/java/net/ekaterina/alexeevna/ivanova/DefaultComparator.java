/*
 Copyright (c) 2016 Ekaterina Alexeevna Ivanova. All rights reserved.
 PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
 use.
 */
package net.ekaterina.alexeevna.ivanova;
/**
 *
 * @author Ekaterina Alexeevna Ivanova
 * @since 10 February 2016
 */
import java.util.Comparator;

    public class DefaultComparator implements Comparator<Driver> {

        @Override
        public int compare(Driver o1, Driver o2) {
            if(o1 == null || o2 == null){
                return 0;
            } else if(o1.equals(o2)){
                return 0;
            } 
            
            return o1.getLastname().compareTo(o2.getLastname());
        }
        
    }