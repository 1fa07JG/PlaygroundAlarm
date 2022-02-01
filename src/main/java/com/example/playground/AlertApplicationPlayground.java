package com.example.playground;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlertApplicationPlayground extends Application {
    String message ="Nachricht";
/*AlertApplication(String text){
    message=text;
}*/

    @Override
    public void start(Stage stage) throws Exception {
        Alert a = new Alert(Alert.AlertType.INFORMATION,message);


        a.show();
    }





        public static void main(String args[])
        {
            launch(args);
        }
    public static void show()
    {
        launch();
    }

}
