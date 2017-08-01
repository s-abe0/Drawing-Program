
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class Draw extends Application 
{
    Color currentColor = Color.BLACK;
    static Pane pane = new Pane();
    Circle circle = new Circle(50, 50, 5, Color.BLACK);
    
    @Override
    public void start(Stage primaryStage) 
    {
        Menu colorsMenu = new Menu("Colors");
        Menu editMenu = new Menu("Edit");
        MenuItem black = new MenuItem("Black");
        MenuItem red = new MenuItem("Red");
        MenuItem blue = new MenuItem("Blue");
        MenuItem clearscreen = new MenuItem("Clear Screen");
        MenuBar menu = new MenuBar();
        colorsMenu.getItems().addAll(black, red, blue);
        editMenu.getItems().addAll(clearscreen);
        menu.getMenus().addAll(colorsMenu, editMenu);
        
        pane.getChildren().addAll(circle, menu);

        black.setOnAction(e -> 
	{
           currentColor = Color.BLACK;
           circle.setFill(currentColor);
        });
        
        blue.setOnAction(e -> 
	{
           currentColor = Color.BLUE;
           circle.setFill(currentColor);
        });
        
        red.setOnAction(e -> 
	{
           currentColor = Color.RED;
           circle.setFill(currentColor);
        });
        
        clearscreen.setOnAction(e ->
        {
            clearScreen();
        });
        
        circle.setOnKeyPressed(e -> 
	{
            switch (e.getCode())
            {
                case DOWN:
                    circle.setCenterY(circle.getCenterY() + 5);
                    if (e.isShiftDown()) 
                    {
                        createLine().setEndY(circle.getCenterY());
                        createLine().setEndY(circle.getCenterY() - 5);
                    }
                    break;
                case UP:
                    circle.setCenterY(circle.getCenterY() - 5);
                    if (e.isShiftDown()) 
                    {
                        createLine().setEndY(circle.getCenterY());
                        createLine().setEndY(circle.getCenterY() + 5);
                    }
                    break;
                case LEFT:
                    circle.setCenterX(circle.getCenterX() - 5);
                    if (e.isShiftDown()) 
                    {
                        createLine().setEndX(circle.getCenterX());
                        createLine().setEndX(circle.getCenterX() + 5);
                    }
                    break;
                case RIGHT:
                    circle.setCenterX(circle.getCenterX() + 5);
                    if (e.isShiftDown()) 
                    {
                        createLine().setEndX(circle.getCenterX());
                        createLine().setEndX(circle.getCenterX() - 5);
                    }
                    break;
            }
        });

        primaryStage.setTitle("Draw");
        primaryStage.setScene(new Scene(pane, 600, 500));
        primaryStage.show();

        circle.requestFocus();
    }

    public static void main(String args[])
    {
        Application.launch();
    }

    private Line createLine()
    {
        Line line = new Line(circle.getCenterX(), circle.getCenterY(),
                circle.getCenterX(), circle.getCenterY());
        line.setStrokeWidth(3);
        line.setStroke(currentColor);
        pane.getChildren().add(line);

        return line;
    }
    
    private static void clearScreen()
    {
        
        pane.getChildren().removeIf(n -> n instanceof Line);
    }
}
