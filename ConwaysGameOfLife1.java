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
import javax.swing.Timer; //timer for a delay
public class ConwaysGameOfLife7 extends JFrame implements ActionListener,MouseListener
{
    int windowX = 1000;  //size of the window
    int windowY = 800;
    int xMargin = 20;  //margin of the grid from the top lef of the window
    int yMargin =40;  
   
    //how many rows and columns there are and their size
    int Rows = 50;
    int Cols = 50;
    int cellSize = 15;
   
   
    int[][] grid = new int[Rows][Cols];        //2d array to hold the vaule of each cell
    int[][] Neighbours = new int [Rows][Cols]; //2d array to hold the value of the sourounding cells
   
    int advanceTurns = 10;
   
    //used when finding the cell clicked instead of the x and y cordinate
    int cellX;
    int cellY;
   
    //creating all the java buttons,canvas and timer
    Canvas myGraphic;
    JButton quitButton;
    JButton clearButton;
    JButton advanceTurn;
    JButton advanceSomeTurns;
    JButton increaseTurn;
    JButton decreaseTurn;
    Timer timer;
    JButton stopButton;
    /**
     * Constructor for objects of class ConwaysGameOfLife
     */
    public ConwaysGameOfLife7()
    {
        this.setLayout(null);                      
        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setBounds (830,30,100,50);
        quitButton.setFocusable(false);
        quitButton.addActionListener(this);
        this.add(quitButton);
       
        clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setBounds (830,130,100,50);
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);
        this.add(clearButton);

        advanceTurn = new JButton();
        advanceTurn.setText("Advance Turn");
        advanceTurn.setBounds (800,230,150,50);
        advanceTurn.setFocusable(false);
        advanceTurn.addActionListener(this);
        this.add(advanceTurn);
       
        advanceSomeTurns = new JButton();
        advanceSomeTurns.setText("Advance "+advanceTurns+" Turns");
        advanceSomeTurns.setBounds (790,330,140,50);
        advanceSomeTurns.setFocusable(false);
        advanceSomeTurns.addActionListener(this);
        this.add(advanceSomeTurns);
       
        increaseTurn = new JButton();
        increaseTurn.setText("+");
        increaseTurn.setBounds (940,330,45,23);
        increaseTurn.setFocusable(false);
        increaseTurn.addActionListener(this);
        this.add(increaseTurn);
       
        decreaseTurn = new JButton();
        decreaseTurn.setText("-");
        decreaseTurn.setBounds (940,357,45,23);
        decreaseTurn.setFocusable(false);
        decreaseTurn.addActionListener(this);
        this.add(decreaseTurn);
       
        stopButton = new JButton();
        stopButton.setText("Stop");
        stopButton.setBounds(830, 390, 100, 50);
        stopButton.setFocusable(false);
        stopButton.addActionListener(this);
        this.add(stopButton);
               
       
        //set title of the window
        setTitle("Conways Game Of Life");
        //window dimentions when open.
        this.getContentPane().setPreferredSize(new Dimension(windowX,windowY));
        //says what happens when we close the window.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   
       
        JPanel panel = new JPanel(); // panel for canvas
        panel.setPreferredSize(new Dimension(windowX,windowY));
        myGraphic = new Canvas();
        panel.add(myGraphic);
        addMouseListener(this); // listen for the mouse
        //make the window visible
        this.setResizable(false); //make user unable to change window size
        this.pack();
        this.toFront();
        this.setVisible(true);

    }
   
   
    public void applyRules(){
        for (int R = 0; R < Rows; R++) {
            for (int C = 0; C < Cols; C++) {
                Neighbours[R][C] = countNeighbours(R,C);
            }
        }

        for (int R = 0; R < Rows; R++) {
            for (int C = 0; C < Cols; C++) {
                if (grid[R][C] == 1){
                    if (Neighbours[R][C] < 2){
                        grid[R][C] = 0;
                    }
                    if (Neighbours[R][C] > 3){
                        grid[R][C] = 0;
                    }
                   
                }
                if (grid[R][C] == 0){
                    if (Neighbours[R][C] == 3){
                        grid[R][C] = 1;
                    }
                }
            }
        }
        //done in two loops to prevent it changing while still going through the grid
        repaint();
    }
   
   
   
    public void paint (Graphics g) {
        super.paint(g);

       
        Graphics2D g2 = (Graphics2D) g; // set up to draw.
       
        // Draw grid background
        g.setColor(Color.WHITE);
        g.fillRect(xMargin, yMargin, Cols * cellSize, Rows * cellSize);

        // Draw cells
        for (int R = 0; R < Rows; R++) {
            for (int C = 0; C < Cols; C++) {
                if (grid[R][C] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect((C * cellSize)+xMargin, (R * cellSize)+yMargin, cellSize, cellSize);
                }
                g.setColor(Color.GRAY);
                g.drawRect((C * cellSize)+xMargin, (R * cellSize)+yMargin, cellSize, cellSize);
            }
        }
       
    }
   
   
   
   
    //actions to events
        public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
       
        System.out.println(cmd);        

        if (e.getSource()==quitButton){
            System.exit(0);
        } //Quit program
       
        if (e.getSource()==clearButton){
            for (int R = 0; R < Rows; R++) {
                for (int C = 0; C < Cols; C++) {
                    if (grid[R][C] == 1){
                        grid[R][C] = 0;
                    }
                }
            }
            repaint();
        } //Clear grid
       
            if (e.getSource()==advanceTurn){
            System.out.println("advance a turn");
            applyRules();
        }
       
            if (e.getSource()==increaseTurn){
            advanceTurns = advanceTurns + 1;
            advanceSomeTurns.setText("Advance "+advanceTurns+" Turns");
        }
            if (e.getSource()==decreaseTurn){
            if (advanceTurns>0){
                advanceTurns = advanceTurns - 1;
            }  
            advanceSomeTurns.setText("Advance "+advanceTurns+" Turns");
        }
            if (e.getSource()==advanceSomeTurns){
            autoTurns(advanceTurns);
        }
        if (e.getSource() == stopButton) {
            if (timer != null && timer.isRunning()) {
                timer.stop();
                System.out.println("Timer stopped");
            }
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
       
        int mouseXGrid = mouseX -xMargin;  //mouse position against the grid
        int mouseYGrid = mouseY -yMargin;  //mouse position against the grid
       
        //finds out what cell was clicked on
        cellY = mouseXGrid / cellSize;
        cellX = mouseYGrid / cellSize;
        System.out.println("clicked cell= "+cellX+","+cellY);

        //finds out if the cell is already alive or dead, and then switches its state.
        if (grid[cellX][cellY] == 0){
            grid[cellX][cellY] = 1;
        }
        else if (grid[cellX][cellY] == 1){
            grid[cellX][cellY] = 0;
        }
       
       
        repaint();//refreshes the screen
       
        countNeighbours(cellX,cellY);
    }
   
    public void autoTurns(int rounds){
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
       
        final int[] count = {0};  // counter wrapped in array to modify inside inner class
       
        timer = new Timer(300, new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count[0] < rounds) {
                    applyRules();  // advance one generation
                    count[0]++;
                } else {
                    timer.stop();  // stop timer after completing all rounds
                }
            }
        });

        timer.start();  // start the timer to begin simulation
    }
       
   
   
    public int countNeighbours(int R, int C){
        int Neighbours = 0;
        //top and bottom cells and the corners of them
        if (R == 0){
                if (grid[R+1][C] == 1) {
                Neighbours++ ;
            }
            if (C>0 && C < (Cols-1)){
                if (grid[R+1][C-1] == 1){
                    Neighbours++ ;
                }
            }
            if (C < (Cols-1) && C>0){
                if (grid[R+1][C+1] == 1){
                    Neighbours++ ;
                }
            }
        }
        if (R == (Rows-1)){
                if (grid[R-1][C] == 1){
                Neighbours++ ;
            }
            if (C>0 && C < (Cols-1)){
                if (grid[R-1][C-1] == 1){
                    Neighbours++ ;
                }
            }
            if (C < (Cols-1) && C>0){
                if (grid[R-1][C+1] == 1){
                    Neighbours++ ;
                }
            }
        }
       
        //far left and far right cells
        if (C == 0){
            if (grid[R][C+1] == 1){
                Neighbours++ ;
            }
            if (R > 0){
                if (grid[R-1][C+1] == 1){
                    Neighbours++ ;
                }
            }
            if (R < (Rows-1)){
                if (grid[R+1][C+1] == 1){
                    Neighbours++ ;
                }
                }
        }
        if (C == (Cols-1)){
               if (grid[R][C-1] == 1){
               Neighbours++ ;
           }
           if (R>0){
               if (grid[R-1][C-1] == 1){
                    Neighbours++ ;
               }
           }
           if (R < (Rows-1)){
               if (grid[R+1][C-1] == 1){
                   Neighbours++ ;
               }
           }
        }
       
       
        //left and right cells when in grid
        if (C > 0 && C < (Cols-1)){
                if (grid[R][C-1] == 1){
                Neighbours++ ;
            }
                if (grid[R][C+1] == 1){
                Neighbours++ ;
            }
        }
        //up and down cells when in grid
        if (R > 0 && R < (Rows-1)){
                if (grid[R-1][C] == 1){
                Neighbours++ ;
            }
                if (grid[R+1][C] == 1){
                Neighbours++ ;
            }
        }
       
        //corners when in grid
        if ((R > 0 && R < (Rows-1)) && (C > 0 && C < (Cols-1))){
            if (grid[R+1][C+1] == 1){
                Neighbours++ ;
            }
            if (grid[R-1][C+1] == 1){
                Neighbours++ ;
            }
            if (grid[R-1][C-1] == 1){
                Neighbours++ ;
            }
            if (grid[R+1][C-1] == 1){
                Neighbours++ ;
            }
        }
           
        return Neighbours;
       
    }
   

}