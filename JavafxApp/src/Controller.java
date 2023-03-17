import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    
    @FXML
    private Spinner<Integer> spinnerTemperature;

    int currentValue;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        currentValue = spinnerTemperature.getValue();
        System.out.println(currentValue);
    }

}
