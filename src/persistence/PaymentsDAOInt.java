//Juan Diego Torres Hernandez          Codigo : 20212200521
//Hector Stiven Trujillo Trujillo      Codigo : 20212200152
package persistence;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import model.Payment;


public interface PaymentsDAOInt {
    public List<Payment> listPayments() throws FileNotFoundException, IOException, ClassNotFoundException;
    public String addPayments(Payment payment) throws FileNotFoundException, IOException;
    public void deletePayments(Payment payment);
    public void updatePayments(Payment payment);
}