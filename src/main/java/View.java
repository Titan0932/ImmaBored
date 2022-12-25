import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javafx.geometry.Pos.CENTER;

public class View extends Pane {
    private Label error, success;
    private final Button generateBtn, saveBtn, myActivitiesBtn, generateWindowBtn, delDataBtn;
    private ListView<String> rList;
    private final VBox vbox;
    private Map<String, Object> curActivity;
    private TableView myActivitiesTable;

    public View(){
        double VIEWWIDTH, VIEWHEIGHT, DATAWINDOW_WIDTH, DATAWINDOW_HEIGHT, VIEW_LEFTPADDING, VIEW_RIGHTPADDING;
        VIEWWIDTH=920;
        VIEWHEIGHT=805;
        VIEW_LEFTPADDING=30;
        VIEW_RIGHTPADDING=60;
        DATAWINDOW_HEIGHT=640;
        DATAWINDOW_WIDTH=VIEWWIDTH-VIEW_LEFTPADDING-VIEW_RIGHTPADDING;
        String TABLECOLUMN_STYLES= "-fx-background-color: #545E75; -fx-border-color: #75B9BE; -fx-text-fill: #FFFFFF; -fx-wrap-text: true;";



        rList = new ListView<String>();
        rList.setPrefSize(DATAWINDOW_WIDTH,DATAWINDOW_HEIGHT);
        rList.relocate(VIEW_LEFTPADDING,80);
        rList.setStyle("-fx-background-radius: 10; -fx-background-color: #545E75;");
        rList.setPadding(new Insets(2d,5d,2d,3d));
        rList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                            setText(item);
                            setTextFill(Color.web("#75B9BE"));
                            setStyle("-fx-background-color: #545E75; -fx-background-radius: 7; -fx-padding: 3;-fx-font: 15 Georgia");
                    }
                };
            }
        });
        //============
        myActivitiesTable= new TableView();
        myActivitiesTable.setEditable(true);

        TableColumn<Activity, String> activitiesColumn = new TableColumn<>("Activities");
        TableColumn<Activity, String> categoryColumn = new TableColumn<>("Category");
        TableColumn<Activity, String> priceColumn = new TableColumn<>("Price");
        TableColumn<Activity, String> accessibilityColumn = new TableColumn<>("Accessibility");
        TableColumn<Activity, String> participantsColumn = new TableColumn<>("Participants");
        TableColumn<Activity, String> linkColumn = new TableColumn<>("Link");

// Set the cell value factory for each column
        activitiesColumn.setCellValueFactory(data -> data.getValue().getActivities());
        categoryColumn.setCellValueFactory(data -> data.getValue().getCategory());
        priceColumn.setCellValueFactory(data -> data.getValue().getPrice());
        accessibilityColumn.setCellValueFactory(data -> data.getValue().getAccessibility());
        participantsColumn.setCellValueFactory(data -> data.getValue().getParticipants());
        linkColumn.setCellValueFactory(data -> data.getValue().getLink());
//        activitiesColumn.setStyle(TABLECOLUMN_STYLES);
//        categoryColumn.setStyle(TABLECOLUMN_STYLES);
//        priceColumn.setStyle(TABLECOLUMN_STYLES);
//        accessibilityColumn.setStyle(TABLECOLUMN_STYLES);
//        linkColumn.setStyle(TABLECOLUMN_STYLES);
//        participantsColumn.setStyle(TABLECOLUMN_STYLES);

        setTableCellStyle(TABLECOLUMN_STYLES,activitiesColumn);
        setTableCellStyle(TABLECOLUMN_STYLES,categoryColumn);
        setTableCellStyle(TABLECOLUMN_STYLES,priceColumn);
        setTableCellStyle(TABLECOLUMN_STYLES,accessibilityColumn);
        setTableCellStyle(TABLECOLUMN_STYLES,linkColumn);
        setTableCellStyle(TABLECOLUMN_STYLES,participantsColumn);

        activitiesColumn.setPrefWidth(400);
        categoryColumn.setPrefWidth(100);
        linkColumn.setPrefWidth(250);
        myActivitiesTable.getColumns().addAll(activitiesColumn,categoryColumn,priceColumn,accessibilityColumn,participantsColumn,linkColumn);
        myActivitiesTable.setStyle("-fx-background-radius: 10;  -fx-padding: 7; ");
        myActivitiesTable.setPrefHeight(DATAWINDOW_HEIGHT);


        vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.setPrefSize(DATAWINDOW_WIDTH,DATAWINDOW_HEIGHT);
        vbox.relocate(VIEW_LEFTPADDING,80);
//        vbox.setStyle("-fx-background-radius: 10; -fx-background-color: #545E75; -fx-padding: 7;");
        vbox.setPadding(new Insets(2d,5d,2d,3d));
        vbox.getChildren().addAll(myActivitiesTable);
        vbox.setVisible(false);


        saveBtn= new Button("Save Activity!");
        saveBtn.setPrefSize(100, 20);
        saveBtn.relocate(VIEWWIDTH-VIEW_RIGHTPADDING-100,DATAWINDOW_HEIGHT+90);
        saveBtn.setStyle("-fx-base: #CF5C36; -fx-text-fill: #000000");
        saveBtn.setPadding(new Insets(3d,3d,4d,3d));

        delDataBtn= new Button("Delete Activity!");
        delDataBtn.setPrefSize(100, 20);
        delDataBtn.relocate(VIEWWIDTH-VIEW_RIGHTPADDING-100,DATAWINDOW_HEIGHT+90);
        delDataBtn.setStyle("-fx-base: #CF5C36; -fx-text-fill: #000000");
        delDataBtn.setPadding(new Insets(3d,3d,4d,3d));



        generateBtn = new Button("Let's do Something!!");
        generateBtn.setPrefSize(130,30);
        generateBtn.setPadding(new Insets(3d,1d,3d,1d));
        generateBtn.setStyle("-fx-font: 12 Georgia; -fx-border-color:#75B9BE;-fx-base: #8CD867; -fx-font-weight: 300; -fx-border-radius: 5; -fx-text-fill:#000000 ");
        generateBtn.relocate(VIEW_LEFTPADDING,40);

        generateWindowBtn= new Button(" Activity");
        Image createIcon= new Image("C:/Users/User/Documents/MyFiles/projects/ImmaBoredd/images/create.png");
        ImageView createImgView= new ImageView(createIcon);
        createImgView.setPreserveRatio(true);
        createImgView.setFitHeight(10);
        createImgView.setFitWidth(20);
        createImgView.setScaleX(2);
        createImgView.setScaleY(2);
        generateWindowBtn.setGraphic(createImgView);

        generateWindowBtn.setPrefSize(100,30);
        generateWindowBtn.setPadding(new Insets(3d,1d,4d,1d));
        generateWindowBtn.setStyle("-fx-font: 14 Georgia; -fx-font-weight: 300 ; -fx-base: #75B9BE; -fx-text-fill: #000000; -fx-border-color:#75B9BE; -fx-border-radius: 5 ");
        generateWindowBtn.relocate(VIEWWIDTH-VIEW_RIGHTPADDING-100-50-10,40);

        myActivitiesBtn= new Button();
        Image bookmarkIcon= new Image("C:/Users/User/Documents/MyFiles/projects/ImmaBoredd/images/bookmark.png");
        ImageView bookmarkImgView= new ImageView(bookmarkIcon);
        bookmarkImgView.setPreserveRatio(true);
        bookmarkImgView.setFitHeight(20);
        bookmarkImgView.setFitWidth(30);
        bookmarkImgView.setScaleX(2);
        bookmarkImgView.setScaleY(2);
        myActivitiesBtn.setGraphic(bookmarkImgView);
        myActivitiesBtn.setStyle("-fx-base: #545E75;");
        myActivitiesBtn.setPrefSize(20,30);
        myActivitiesBtn.relocate(VIEWWIDTH-VIEW_RIGHTPADDING-50,40);

        success= new Label();
        success.relocate(VIEW_LEFTPADDING, DATAWINDOW_HEIGHT+90);
        success.setStyle("-fx-text-fill: green");

        error= new Label();
        error.relocate(VIEW_LEFTPADDING, DATAWINDOW_HEIGHT+90);
        error.setStyle("-fx-text-fill: #CF5C36");
        saveBtn.setDisable(true);
        delDataBtn.setVisible(false);
        delDataBtn.setDisable(true);
        getChildren().addAll(generateBtn, rList, saveBtn, error, myActivitiesBtn, vbox, generateWindowBtn, success, delDataBtn);
        setPrefSize(VIEWWIDTH,VIEWHEIGHT);
    }

    public Button getGenerateButton(){
        return generateBtn;
    }
    public TableView getMyActivitiesTable(){
        return this.myActivitiesTable;
    }

    public ListView<String> getResultList(){
        return rList;
    }

    public Button getGenerateWindowBtn() {
        return generateWindowBtn;
    }

    public Button getMyActivitiesBtn(){
        return myActivitiesBtn;
    }

    public void setTableCellStyle(String style, TableColumn tableColumn){
        tableColumn.setCellFactory(column -> {
            TableCell<Activity, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.wrappingWidthProperty().bind(tableColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            cell.setPrefHeight(50);
//            cell.setStyle(style);
            cell.setAlignment(Pos.CENTER);
            cell.setPadding(new Insets(3d,1d,4d,1d));
            return cell;
        });
    }


    public Button getSaveButton(){
        return saveBtn;
    }

    public Button getDeleteBtn(){
        return delDataBtn;
    }

    public void updateGenerate(){
        ActivityGenerator generator= new ActivityGenerator();
        try {
            this.updateGenerateWindow();
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
            result.add("Price: " + Activity.convertDataToString((double)allData.get("price")));
            result.add("Accessibility: " + Activity.convertDataToString((double)allData.get("accessibility")));
            if(!allData.get("link").equals("")) {
                result.add("Check this out: " + allData.get("link"));
            }else{
              result.add("");
            };
            rList.setItems(FXCollections.observableArrayList(result));
            this.saveBtn.setDisable(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateSave() {
        try{
            this.error.setVisible(false);
            this.success.setVisible(false);
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
            int rows= postSQLReq(sqlCommand);
            if(rows>0) {
                System.out.println("ADDED ROW");
                this.success.setText("Successfully Saved!! ");
                this.success.setVisible(true);
                this.error.setVisible(false);
            };
        }catch (SQLIntegrityConstraintViolationException e){
            this.error.setText("You have it saved already! :))");
            this.error.setVisible(true);
            this.success.setVisible(false);
            System.out.println("DUPLICATE RECORD ADDED!!");
            e.printStackTrace();
        }
        catch(SQLException | ClassNotFoundException e){
            this.error.setText("ERROR!! Try Again later! :((");
            this.error.setVisible(true);
            this.success.setVisible(false);
            System.out.println("ERROR CONNECTING TO DB!!");
            e.printStackTrace();
    }}

    private int postSQLReq(String sqlCommand) throws SQLIntegrityConstraintViolationException,ClassNotFoundException ,SQLException{
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(constants.getDbUrl(), constants.getDbUsername(), constants.getDbPassword());
        Statement statement= connection.createStatement();
        int rows = statement.executeUpdate(sqlCommand);
        connection.close();
        return rows;
    }

    private ResultSet getSQLReq(String sqlCommand) throws SQLIntegrityConstraintViolationException,ClassNotFoundException ,SQLException{
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(constants.getDbUrl(), constants.getDbUsername(), constants.getDbPassword());
        Statement statement= connection.createStatement();
        ResultSet rows = statement.executeQuery(sqlCommand);
        connection.close();
        return rows;
    }
    
    public void updateGenerateWindow(){
        this.vbox.setVisible(false);
        this.generateBtn.setVisible(true);
        this.saveBtn.setVisible(true);
        this.delDataBtn.setVisible(false);
    };

    public void updateBookmarkWindow(){
        this.vbox.setVisible(true);
        this.saveBtn.setVisible(false);
        this.delDataBtn.setVisible(true);
        this.generateBtn.setVisible(false);
        String sqlCommand= "SELECT * FROM public.activities";
        try{
            ResultSet rows= this.getSQLReq(sqlCommand);
            ObservableList<Activity> data= FXCollections.observableArrayList();
            while(rows.next()){
                String activitiesData = (rows.getString("Activities"));
                String categoryData = (rows.getString("Category"));
                String priceData = (rows.getString("Price"));
                String accessibilityData = (rows.getString("Accessibility"));
                String participantsData = (rows.getString("Participants"));
                String linkData = (rows.getString("Link"));
                data.add(new Activity(activitiesData, categoryData, priceData, accessibilityData, participantsData, linkData));
            };
            myActivitiesTable.setItems(data);
            this.success.setVisible(false);
            this.error.setVisible(false);
        }
        catch(SQLException | ClassNotFoundException e){
            this.error.setText("ERROR!! Try Again later! :((");
            this.error.setVisible(true);
            this.success.setVisible(false);
            System.out.println("ERROR CONNECTING TO DB!!");
            e.printStackTrace();
        }


    }

    public void updateDeleteRow(){
        Object selectedItem = this.myActivitiesTable.getSelectionModel().getSelectedItem();
        Activity selectedRow= (Activity) selectedItem;
        String sqlCommand= "Delete FROM activities WHERE \"Activities\"= '"+ selectedRow.getActivities().getValue()+ "'";
        try{
            int rows= this.postSQLReq(sqlCommand);
            if(rows>0){
                this.success.setText("DELETED ROW!!");
                this.success.setVisible(true);
                this.updateBookmarkWindow();
            }
        }
        catch(SQLException | ClassNotFoundException e){
            this.error.setText("ERROR!! Try Again later! :((");
            this.error.setVisible(true);
            this.success.setVisible(false);
            System.out.println("ERROR CONNECTING TO DB!!");
            e.printStackTrace();
        }

    };


}
