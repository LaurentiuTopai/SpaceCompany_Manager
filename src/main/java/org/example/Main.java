package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Task1 task1 = new Task1();
        task1.citire1();
        task1.citire2();
        task1.citire3();

        String css = getClass().getResource("/style.css").toExternalForm();

        Label l1 = new Label("Planete: Mercur(0), Venus(1), Earth(2), Mars(3), Jupiter(4), Saturn(5), Uranus(6), Neptune(7), Pluto(8)");
        l1.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-text-fill: #4CAF50; " +
                        "-fx-padding: 10px; " +
                        "-fx-background-color: #f4f4f4; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #4CAF50; " +
                        "-fx-border-width: 2px;"
        );
        HBox infoPlanete = new HBox(10, l1);

        /** TASK1*/
        TextArea showinfo1 = new TextArea();
        Button showText1 = new Button("Task1_Show");
        showText1.setOnAction(e -> {
            try {
                String result = task1.afisare1();
                showinfo1.setText(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        HBox box1 = new HBox(2,showText1, showinfo1);

        TextArea showinfo2 = new TextArea();
        Button showText2 = new Button("Task2_Show");
        showText2.setOnAction(e -> {
            try {
                String result = task1.afisare2();
                showinfo2.setText(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        HBox box2 = new HBox(2,showText2, showinfo2);

        /**Task3**/
        TextArea showinfo3 = new TextArea();
        Button showText3 = new Button("Task3_Show");
        showText3.setOnAction(e -> {
            try {
                String result = task1.afisare3();
                showinfo3.setText(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox box3 = new HBox(2,showText3, showinfo3);

        /**Task4*/
        Label choose4=new Label();
        choose4.setText("AlegePlaneta");
        Label setDuration4=new Label();
        setDuration4.setText("SetDuration");
        Label degrees4=new Label();
        degrees4.setText("Grade");
        TextField choosePlaneteText = new TextField();
        TextField setDuration = new TextField();
        Button calculate4=new Button("Calculate");
        TextField show4 = new TextField();
        calculate4.setOnAction(e -> {
            try{
                int result = Integer.parseInt(choosePlaneteText.getText());
                int days = Integer.parseInt(setDuration.getText());
                double raspunss = task1.angular(task1.getPeriod(),result,days);
                String formattedAnswer=String.format("%.2f",raspunss);
                show4.setText(String.valueOf(formattedAnswer+" Grade"));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        VBox for4_1=new VBox(choose4,choosePlaneteText);
        VBox for4_2=new VBox(setDuration4,setDuration);
        VBox for4_3=new VBox(degrees4,show4);
        HBox final4=new HBox(calculate4,for4_1,for4_2,for4_3);

        /**Task5*/

        Label planetName1 = new Label("Planeta:1");
        Label planetName2 = new Label("Planeta:2");
        Button calculate5=new Button("Calculate5");
        TextField planet1 = new TextField();
        TextField planet2 = new TextField();
        VBox parter1 = new VBox(planetName1,planet1);
        VBox parter2 = new VBox(planetName2,planet2);
        TextArea planetText = new TextArea();
        HBox final5 = new HBox(calculate5,parter1,parter2,planetText);
        calculate5.setOnAction(e -> {
            try{
                int pl1=Integer.parseInt(planet1.getText());
                int pl2=Integer.parseInt(planet2.getText());
                double[] raspuns = task1.findOptimalTransferWindow(pl1,pl2);
                if (raspuns == null) {
                    planetText.setText("Nu exista o fereastra optima de transfer disponibila.");
                } else {
                    String result = String.format("Distanta: %.3f AU\nZiua optima: %d", raspuns[0], (int) raspuns[1]);
                    planetText.setText(result);
                }
            }catch(Exception ex){

            }
        });


        VBox vboxfinal = new VBox( infoPlanete,box1,box2,box3,final4,final5);


        StackPane root = new StackPane();
        root.getChildren().add(vboxfinal);

        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setTitle("Informatii despre Planete");
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add(css);
        primaryStage.show();
    }


}