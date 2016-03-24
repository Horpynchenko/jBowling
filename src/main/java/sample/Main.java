package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Dmytro on 23.03.2016.
 */

//// TODO: 24.03.2016 create different tests
//// TODO: 24.03.2016 check max possible score in second throw in Frame
//// TODO: 23.03.2016 create selection possibility: 'Continue' or 'End' of the game at the end of current game.
//// TODO: 23.03.2016 implement javaFX gui

public class Main {

    public static void main(String[]args){
        System.out.println("Start the Game!");
        MainExecutor.startInteractiveGame();
    }

}



//public class Main extends Application {
//
//    public static void main(String[] args) {
//        System.out.println("Start the Game!");
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        Pane root = new Pane();
//        Image image = new Image(getClass().getResourceAsStream("bowling.jpg"));
//        ImageView img = new ImageView(image);
//        img.setFitHeight(800);
//        img.setFitWidth(1280);
//        root.getChildren().add(img);
//
//        MenuItem newGame = new MenuItem("NEW GAME");
//        MenuItem options = new MenuItem("SETTINGS");
//        MenuItem exitGame = new MenuItem("EXIT");
//        SubMenu mainMenu = new SubMenu(
//                newGame,options,exitGame
//        );
//        MenuItem sound = new MenuItem("SOUND");
//        MenuItem video = new MenuItem("VIDEO");
//        MenuItem keys = new MenuItem("CONTROL");
//        MenuItem optionsBack = new MenuItem("BACK");
//        SubMenu optionsMenu = new SubMenu(
//                sound,video,keys,optionsBack
//        );
//
//        MenuItem NG1 = new MenuItem("SINGLE PLAY");
//        MenuItem NG2 = new MenuItem("CHAMPIONSHIP");
//        MenuItem newGameBack = new MenuItem("BACK");
//        SubMenu newGameMenu = new SubMenu(
//                NG1,NG2,newGameBack
//        );
//        MenuBox menuBox = new MenuBox(mainMenu);
//
//        MenuItem startSingleGame = new MenuItem("START SINGLE GAME!");
//        MenuItem singleGameBack = new MenuItem("BACK");
//        SubMenu singlePlay = new SubMenu(
//                startSingleGame,singleGameBack
//        );
//        MenuBox menuSinglePlay = new MenuBox(singlePlay);
//
//        MenuItem startGame = new MenuItem("START CHAMPIONSHIP!");
//        MenuItem championshipBack = new MenuItem("BACK");
//        SubMenu championship = new SubMenu(
//                startGame,singleGameBack
//        );
//        MenuBox menuChampionship = new MenuBox(singlePlay);
//
//        newGame.setOnMouseClicked(event->menuBox.setSubMenu(newGameMenu));
//        menuSinglePlay.setOnMouseClicked(event->menuBox.setSubMenu(singlePlay));
//        menuChampionship.setOnMouseClicked(event->menuBox.setSubMenu(championship));
//        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
//        exitGame.setOnMouseClicked(event-> System.exit(0));
//        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
//        newGameBack.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
//        singleGameBack.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
//        championshipBack.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
//        root.getChildren().addAll(menuBox);
//
//        Scene scene = new Scene(root,1280,800);
//        scene.setOnKeyPressed(event -> {
//            if ( event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER ) {
//                FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
//                if (!menuBox.isVisible()) {
//                    ft.setFromValue(0);
//                    ft.setToValue(1);
//                    ft.play();
//                    menuBox.setVisible(true);
//                }
//                else{
//                    ft.setFromValue(1);
//                    ft.setToValue(0);
//                    ft.setOnFinished(evt ->   menuBox.setVisible(false));
//                    ft.play();
//
//                }
//            }
//        });
//        primaryStage.setTitle("Pause");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private static class MenuItem extends StackPane{
//
//        public  MenuItem(String name){
//            Rectangle bg = new Rectangle(200,20,Color.WHITE);
//            bg.setOpacity(0.5);
//
//            Text text = new Text(name);
//            text.setFill(Color.BROWN);
//            text.setFont(Font.font("Arial",FontWeight.BOLD,14));
//
//            setAlignment(Pos.CENTER);
//            getChildren().addAll(bg,text);
//            FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
//            setOnMouseEntered(event -> {
//                st.setFromValue(Color.DARKGRAY);
//                st.setToValue(Color.DARKGOLDENROD);
//                st.setCycleCount(Animation.INDEFINITE);
//                st.setAutoReverse(true);
//                st.play();
//            });
//            setOnMouseExited(event -> {
//                st.stop();
//                bg.setFill(Color.WHITE);
//            });
//        }
//    }
//
//    private static class MenuBox extends Pane{
//        static SubMenu subMenu;
//        public MenuBox(SubMenu subMenu){
//            MenuBox.subMenu = subMenu;
//
//            setVisible(false);
//            Rectangle bg = new Rectangle(1280,800,Color.LIGHTBLUE);
//            bg.setOpacity(0.2);
//            getChildren().addAll(bg, subMenu);
//        }
//        public void setSubMenu(SubMenu subMenu){
//            getChildren().remove(MenuBox.subMenu);
//            MenuBox.subMenu = subMenu;
//            getChildren().add(MenuBox.subMenu);
//        }
//    }
//
//    private static class SubMenu extends VBox{
//        public SubMenu(MenuItem...items){
//            setSpacing(15);
//            setTranslateY(100);
//            setTranslateX(50);
//            for(MenuItem item : items){
//                getChildren().addAll(item);
//            }
//        }
//    }
//}
