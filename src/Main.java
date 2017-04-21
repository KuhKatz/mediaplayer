package sample;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Main extends Application {

//    MPStyle style = new MPStyle();

    private Label time = new Label();
    MediaView mediaView;
    MediaPlayer mediaPlayer;
    private Duration duration;
    Media media;
    Pane root;
    File file;
    String path;
    Scene scene;
    Slider timeSlider;
    private Slider volumeSlider;
    private FlowPane toolBar;
    MenuBar menuBar;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        root = new Pane();
        //root.setBottom(toolBar);
        path = "C:\\Users\\Neal\\Videos\\Stage Dolls - Into The Fire.mp3";
        media = new Media(new File(path).toURI().toString());
        /*media = new Media ((this.getClass().getResource
        ("David Lindgren - We Are Your Tomorrow (Official Audio).mp3").toExternalForm()));*/                            //Adds a music file from the sample folder to the Player
        mediaPlayer = new MediaPlayer(media);                                                                           //Setting up a new MediaPlayer
        mediaPlayer.setAutoPlay(false);                                                                                 //Set AutoPlay to false so the File is not playing when the Project starts
        mediaView = new MediaView(mediaPlayer);                                                                         //Adds MediaView so the MediaPlayer is able to play Media files
        mediaView.setFitHeight(700);
        mediaView.setFitWidth(1200);


        toolBar = new FlowPane();
        toolBar.setAlignment(Pos.CENTER);
        // toolBar.setColumnHalignment(HPos.LEFT);
        toolBar.setPadding(new Insets(0, 0, 0, 5));                                                                     //Sets up the bytes between the Toolbar and the Stage (Top, Left, Bottom, Right)
        toolBar.relocate(0, 700);                                                                                       //Relocates the Toolbar
        toolBar.alignmentProperty().isBound();                                                                          //Sets the Toolbar as bound
        toolBar.setPrefWidth(1200);
        toolBar.setStyle("-fx-background-color: WHITE");                                                                //Sets the background color of the toolbar

        Image filesButtonImage = new Image(this.getClass().getResource("pictures\\files.png").toExternalForm());                  //Adds an Image for FilesButton
        Button filesButton = new Button();                                                                              //FilesButton is created as a button
        filesButton.setGraphic(new ImageView(filesButtonImage));                                                        //Adds the Image to the Button
        filesButton.setStyle("-fx-background-color: White");                                                            //Sets the background color

        Image firstButtonImage = new Image(this.getClass().getResource("pictures\\first.png").toExternalForm());                  //Adds an Image for FirstButton
        Button firstButton = new Button();                                                                              //FirstButton is created as a button
        firstButton.setGraphic(new ImageView(firstButtonImage));                                                        //Adds the Image to the Button
        firstButton.setStyle("-fx-background-color: White");                                                            //Sets the background color

        Image backButtonImage = new Image(this.getClass().getResource("pictures\\back.png").toExternalForm());                    //Adds an Image for BackButton
        Button backButton = new Button();                                                                               //BackButton is created as a button
        backButton.setGraphic(new ImageView(backButtonImage));                                                          //Adds the Image to the Button
        backButton.setStyle("-fx-background-color: White");                                                             //Sets the background color

        Image playButtonImage = new Image(this.getClass().getResource("pictures\\play.png").toExternalForm());                    //Adds an Image for PlayButton
        Button playButton = new Button();                                                                               //PlayButton is created as a button
        playButton.setGraphic(new ImageView(playButtonImage));                                                          //Adds the Image to the Button
        playButton.setStyle("-fx-background-color: White");                                                             //Sets the background color

        Image pausedButtonImage = new Image(this.getClass().getResource("pictures\\Pause.png").toExternalForm());                 //Adds an Image for PausedButton
        Button pauseButton = new Button();                                                                              //PauseButton is created as a button
        pauseButton.setGraphic(new ImageView(pausedButtonImage));                                                       //Adds the Image to the Button
        pauseButton.setStyle("-fx-background-color: White");                                                            //Sets the background color

        Image forwardButtonImage = new Image(this.getClass().getResource("pictures\\forward.png").toExternalForm());              //Adds an Image for ForwardButton
        Button forwardButton = new Button();                                                                            //ForwardButton is created as a button
        forwardButton.setGraphic(new ImageView(forwardButtonImage));                                                    //Adds the Image to the Button
        forwardButton.setStyle("-fx-background-color: White");                                                          //Sets the background color

        Image lastButtonImage = new Image(this.getClass().getResource("pictures\\last.png").toExternalForm());                    //Adds an Image for LastButton
        Button lastButton = new Button();                                                                               //LastButton is created as a button
        lastButton.setGraphic(new ImageView(lastButtonImage));                                                          //Adds the Image to the Button
        lastButton.setStyle("-fx-background-color: White");                                                             //Sets the background color

        Image reloadButtonImage = new Image(this.getClass().getResource("pictures\\reload.png").toExternalForm());                //Adds an Image for ReloadButton
        Button reloadButton = new Button();                                                                             //ReloadButton is created as a button
        reloadButton.setGraphic(new ImageView(reloadButtonImage));                                                      //Adds the Image to the Button
        reloadButton.setStyle("-fx-background-color: White");                                                           //Sets the background color

        Image fullScreenButtonImage = new Image(this.getClass().getResource("pictures\\fullscreen.png").toExternalForm());        //Adds an Image for FullScreenButton
        Button fullScreenButton = new Button();                                                                         //FullScreenButton is created as a button
        fullScreenButton.setGraphic(new ImageView(fullScreenButtonImage));                                              //Adds the Image to the Button
        fullScreenButton.setStyle("-fx-background-color: White");

        filesButton.setOnAction((ActionEvent e) -> {                                                                    //An ActionEvent is set up for the filesButton
            FileChooser fc = new FileChooser();                                                                         //Sets up a new File Chooser
            fc.setInitialDirectory(new File("C:\\Users\\Neal\\Videos"));                                                //Sets the initial directory for Files you'd like to open
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music Files", "*.mp*"));                      //Sets the Filter to Music Files with *.mp* which means all music files with this ending will be shown
            file = fc.showOpenDialog(primaryStage);

            if (file != null) {
                path = file.getAbsolutePath();
                path = path.replace("\\", "/");
                media = new Media(new File(path).toURI().toString());
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);

                mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {                                                  //Makes the Time label actually runs when audio starts to play
                    updateValues();
                });
                mediaPlayer.setOnReady(() -> {                                                                                      //Duration appears on screen
                            duration = mediaPlayer.getMedia().getDuration();
                            updateValues();
                        });
                mediaPlayer.setAutoPlay(true);
        }
        });

        filesButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                       //An EventHandler for Mouse_Entered is set up for the filesButton
            filesButton.setStyle("-fx-body-color: White");                                                              //This is required to make it visible when the Mouse enters the Button
        });
        filesButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                        //An EventHandler for Mouse_Exited is set up for the filesButton
            filesButton.setStyle("-fx-background-color: White");                                                        //This is required to set it back to normal when the Mouse exits the Button
        });

        firstButton.setOnAction((ActionEvent e) -> {                                                                    //An ActionEvent is set up for the filesButton
            mediaPlayer.seek(mediaPlayer.getStartTime());                                                               //The MediaPlayer goes back to the StartTime
            mediaPlayer.stop();                                                                                         //MediaPlayer stops playing
        });
        firstButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                       //An EventHandler for Mouse_Entered is set up for the firstButton
            firstButton.setStyle("-fx-background-color: White");
            firstButton.setStyle("-fx-body-color: White");
        });
        firstButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                        //An EventHandler for Mouse_Entered is set up for the firstButton
            firstButton.setStyle("-fx-background-color: White");
        });

        backButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
        });
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                        //An EventHandler for Mouse_Entered is set up for the backButton
            backButton.setStyle("-fx-background-color: White");
            backButton.setStyle("-fx-body-color: White");
        });
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                         //An EventHandler for Mouse_Entered is set up for the backButton
            backButton.setStyle("-fx-background-color: White");
        });

        playButton.setOnAction((ActionEvent e) -> {                                                                     //An EventHandler for Mouse_Entered is set up for the playButton
            mediaPlayer.play();
        });

        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                        //An EventHandler for Mouse_Entered is set up for the playButton
            playButton.setStyle("-fx-background-color: White");
            playButton.setStyle("-fx-body-color: White");
        });
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                         //An EventHandler for Mouse_Entered is set up for the playButton
            playButton.setStyle("-fx-background-color: White");
        });

        pauseButton.setOnAction((ActionEvent e) -> {                                                                    //An EventHandler for Mouse_Entered is set up for the pauseButton
            mediaPlayer.pause();
        });

        pauseButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                       //An EventHandler for Mouse_Entered is set up for the pauseButton
            pauseButton.setStyle("-fx-background-color: White");
            pauseButton.setStyle("-fx-body-color: White");
        });
        pauseButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                        //An EventHandler for Mouse_Entered is set up for the pauseButton
            pauseButton.setStyle("-fx-background-color: White");
        });

        forwardButton.setOnAction((ActionEvent e) -> {                                                                  //An EventHandler for Mouse_Entered is set up for the forwardButton
            mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
        });

        forwardButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                     //An EventHandler for Mouse_Entered is set up for the forwardButton
            forwardButton.setStyle("-fx-background-color: White");
            forwardButton.setStyle("-fx-body-color: White");
        });
        forwardButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                      //An EventHandler for Mouse_Entered is set up for the forwardButton
            forwardButton.setStyle("-fx-background-color: White");
        });

        lastButton.setOnAction((ActionEvent e) -> {                                                                     //An EventHandler for Mouse_Entered is set up for the lastButton
            mediaPlayer.seek(mediaPlayer.getTotalDuration());
            mediaPlayer.stop();
        });

        lastButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                        //An EventHandler for Mouse_Entered is set up for the lastButton
            lastButton.setStyle("-fx-background-color: White");
            lastButton.setStyle("-fx-body-color: White");
        });
        lastButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                         //An EventHandler for Mouse_Entered is set up for the lastButton
            lastButton.setStyle("-fx-background-color: White");
        });

        reloadButton.setOnAction((ActionEvent e) -> {                                                                   //An EventHandler for Mouse_Entered is set up for the reloadButton
            mediaPlayer.seek(mediaPlayer.getStartTime());
        });

        reloadButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                      //An EventHandler for Mouse_Entered is set up for the reloadButton
            reloadButton.setStyle("-fx-background-color: White");
            reloadButton.setStyle("-fx-body-color: White");
        });
        reloadButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                       //An EventHandler for Mouse_Entered is set up for the reloadButton
            reloadButton.setStyle("-fx-background-color: White");
        });

        fullScreenButton.setOnAction((ActionEvent e) -> {                                                               //An EventHandler for Mouse_Entered is set up for the fullScreenButton
            if (primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(false);
                toolBar.relocate(0, 700);
                toolBar.setPadding(new Insets(0, 0, 0, 5));
                toolBar.setPrefWidth(1200);
                timeSlider.setMinWidth(1150);
                timeSlider.setMaxWidth(Double.MAX_VALUE);
                mediaView.setFitHeight(700);
                mediaView.setFitWidth(1200);
            } else {
                primaryStage.setFullScreen(true);
                mediaView.setFitWidth(1600);
                toolBar.relocate(0,975);
                toolBar.setPrefWidth(2200);
                timeSlider.setMinWidth(2150);
                timeSlider.setMaxWidth(Double.MAX_VALUE);
                mediaView.setFitHeight(2000);
                mediaView.setFitWidth(2000);
            }
        });

        fullScreenButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {                                      //An EventHandler for Mouse_Entered is set up for the fullScreenButton
            fullScreenButton.setStyle("-fx-background-color: White");
            fullScreenButton.setStyle("-fx-body-color: White");
        });
        fullScreenButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {                                       //An EventHandler for Mouse_Entered is set up for the fullScreenButton
            fullScreenButton.setStyle("-fx-background-color: White");
        });

        time = new Label();
        time.setTextFill(Color.BLUE);
        time.setPrefWidth(150);
        time.setPadding(new Insets(25));


        mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {                                                  //Makes the Time label actually runs when audio starts to play
            updateValues();
        });

        mediaPlayer.setOnReady(() -> {                                                                                      //Duration appears on screen
            duration = mediaPlayer.getMedia().getDuration();
            updateValues();
        });

       /* DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(shadow);
                    }
                });*/

        Label timeLabel = new Label("Time: ");
        timeLabel.setTextFill(Color.BLUE);
        timeSlider = new Slider();
        timeSlider.setMinWidth(1150);
        timeSlider.setMaxWidth(Double.MAX_VALUE);

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });

        Label volumeLabel = new Label("Vol: ");
        volumeLabel.setTextFill(Color.BLUE);

        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(250);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(0);
        volumeSlider.setShowTickLabels(true);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });

       root.setId("pane");
       root.getChildren().addAll(mediaView, toolBar);
        toolBar.getChildren().addAll(timeLabel, timeSlider, filesButton, firstButton, backButton, playButton, pauseButton,
                forwardButton, lastButton, reloadButton, fullScreenButton, time, volumeLabel, volumeSlider);
        primaryStage.setTitle("A truly cool Media Player that will completely change your mind");
        scene = new Scene(root, 1200, 800);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());                                       //Stylesheet with information on the Background is added
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    protected void updateValues() {
        if (time != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    time.setText(formatTime.formatTime(currentTime, duration));

                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }

                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int)Math.round(mediaPlayer.getVolume()
                                * 100));
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void stop() throws Exception {
    }

}
