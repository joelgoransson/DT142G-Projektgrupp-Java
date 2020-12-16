/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

/**
 *
 * @author Sundsvall
 */
@Named(value = "indexBean")
@SessionScoped
public class IndexBean implements Serializable {

    private boolean everyOther = false;
    
     /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }

    public boolean isEveryOther() {
        everyOther = !everyOther;
        return everyOther;
    }

    public void setEveryOther(boolean everyOther) {
        this.everyOther = everyOther;
    }   
    
    public String getDayOfWeekString(){
        int dayOfWeek = DayOfWeekInt();
        return DayToString(dayOfWeek);
    }

    private String DayToString(int dayOfWeek) {

        Vector<String> weekDays = new Vector<>();
        weekDays.add("Måndag");
        weekDays.add("Tisdag");
        weekDays.add("Onsdag");
        weekDays.add("Torsdag");
        weekDays.add("Fredag");
      
        switch(dayOfWeek) {
            // Obs! 0 is sunday and 6 is saturday
            case 2:
                return weekDays.get(0);
            case 3:
                return weekDays.get(1);
            case 4:
                return weekDays.get(2);
            case 5:
                return weekDays.get(3);
            case 6:
                return weekDays.get(4);
        default:
            return "Ingen dagens på helger!";
        }
    }

    private int DayOfWeekInt() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_WEEK);
    }
}
