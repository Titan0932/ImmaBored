import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.postgresql.jdbc.SslMode;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class View extends Pane {
    private Label label2;
    private Button generateBtn, saveBtn;
    private ListView<String> rList;
    private Map<String, Object> curActivity;

    public View(){

        generateBtn = new Button("Let's do Something!!");
        generateBtn.setPrefSize(135,30);
        generateBtn.setPadding(new Insets(3d,2d,4d,2d));
        generateBtn.setStyle("-fx-font: 12 Georgia; -fx-base: #75B9BE; -fx-text-fill: #000000; -fx-border-color:#75B9BE; -fx-border-radius: 5 ");
        generateBtn.relocate(30,30);

        rList = new ListView<String>();
        rList.setPrefSize(430,240);
        rList.relocate(30,80);
        rList.setStyle("-fx-background-radius: 10; -fx-background-color: #545E75; -fx-padding: 7");
        rList.setPadding(new Insets(2d,5d,2d,3d));
        rList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setTextFill(Color.web("#75B9BE"));
                            setStyle("-fx-background-color: #545E75; -fx-background-radius: 7; -fx-padding: 3;-fx-font: 15 Georgia");
                        }
                    }
                };
            }
        });
        saveBtn= new Button("Save Activity!");
        saveBtn.setPrefSize(100, 20);
        saveBtn.relocate(357,330);
        saveBtn.setStyle("-fx-base: #CF5C36; -fx-text-fill: #000000");
        saveBtn.setPadding(new Insets(3d,3d,4d,3d));
        getChildren().addAll(generateBtn, rList, saveBtn);
        setPrefSize(520,405);
    }

    public Button getGenerateButton(){
        return generateBtn;
    }

    public ListView<String> getResultList(){
        return rList;
    }

    public Button getSaveButton(){
        return saveBtn;
    }

    public void updateGenerate(){
        ActivityGenerator generator= new ActivityGenerator();
        try {
            ArrayList<String> result = new ArrayList<>();
            Map<String, Object> allData= generator.generateActivity();
            this.curActivity= allData;
//            for(String key: allData.keySet()){
//                result.add(key+ " --> "+ allData.get(key));
//            }
            result.add("Hmmm..You could.. ");
            result.add(((String)allData.get("activity")).toUpperCase()+"!!");
            result.add("=====================================");
            result.add("You need "+ allData.get("participants")+ " people for this activity!");
            result.add("Category: " + allData.get("type"));
            result.add("Price: " + allData.get("price"));
            result.add("Accessibility: " + allData.get("accessibility"));
            if(!allData.get("link").equals("")) {
                result.add("Check this out: " + allData.get("link"));
            }else{
              result.add("");
            };
            result.add("");


            rList.setItems(FXCollections.observableArrayList(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateSave() {
        try{
            Class.forName("org.postgresql.Driver");
            String jdbcURL= "jdbc:postgresql://localhost:5432/ImmaBoredd";
            String username= "postgres";
            String password= "admin";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sqlValues=String.format("'%s','%s','%f','%f','%d','%s', '%d'",this.curActivity.get("activity"),
                    this.curActivity.get("type"),
                    ((Double)this.curActivity.get("price")).floatValue(),
                    ((Double)this.curActivity.get("accessibility")).floatValue(),
                    Math.round((double)this.curActivity.get("participants")),
                    this.curActivity.get("link"),
                    Integer.parseInt((String) this.curActivity.get("key")));
            String sqlCommand= "INSERT INTO activities (\"Activities\", \"Category\", \"Price\", \"Accessibility\", \"Participants\", \"Link\", \"id\")" +
                    " VALUES("+ sqlValues+")";
            System.out.println(sqlCommand);
            Statement statement= connection.createStatement();
            int rows = statement.executeUpdate(sqlCommand);
            if(rows>0) System.out.println("ADDED ROW");
            System.out.println("SAVINGG");
            connection.close();
        }catch (SQLIntegrityConstraintViolationException | ClassNotFoundException e){
            System.out.println("DUPLICATE RECORD ADDED!!");
            e.printStackTrace();
        }
        catch(SQLException e){
            System.out.println("ERROR CONNECTING TO DB!!");
            e.printStackTrace();
    }}
}
