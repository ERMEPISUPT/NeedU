package negocio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

public class clsNWebcam {
    private MediaPlayer mediaPlayer;

    public void mostrarCamara() {
        StackPane root = new StackPane();

        initializeMediaPlayer();

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(640);
        mediaView.setFitHeight(480);

        root.getChildren().add(mediaView);

        Scene scene = new Scene(root, 640, 480, Color.BLACK);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Webcam Viewer");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                mediaPlayer.stop();
            }
        });

        primaryStage.show();
    }

    private void initializeMediaPlayer() {
        String mediaSource = "http://your-camera-stream-url"; 
        Media media = new Media(mediaSource);
        mediaPlayer = new MediaPlayer(media);



        mediaPlayer.play();
    }
}
