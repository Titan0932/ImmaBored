import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
                view.updateGenerate();
            }
        });

        view.getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.updateSave();
            }
        });

        view.getMyActivitiesBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.updateBookmarkWindow();
            }
        });

        view.getGenerateWindowBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.updateGenerateWindow();
            }
        });

        view.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.updateDeleteRow();
            }
        });

        view.getMyActivitiesTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                view.getDeleteBtn().setDisable(false);
            } else {
                view.getDeleteBtn().setDisable(true);
            }
        });

    }


}
