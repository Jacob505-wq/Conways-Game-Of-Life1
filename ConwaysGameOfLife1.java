/**
 *ConwaysGameOfLife
 * -AS981897
 *
 * @author Jacob Busby
 * @version 20/5/25
 */
import javax.swing.*;    //for GUI
import java.awt.*;       //for GUI
import java.awt.event.*; //for GUI
import java.awt.geom.*; //geometry for lines and shapes
import javax.swing.JButton;//for buttons

public class ConwaysGameOfLife1 extends JFrame implements ActionListener,MouseListener
{

    int Rows = 50;
    int Cols = 50;
    int cellSize = 15;
    int[][] grid = new int[Rows][Cols];
   
    int cellX;
    int cellY;
    Canvas myGraphic;

    /**
     * Constructor for objects of class ConwaysGameOfLife
     */
    public ConwaysGameOfLife1()
    {
        //set title of the window
        setTitle("Conways Game Of Life");
       
        //window dimentions when open.
        this.getContentPane().setPreferredSize(new Dimension(1000,800));
       
        //says what happens when we close the window.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   
       
       
       
        JPanel panel = new JPanel(); // panel for canvas
        panel.setPreferredSize(new Dimension(1000,800));
        myGraphic = new Canvas();
        panel.add(myGraphic);

        addMouseListener(this); // listen for the mouse

        //make the window visible
        this.setResizable(false); //make user unable to change window size
        this.pack();
        this.toFront();
        this.setVisible(true);

    }
   
    public void paint (Graphics g) {
        super.paint(g);

       
        Graphics2D g2 = (Graphics2D) g; // set up to draw.

       
        // Draw grid background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Cols * cellSize, Rows * cellSize);

       
        // Draw cells
        for (int R = 0; R < Rows; R++) {
            for (int C = 0; C < Cols; C++) {
                if (grid[R][C] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(C * cellSize, R * cellSize, cellSize, cellSize);
                }
                g.setColor(Color.GRAY);
                g.drawRect(C * cellSize, R * cellSize, cellSize, cellSize);
            }
        }
       
    }
   
   
   
   
   
        public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
       
        System.out.println(cmd);
       
        switch (cmd) {
            case "Quit" :System.exit(0);
                        break;
            case "option1" :System.out.println("switchOption1");
                        break;    
            default: System.out.println("Invalid Input");
                        break;
        }
    }
   
    public void mouseExited(MouseEvent e) {System.out.println("exit");}
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    public void mouseClicked(MouseEvent e) {
        int mouseX=e.getX();
        int mouseY=e.getY();
        System.out.println("click "+mouseX+","+mouseY);
       
        //finds out what cell was clicked on
        cellY = mouseX / cellSize;
        cellX = mouseY / cellSize;
        System.out.println("clicked cell= "+cellX+","+cellY);

        //finds out if the cell is already alive or dead, and then switches its state.
        if (grid[cellX][cellY] == 0){
            grid[cellX][cellY] = 1;
        }
        else if (grid[cellX][cellY] == 1){
            grid[cellX][cellY] = 0;
        }
        repaint();//refreshes the screen
    }
   
   

}