import uk.ac.leedsbeckett.oop.OOPGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainClass extends OOPGraphics {

    public static void main(String[] args) {
        new MainClass();
        new GraphicSystem();
    }

    public MainClass() {
        JFrame mainFrame = new JFrame("BUGRA HAN CETINKAYA");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(this);
        mainFrame.pack();
        mainFrame.setVisible(true);
        displayMessage("BUGRA HAN CETINKAYA");
    }

    @Override
    public void about() {
        super.about();
        displayMessage("BUGRA HAN CETINKAYA CS4B C3653570");
    }

    public void processCommand(String command) {
        String[] parts = command.split("\\s+");
        System.out.println("Command: " + command);
        switch (parts[0]) {
            case "pendown":
                penDown();
                break;
            case "penup":
                penUp();
                break;
            case "forward":
                if (parts.length < 2) {
                    System.out.println("Missing parameter for 'forward' command.");
                    break;
                }
                if (!isNumeric(parts[1])) {
                    System.out.println("Non-numeric parameter for 'forward' command.");
                    break;
                }
                int distance = Integer.parseInt(parts[1]);
                forward(distance);
                break;
            case "backward":
                if (parts.length < 2) {
                    System.out.println("Missing parameter for 'backward' command.");
                    break;
                }
                if (!isNumeric(parts[1])) {
                    System.out.println("Non-numeric parameter for 'backward' command.");
                    break;
                }
                distance = Integer.parseInt(parts[1]);
                forward(-distance);
                break;
            case "turnleft":
                turn(parts, -1);
                break;
            case "turnright":
                turn(parts, 1);
                break;
            case "clear":
                clear();
                break;
            case "reset":
                reset();
                break;
            case "about":
                about();
                break;
            case "circle":
                circle(36);
                break;
            case "red":
                setPenColour(Color.red);
                break;
            case "blue":
                setPenColour(Color.BLUE);
                break;
            case "green":
                setPenColour(Color.GREEN);
                break;
            case "yellow":
                setPenColour(Color.YELLOW);
                break;
            case "save":
                saveImage();
                break;
            case "load":
                loadImage();
                break;
            case "square":
                int length = parts.length > 1 ? Integer.parseInt(parts[1]) : 100; // Default length is 100
                square(length);
                break;
            case "pen":
                if (parts.length != 4) {
                    System.out.println("Invalid number of parameters for pen command.");
                    break;
                }
                try {
                    int red = Integer.parseInt(parts[1]);
                    int green = Integer.parseInt(parts[2]);
                    int blue = Integer.parseInt(parts[3]);
                    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
                        System.out.println("Invalid RGB values. Values must be between 0 and 255.");
                        break;
                    }
                    setPenColour(new Color(red, green, blue));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid RGB values. Please provide integer values.");
                }
                break;
            case "triangle":
                int size = parts.length > 1 ? Integer.parseInt(parts[1]) : 100;
                triangle(size);
                break;
            case "pentagon":
                size = parts.length > 1 ? Integer.parseInt(parts[1]) : 100;
                pentagon(size);
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

    public void square(int length) {
        for (int i = 0; i < 4; i++) {
            forward(length);
            turnRight();
        }
    }

    public void triangle(int size) {
        for (int i = 0; i < 3; i++) {
            forward(size);
            turnLeft(120);
        }
    }

    public void pentagon(int size) {
        for (int i = 0; i < 5; i++) {
            forward(size);
            turnLeft(72);
        }
    }

    public void saveImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(image.getGraphics());
        try {
            File file = new File("saved.png");
            ImageIO.write(image, "png", file);
            displayMessage("Image Saved");
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
            displayMessage("Failed to save image: " + e.getMessage());
        }
    }

    public void loadImage() {
        try {
            File file = new File("circle1.png");
            BufferedImage img = ImageIO.read(file);
            if (img != null) {
                System.out.println("Image loaded successfully.");
            } else {
                System.out.println("Failed to load image.");
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void turn(String[] parts, int direction) {
        int angle = 90;
        if (parts.length > 1 && isNumeric(parts[1])) {
            angle = Integer.parseInt(parts[1]);
        }
        if (direction == 1) {
            turnLeft(angle);
        } else {
            turnRight(angle);
        }
    }
}
