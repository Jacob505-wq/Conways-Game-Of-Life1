/**
 *ConwaysGameOfLife
 * -AS981897
 *
 * @author Jacob Busby
 * @version 20/5/25
 */
import javax.swing.*;       //for GUI
import java.awt.*;          //for GUI
import java.awt.event.*;    //for GUI
import java.awt.geom.*;     //geometry for lines and shapes
import javax.swing.JButton; //for buttons
import javax.swing.Timer;   //timer for a delay 
public class ConwaysGameOfLife1 extends JFrame implements ActionListener,MouseListener
{
    final int windowX = 1000;  //size of the window
    final int windowY = 800;
    final int xMargin = 20+215;  //margin of the grid from the top lef of the window
    final int yMargin =40;   
    
    //how many rows and columns there are and their size
    final int rows = 30;
    final int cols = 30;
    final int cellSize = 25;

    int[][] grid = new int[rows][cols];        //2d array to hold the vaule of each cell
    int[][] neighbours = new int [rows][cols]; //2d array to hold the value of the sourounding cells
    
    //sets the default number for the advance a number of times button
    int advanceTurns = 10; 
    
    //used when finding the cell clicked instead of the x and y cordinate
    int cellX;
    int cellY;
    //used to create the button placement
    final int buttonMarginX = -770;
    final int buttonMarginY = 0;
    
    //creating/implementing all the java buttons,canvas and timer
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
     * Constructor for objects of class ButtonLayout2ConwaysGameOfLife
     */
    public ConwaysGameOfLife1()
    {
        this.setLayout(null);            
        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setBounds (830+buttonMarginX,30+buttonMarginY,100,50);
        quitButton.setFocusable(false); 
        quitButton.addActionListener(this);
        this.add(quitButton);
        //Quit button
        
        clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setBounds (830+buttonMarginX,130+buttonMarginY,100,50);
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);
        this.add(clearButton);
        //clear button
        
        advanceTurn = new JButton();
        advanceTurn.setText("Advance Turn");
        advanceTurn.setBounds (800+buttonMarginX,230+buttonMarginY,150,50);
        advanceTurn.setFocusable(false);
        advanceTurn.addActionListener(this);
        this.add(advanceTurn);
        //advance a turn button
        
        advanceSomeTurns = new JButton();
        advanceSomeTurns.setText("Advance "+advanceTurns+" Turns");
        advanceSomeTurns.setBounds (790+buttonMarginX,330+buttonMarginY,140,50);
        advanceSomeTurns.setFocusable(false);
        advanceSomeTurns.addActionListener(this);
        this.add(advanceSomeTurns);
        //advance a number of turns button
        
        increaseTurn = new JButton();
        increaseTurn.setText("+");
        increaseTurn.setBounds (940+buttonMarginX,330+buttonMarginY,45,23);
        increaseTurn.setFocusable(false);
        increaseTurn.addActionListener(this);
        this.add(increaseTurn);
        //button to increment the value for advance turns
 
        decreaseTurn = new JButton();
        decreaseTurn.setText("-");
        decreaseTurn.setBounds (940+buttonMarginX,357+buttonMarginY,45,23);
        decreaseTurn.setFocusable(false);
        decreaseTurn.addActionListener(this);
        this.add(decreaseTurn);
        //button to decrement the value for advance turns

        
        stopButton = new JButton();
        stopButton.setText("Stop");
        stopButton.setBounds(830+buttonMarginX, 390+buttonMarginY, 100, 50);
        stopButton.setFocusable(false);
        stopButton.addActionListener(this);
        this.add(stopButton);
        //stop button to stop the advance a number of turns function
        
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
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                neighbours[r][c] = countNeighbours(r,c);
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1){
                    if (neighbours[r][c] < 2){
                        grid[r][c] = 0;
                    }
                    if (neighbours[r][c] > 3){
                        grid[r][c] = 0;
                    }
                    
                }
                if (grid[r][c] == 0){
                    if (neighbours[r][c] == 3){
                        grid[r][c] = 1;
                    }
                }
            }
        }
        //done in two loops to prevent it changing while still going through the grid
        repaint();
    }//function to apply the rules 
    
    
    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g; // set up to draw.
        
        // Draw grid background
        g.setColor(Color.WHITE);
        g.fillRect(xMargin, yMargin, cols * cellSize, rows * cellSize);

        // Draw cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect((c * cellSize)+xMargin, (r * cellSize)+yMargin, cellSize, cellSize);
                }
                g.setColor(Color.GRAY);
                g.drawRect((c * cellSize)+xMargin, (r * cellSize)+yMargin, cellSize, cellSize);
            }
        }//loop to draw the grid
    }//function to make the GUI
    
    //actions to events
        public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        
        System.out.println(cmd);        

        if (e.getSource()==quitButton){
            System.exit(0);
        } //Quit program
        
        if (e.getSource()==clearButton){
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 1){
                        grid[r][c] = 0;
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
    }//method that responds to button presses
    
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
        
        //count the neighbours of the clicked cell
        countNeighbours(cellX,cellY);
    }//method to detect a mouse click and its cordinites
    
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
    }//method to make the advance a number of turns have a delay between increnments
        
    
    public int countNeighbours(int r, int c){
        int neighbours = 0;
        //top and bottom cells and the corners of them
        if (r == 0){
                if (grid[r+1][c] == 1) {
                neighbours++ ;
            }
            if (c>0 && c < (cols-1)){
                if (grid[r+1][c-1] == 1){
                    neighbours++ ;
                }
            }
            if (c < (cols-1) && c>0){
                if (grid[r+1][c+1] == 1){
                    neighbours++ ;
                }
            }
        }
        if (r == (rows-1)){
                if (grid[r-1][c] == 1){
                neighbours++ ;
            }
            if (c>0 && c < (cols-1)){
                if (grid[r-1][c-1] == 1){
                    neighbours++ ;
                }
            }
            if (c < (cols-1) && c>0){
                if (grid[r-1][c+1] == 1){
                    neighbours++ ;
                }
            }
        }
        
        //far left and far right cells
        if (c == 0){
            if (grid[r][c+1] == 1){
                neighbours++ ;
            }
            if (r > 0){
                if (grid[r-1][c+1] == 1){
                    neighbours++ ;
                }
            }
            if (r < (rows-1)){
                if (grid[r+1][c+1] == 1){
                    neighbours++ ;
                }
                }
        }
        if (c == (cols-1)){
               if (grid[r][c-1] == 1){
               neighbours++ ;
           }
           if (r>0){
               if (grid[r-1][c-1] == 1){
                    neighbours++ ;
               }
           }
           if (r < (rows-1)){
               if (grid[r+1][c-1] == 1){
                   neighbours++ ;
               }
           }
        }
        
        //left and right cells when in grid
        if (c > 0 && c < (cols-1)){
                if (grid[r][c-1] == 1){
                neighbours++ ;
            }
                if (grid[r][c+1] == 1){
                neighbours++ ;
            }
        }
        //up and down cells when in grid
        if (r > 0 && r < (rows-1)){
                if (grid[r-1][c] == 1){
                neighbours++ ;
            }
                if (grid[r+1][c] == 1){
                neighbours++ ;
            }
        }
        
        //corners when in grid
        if ((r > 0 && r < (rows-1)) && (c > 0 && c < (cols-1))){
            if (grid[r+1][c+1] == 1){
                neighbours++ ;
            }
            if (grid[r-1][c+1] == 1){
                neighbours++ ;
            }
            if (grid[r-1][c-1] == 1){
                neighbours++ ;
            }
            if (grid[r+1][c-1] == 1){
                neighbours++ ;
            }
        }
            
        return neighbours;
    }//loop that counts the neighbours for a selected cell
}
