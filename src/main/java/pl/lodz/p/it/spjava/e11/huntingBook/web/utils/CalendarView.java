package pl.lodz.p.it.spjava.e11.huntingBook.web.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CalendarView implements Serializable {

    private Date minTimeToStart;
    private Date minTimeToEnd;
    private Date maxTimeToStart;
    private Date maxTimeToEnd;

    @PostConstruct
    public void init() {

        Calendar tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 16);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        minTimeToStart = tmp.getTime();

        tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 7);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        maxTimeToStart = tmp.getTime();
        
        tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 16);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        minTimeToEnd = tmp.getTime();
        
        tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 9);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        maxTimeToEnd = tmp.getTime();
    }

    public Date getMinTimeToStart() {
        return minTimeToStart;
    }

    public void setMinTimeToStart(Date minTimeToStart) {
        this.minTimeToStart = minTimeToStart;
    }

    public Date getMinTimeToEnd() {
        return minTimeToEnd;
    }

    public void setMinTimeToEnd(Date minTimeToEnd) {
        this.minTimeToEnd = minTimeToEnd;
    }

    public Date getMaxTimeToStart() {
        return maxTimeToStart;
    }

    public void setMaxTimeToStart(Date maxTimeToStart) {
        this.maxTimeToStart = maxTimeToStart;
    }

    public Date getMaxTimeToEnd() {
        return maxTimeToEnd;
    }

    public void setMaxTimeToEnd(Date maxTimeToEnd) {
        this.maxTimeToEnd = maxTimeToEnd;
    }
    
    
}
