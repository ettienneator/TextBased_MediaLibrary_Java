package project1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ettienne Martinez
 */
public class Item {
    
    private String Title;
    private String Format;
    private boolean Availability = true;
    private String NameLoaning;
    private String Date;

    Item(String Title, String Format) {
        this.Title = Title.trim();
        this.Format = Format.trim();
    }
    
    public String getTitle() {
        return Title;
    }

    public String getFormat() {
        return Format;
    }

    public boolean isAvailable() {
        return Availability;
    }
    
    public void setIsAvailable(boolean isAvailable){
        this.Availability = isAvailable;
    }

    public String getNameLoaning() {
        return NameLoaning;
    }

    public void setNameLoaning(String NameLoaning) {
        this.NameLoaning = NameLoaning.trim();
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date.trim();
    }

    @Override
    public String toString() {
        return "Title=" + Title + ", Format=" + Format + ", Availability=" + Availability + ", NameLoaning=" + NameLoaning + ", Date=" + Date + '}';
    }
    
    public String niceToString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getTitle() + " - " + getFormat());
        if(!isAvailable()){
            sb.append("(" + getNameLoaning() + ", " + getDate() + ")");
        }
        return sb.toString();
    }
    
    public String writeToString(){
        //Zelda: Breath of the Wild-Switch - false - Bob1 - 11,09,16
        StringBuffer sb = new StringBuffer();
        sb.append(getTitle());
        sb.append("-");
        sb.append(getFormat());
        sb.append("-");
        sb.append(isAvailable());
        sb.append("-");
        sb.append(getNameLoaning());
        sb.append("-");
        sb.append(getDate());
        return sb.toString();
    }
    
}
