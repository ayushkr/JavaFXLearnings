package fx.youtube;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Ma extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        WebView myWebView = new WebView();
        WebEngine engine = myWebView.getEngine();
        //dirty code xD

        Button btn = new Button("Load Youtube");
        btn.setOnAction((ActionEvent event) -> engine.load("https://www.youtube.com/watch?v=tYRYvJK-dDI"));

        Button btn2 = new Button("Mute");
        btn2.setOnAction((ActionEvent event) -> engine.executeScript(getJSAudioVideo(true)));

        Button btn3 = new Button("Unmute");
        btn3.setOnAction((ActionEvent event) -> engine.executeScript(getJSAudioVideo(false)));

        Button btn4 = new Button("+");
        btn4.setOnAction((ActionEvent event) -> engine.executeScript(getJSSoundVolume(0.9)));

        Button btn5 = new Button("-");
        btn5.setOnAction((ActionEvent event) -> engine.executeScript(getJSSoundVolume(0.1)));

        VBox root = new VBox();
        root.getChildren().addAll(myWebView, btn, btn2, btn3, btn4, btn5);
        
   
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mute a WebView in JavaFX, is it possible?");

        primaryStage.show();
    }

    /**
     * @param mute
     * @return JS code for mute/unmute
     */
    public String getJSAudioVideo(boolean mute){
        return "var videos = document.querySelectorAll('video'),\n" +
                "    audios = document.querySelectorAll('listen');\n" +
                "    [].forEach.call(videos, function(video) { video.muted = "+String.valueOf(mute)+"; });\n" +
                "    [].forEach.call(audios, function(audio) { audio.muted = "+String.valueOf(mute)+"; });";
    }

    /**
     * @param volume
     * @return JS code for setting volume sound
     */
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