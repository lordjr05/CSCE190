/*
 * Written by Jeffrey Lord
 */
import javax.swing.*;
import java.awt.*;

public class HW05 extends JPanel 
{
    private static final int PIXEL_LIMIT = 4;

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Sierpinski's Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        //Connects the panel to the main code
        HW05 panel = new HW05();
        frame.add(panel);

        // Set frame visibility
        frame.setVisible(true);
    }
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        //Makes Initial Starting location
        int[] xPoints = {getWidth() / 2, 50, getWidth() - 50};
        int[] yPoints = {50, getHeight() - 50, getHeight() - 50};

        //Draws the Sierpinski's Triangle
        drawSierpinski(g, xPoints, yPoints, PIXEL_LIMIT);
    }
    private void drawSierpinski(Graphics g, int[] xPoints, int[] yPoints, int limit) 
    {
        //Makes the pixel limit of 4
        if (Math.abs(xPoints[1] - xPoints[0]) <= limit) 
        {
            return;
        }
        // Draws the frame triangle
        g.setColor(Color.BLACK);
        g.fillPolygon(xPoints, yPoints, 3);

        //Finds the midpoints for the next triangle location
        int midX1 = (xPoints[0] + xPoints[1]) / 2;
        int midY1 = (yPoints[0] + yPoints[1]) / 2;

        int midX2 = (xPoints[1] + xPoints[2]) / 2;
        int midY2 = (yPoints[1] + yPoints[2]) / 2;

        int midX3 = (xPoints[0] + xPoints[2]) / 2;
        int midY3 = (yPoints[0] + yPoints[2]) / 2;

        //Places and Draws the new middle upside down triangles
        g.setColor(Color.WHITE);
        int[] innerXPoints = {midX1, midX2, midX3};
        int[] innerYPoints = {midY1, midY2, midY3};
        g.fillPolygon(innerXPoints, innerYPoints, 3);

        //Recursively draws the new triangles
        drawSierpinski(g, new int[]{xPoints[0], midX1, midX3}, new int[]{yPoints[0], midY1, midY3}, limit);
        drawSierpinski(g, new int[]{midX1, xPoints[1], midX2}, new int[]{midY1, yPoints[1], midY2}, limit);
        drawSierpinski(g, new int[]{midX3, midX2, xPoints[2]}, new int[]{midY3, midY2, yPoints[2]}, limit);
    }
}
