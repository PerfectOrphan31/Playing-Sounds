package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        run(primaryStage);
    }

    private void run(Stage primaryStage) throws Exception{
        ArrayList<String> choices = new ArrayList<>();
        choices.add("IR");
        choices.add("chimes");
        choices.add("Congrats!");
        primaryStage.setTitle("Playing With Sounds");
        ChoiceDialog<String> diag = new ChoiceDialog<>("IR", choices);
        diag.setTitle("Play Sound");
        diag.setHeaderText("Look! You get a choice!");
        diag.setContentText("Choose the sound you want!");
        Optional<String> result = diag.showAndWait();
        result.ifPresent(choice -> playSound(result.get()));
        Button reset = new Button("Reset");
        reset.setPrefWidth(100);
        reset.setPrefHeight(50);
        reset.relocate(100,175);
        reset.setDefaultButton(true);
        reset.setScaleShape(true);
        Button quit = new Button("Quit");
        quit.setPrefHeight(50);
        quit.setPrefWidth(100);
        quit.setCancelButton(true);
        quit.setScaleShape(true);
        quit.relocate(400,175);
        reset.setOnAction(event->{
                try {
                    primaryStage.hide();
                    reset(primaryStage);
                }catch(Exception e){
                    e.printStackTrace();
                }
        });
        quit.setOnAction(event-> {primaryStage.close();});
        Group buttons = new Group();
        buttons.getChildren().addAll(reset, quit);
        primaryStage.setScene(new Scene(buttons, 600, 400));
        primaryStage.show();
    }

    private synchronized void playSound(String file){
        try {
            String FILETYPE = ".wav", PATH="Sounds/";
            switch(file) {
                case "IR":
                    Clip clip = AudioSystem.getClip();
                    InputStream is = getClass().getResourceAsStream(PATH + "ir_begin" + FILETYPE);
                    AudioInputStream as = AudioSystem.getAudioInputStream(is);
                    clip.open(as);
                    clip.start();
                    while (clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
                    }
                    Clip clip1 = AudioSystem.getClip();
                    is = getClass().getResourceAsStream(PATH + "ir_inter" + FILETYPE);
                    as = AudioSystem.getAudioInputStream(is);
                    clip1.open(as);
                    clip1.start();
                    while (clip1.getMicrosecondLength() != clip1.getMicrosecondPosition()) {
                    }
                    Clip clip2 = AudioSystem.getClip();
                    is = getClass().getResourceAsStream(PATH + "ir_end" + FILETYPE);
                    as = AudioSystem.getAudioInputStream(is);
                    clip2.open(as);
                    clip2.start();
                    while (clip2.getMicrosecondLength() != clip2.getMicrosecondPosition()) {
                    }
                    break;
                case "Congrats!":
                    clip = AudioSystem.getClip();
                    is = getClass().getResourceAsStream(PATH + "tada" + FILETYPE);
                    as = AudioSystem.getAudioInputStream(is);
                    clip.open(as);
                    clip.start();
                    while (clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
                    }
                    break;
                default:
                    clip = AudioSystem.getClip();
                    is = getClass().getResourceAsStream(PATH + file + FILETYPE);
                    as = AudioSystem.getAudioInputStream(is);
                    clip.open(as);
                    clip.start();
                    while (clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void reset(Stage stage) throws Exception{
        run(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
