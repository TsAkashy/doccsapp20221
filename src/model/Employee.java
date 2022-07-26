package model;
/* 

Name: Juan Diego Torres Hernandez 
Code:20212200521
Name: Hector Stiven Trujillo Trujillo
Code:20212200152

*/
public class Payment extends Client{
   private int hours_worked;
   private double payment_hours;
   private int hours_ext;
   private int total_hours;
   private double salary;
   public Payment (int hours_worked, int payment_hours){
       this.hours_worked=hours_worked;
       this.payment_hours=payment_hours;
   }
    public int getHours_worked() {
        return hours_worked;
    }
    public void setHours_worked(int hours_worked) {
        this.hours_worked = hours_worked;
    }
    public double getPayment_hours() {
        return payment_hours;
    }
    public void setPayment_hours(int payment_hours) {
        this.payment_hours = payment_hours;
    }
    public int getHours_ext() {
        return hours_ext;
    }
    public void setHours_ext(int hours_ext) {
        this.hours_ext = hours_ext;
    }
    public void generate_payment() {
   if (hours_worked > 40){
           hours_ext = hours_worked - 40;
           total_hours = hours_ext + 40;
           salary = total_hours * payment_hours;
    } else 
      salary = hours_worked * payment_hours;
       
    
}
}
