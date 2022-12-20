import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){
        View view= new View();
        Pane aPane= new Pane();
        aPane.getChildren().add(view);
        aPane.setStyle("-fx-background-color:#1F271B; -fx-font: 12 Verdana;");
        primaryStage.setTitle("ImmaBoredd");
        primaryStage.setResizable(false);
        Scene theScene= new Scene(aPane);
//        theScene.setFill(Color.web("#81c483"));
        primaryStage.setScene(theScene);

        primaryStage.show();

        view.getGenerateButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.update();
            }
        });
    }


}
