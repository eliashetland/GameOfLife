package gameoflife;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gameoflife.model.Main;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable{

    @FXML 
    private Button play;

    @FXML
    private Canvas canvas;

    @FXML
    private Slider livePercentSlider, scaleSlider, speedSlider;
    



    private GraphicsContext gc;

    private int width = 800;
    private int height = 600;
    private int scale = 5;
    private int gridCols = width / scale;
    private int gridRows = height / scale;
    private int speed = 4;
    
    private Main game;

    private AnimationTimer gameloop = new AnimationTimer() {
        int frame = 0;
        @Override
        public void handle(long now) {
            if(frame>4-speed){
                drawGrid();
                game.nextGen();
                
                frame =0;
            }
            frame ++;
        }
        
    };

    @FXML
    private void play() throws IOException {
        if (play.getText().equals("Play")) {
            gameloop.start();
            play.setText("Pause");
        }
        else if (play.getText().equals("Pause")) {
            gameloop.stop();
            play.setText("Play");
            
        }

        
    }

    @FXML
    private void restart(){
        gameloop.stop();
        int percentage = (int)livePercentSlider.getValue();
        scale = (int)scaleSlider.getValue() +1;
        gridCols = width / scale;
        gridRows = height / scale;
        game = new Main(gridCols, gridRows);

        game.populateRandom(percentage);
        gameloop.start();
        play.setText("Pause");
    }


    @FXML
    private void changeSpeed(){
        speed = (int)speedSlider.getValue();        
    }

    private void drawGrid(){
        int[][] grid = game.getGrid();
        
        gc.clearRect(0, 0, width, height);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {

                int state = grid[y][x];
                gc.setFill(state==1?Color.BLACK:Color.GREEN);
                gc.fillRect(x*scale, y*scale, scale, scale);
            }
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();


        game = new Main(gridCols, gridRows);
        game.populateRandom(10);

    }
}
