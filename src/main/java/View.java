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

import java.util.ArrayList;
import java.util.Map;

public class View extends Pane {
    private Label label2;
    private Button generateBtn;
    private ListView<String> rList;

    public View(){

        generateBtn = new Button("Let's do Something!!");
        generateBtn.setPrefSize(160,30);
        generateBtn.setPadding(new Insets(2d,3d,5d,3d));
        generateBtn.setStyle("-fx-font: 12 Georgia; -fx-base: #75B9BE; -fx-text-fill: rgb(255,255,255); -fx-border-color:#75B9BE; -fx-border-radius: 5 ");
        generateBtn.relocate(30,30);

        rList = new ListView<String>();
        rList.setPrefSize(400,240);
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
                            setTextFill(Color.web("#C16CB2"));
                            setStyle("-fx-background-color: #545E75; -fx-background-radius: 7; -fx-padding: 3;-fx-font: 15 Georgia");
                        }
                    }
                };
            }
        });

        getChildren().addAll(generateBtn, rList);
        setPrefSize(455,385);
    }

    public Button getGenerateButton(){
        return generateBtn;
    }

    public ListView<String> getResultList(){
        return rList;
    }

    public void update(){
        ActivityGenerator generator= new ActivityGenerator();
        try {
            ArrayList<String> result = new ArrayList<>();
            Map<String, Object> allData= generator.generateActivity();
//            for(String key: allData.keySet()){
//                result.add(key+ " --> "+ allData.get(key));
//            }
            result.add("Hmmm..You could.. ");
            result.add(((String)allData.get("activity")).toUpperCase()+"!");
            result.add("You need "+ allData.get("participants")+ " people for this activity!");
            result.add("=====================================");
            result.add("Category: " + allData.get("type"));
            result.add("Price: " + allData.get("price"));
            result.add("Accessibility: " + allData.get("accessibility"));
            if(!allData.get("link").equals("")) {
                result.add("Check this out: " + allData.get("link"));
            }else{
              result.add("");
            };


            rList.setItems(FXCollections.observableArrayList(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
