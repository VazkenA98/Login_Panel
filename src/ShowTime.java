import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class ShowTime extends JLabel implements ActionListener
{
    String type;
    SimpleDateFormat sdf;
    public ShowTime(String type)
    {
        this.type = type;
        setForeground(Color.green);
        switch (type)
        {
            case "date" : sdf = new SimpleDateFormat("  dd MM yyyy");
                break;
            case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
                break;
            case "day"  : sdf = new SimpleDateFormat("EEEE  ");
                break;
            default     : sdf = new SimpleDateFormat();
                break;
        }
    }

    public String getTime()
    {
    Date d = new Date();
    return (sdf.format(d));
    }
    public String getInspDate() throws ParseException
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString2 = "19 07 2019";
        String inputString1 = getTime();
        Date date1 = myFormat.parse(inputString1);
        Date date2 = myFormat.parse(inputString2);
        long diff = date2.getTime() - date1.getTime();
        long s = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        String str = Long.toString(s);
        return "Days: " +str;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Date d = new Date();
        setText(sdf.format(d));
        System.out.println(sdf.format(d));
    }
}