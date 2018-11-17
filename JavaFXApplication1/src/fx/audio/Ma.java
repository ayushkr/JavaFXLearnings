package fx.audio;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ma extends Application {
MediaPlayer mediaPlayer ;
    @Override
    public void start(Stage primaryStage) throws Exception{
        WebView myWebView = new WebView();
        WebEngine engine = myWebView.getEngine();
        engine.loadContent("hai");
        //dirty code xD
        
        

        Button btn = new Button("Load Youtube");
        btn.setOnAction((ActionEvent event) -> engine.load("file:///home/ayush/NetBeansProjects/WebViewSample/userFiles/video1.mp4"));

        Button btn2 = new Button("Mute");
        btn2.setOnAction((ActionEvent event) -> engine.executeScript(getJSAudioVideo(true)));

        Button btn3 = new Button("Unmute");
        btn3.setOnAction((ActionEvent event) -> {
            mediaPlayer.stop();
            Duration currentTime = mediaPlayer.getCurrentTime();
        mediaPlayer.setStartTime(currentTime.subtract(Duration.seconds(1)));
        mediaPlayer.play();
        });

        Button btn4 = new Button("+");
        btn4.setOnAction((ActionEvent event) -> {
        mediaPlayer.stop();
        mediaPlayer.setStartTime(Duration.seconds(30));
mediaPlayer.play();
        });

        Button btn5 = new Button("-");
        btn5.setOnAction((ActionEvent event) -> {
        String musicFile = "userFiles/a.wav";     // For example

Media sound = new Media(new File(musicFile).toURI().toString());
 mediaPlayer = new MediaPlayer(sound);

mediaPlayer.setStartTime(Duration.seconds(10));
mediaPlayer.play();
        });

        VBox root = new VBox();
        root.getChildren().addAll(myWebView, btn, btn2, btn3, btn4, btn5);
        
   
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mute a WebView in JavaFX, is it possible?");

        primaryStage.show();
    }

      public String getJSAudioVideo(boolean mute){
        return "var videos = document.querySelectorAll('video'),\n" +
                "    audios = document.querySelectorAll('listen');\n" +
                "    [].forEach.call(videos, function(video) { video.muted = "+String.valueOf(mute)+"; });\n" +
                "    [].forEach.call(audios, function(audio) { audio.muted = "+String.valueOf(mute)+"; });";
    }
   
      
         public String getJSSoundVolume(double volume){
        //max 1.0, min 0.0 Default: 1.0
        double _volume = (volume > 1.0 || volume < 0.0) ? 1.0 : volume;

        return "var videos = document.querySelectorAll('video'),\n" +
                "    audios = document.querySelectorAll('listen');\n" +
                "    [].forEach.call(videos, function(video) { video.volume = "+String.valueOf(_volume)+"; });\n" +
                "    [].forEach.call(audios, function(audio) { audio.volume = "+String.valueOf(_volume)+"; });";

    }


    public static void main(String[] args) {
        launch(args);
    }
}