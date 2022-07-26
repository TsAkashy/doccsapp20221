//Juan Diego Torres Hernandez          Codigo : 20212200521
//Hector Stiven Trujillo Trujillo      Codigo : 20212200152
package persistence;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;

public class PaymentsDAOImp implements PaymentsDAOInt {
    
    File file = new File("payments.dat");

   
    @Override
    public List<Payment> listPayments() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Payment> payment = new ArrayList<Payment>();
        FileInputStream fileInput = new FileInputStream(file);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        try {
            while (fileInput.available()!=0){
                payment.add((Payment) objectInput.readObject());
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        }
        objectInput.close();
        fileInput.close();
        return payment;
    }
    @Override
    public String addPayments(Payment payment) throws FileNotFoundException, IOException {
        String msg="";
        
        if (payment != null && !validateIdPayments(payment)) {
            try {
                FileOutputStream fileOutput = null;
                
                fileOutput = new FileOutputStream(file, true);
               
                if (file.length() == 0) {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                    objectOutput.writeObject(payment);
                    objectOutput.close();
                } 
             
                else {
                    MyObjectOutputStream myobjectOutput = null;
                    myobjectOutput = new MyObjectOutputStream(fileOutput);
                    myobjectOutput.writeObject(payment);
                    myobjectOutput.close();
                }
                msg="Payment added!";
                fileOutput.close();
            } catch (IOException e) {
                System.out.println("Error Occurred" + e);
            }
        }
        else {
            msg="Payment exists!!";
        }
        return msg;
    }

    @Override
    public void deletePayments(Payment payment) {
        
        if (validateIdPayments(payment)) {
            
            File fileIn = new File("payment.dat");
            File fileTmp = new File("paymenttmp.dat");
            try ( FileInputStream flowIn = new FileInputStream(fileIn);  FileOutputStream flowOut = new FileOutputStream(fileTmp)) {
                ObjectInputStream reader= new ObjectInputStream (flowIn);
                ObjectOutputStream writer = new ObjectOutputStream(flowOut);
                Payment c;
                while (flowIn.available()!=0) {
                    c = (Payment) reader.readObject();
                   
                    if (c.getId().equals(payment.getId())==false) {
                        writer.writeObject(c);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaymentsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {    
                Logger.getLogger(PaymentsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
            }
           
            fileIn.delete();
            fileTmp.renameTo(fileIn);
        }        
    }

    @Override
    public void updatePayments(Payment payment){
        if (validateIdPayments(payment)) {
            File fileIn = new File("letter.dat");
            File fileTmp = new File("lettertmp.dat");
            try ( FileInputStream flowIn = new FileInputStream(fileIn);  FileOutputStream flowOut = new FileOutputStream(fileTmp)) {
                ObjectInputStream reader= new ObjectInputStream (flowIn);
                ObjectOutputStream writer = new ObjectOutputStream(flowOut);
                Payment c;
                while (flowIn.available()!=0) {
                    c = (Payment) reader.readObject();
                   
                    if (c.getId().equals(payment.getId())==false) {
                        writer.writeObject(c);
                    }
                    else {
                        writer.writeObject(payment);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaymentsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {    
                Logger.getLogger(PaymentsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
            }
            
            fileIn.delete();
            fileTmp.renameTo(fileIn);
        }        
    }
    
   
    public boolean validateIdPayments(Payment payment){
        boolean status = false;
        try {
            var payments = listPayments();
            for (Payment c : payments) {
                status = c.getId().equals(payment.getId());
                if (status)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }
  }