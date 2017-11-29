import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HangManWindow {

    private JFrame frame;

    String[] arrayOfWords = new String[] { "Cheese", "Cookies", "Zigzag",
        "Jawbreaker", "Computer", "Phone", "Clock", "House", "Oxygen",
        "PowerPoint", "Calendar", "Spaceship" };

    Character[] arrayOfTheWord; // A new array will be created, this will host
                                // the single word
    Character[] arrayOfTheWordDashed; // A new array will be created, this will
                                      // host the single word
    Character[] arrayLettersEntered = new Character[60]; // This array exists
                                                         // for the purpose of
                                                         // checking what you
                                                         // entered (that way
                                                         // you cant enter the
                                                         // same letter more
                                                         // than once)

    int arrayRandomNumber; // This integer is used for single player, chooses a
                           // random number which is used with the array of
                           // words to get a random word
    int numberOfTries = 0; // This integer counts your turns
    int turnNumber = 1; // This is simply the turn number you are on
    String dash = "_";
    private JTextField textField; // Use as input
    Character charEntered; // This is the single character you have entered, it
                           // gets defined below
    String textEntered; // This is the same as the char, but in string form
    Boolean gameOver = false; // The game will run as long as gameOver=false
    int wordLength; // This counts the amount of letters in the word you are
                    // using
    int triesLeft = 8; // By default, you can make 8 wrong guesses
    String currentWord = "";
    int correctTries = 0;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HangManWindow window = new HangManWindow();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the application.
     */
    public HangManWindow() {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // These initialize all the text/buttons/ect
        JLabel lblWelcomeToHangman = new JLabel("Welcome to HangMan!");
        JButton btnPlayer1 = new JButton("1 Player");
        JButton btnPlayer2 = new JButton("2 Players");
        JLabel lblJavaVersion = new JLabel("Java Version 1.0");
        JLabel lblBottomBar = new JLabel("2016 - Made for EGR - 120");
        JLabel lblByCristhianBenitez = new JLabel("By Cristhian Benitez");
        JLabel lblEnterLetter = new JLabel("Enter Letter:");
        textField = new JTextField();
        JLabel hangWordLabel = new JLabel("");
        JLabel lblNumberOfTries = new JLabel("Number of Tries: "
            + numberOfTries);
        JLabel lblTurnNumber = new JLabel("Turn Number: " + turnNumber);
        JLabel lblNewLabel = new JLabel("");
        JButton btnEnter = new JButton("Enter");
        JButton btnSubmit = new JButton("Submit");

        JLabel lblTriesLeft = new JLabel("Tries Left: " + triesLeft);

        btnEnter.addActionListener(new ActionListener() { // When You click
                                                          // Enter
            public void actionPerformed(ActionEvent e) {
                /////////// ------------------------------------------------------------------**********************************
                textEntered = textField.getText(); // Obtains what was entered
                                                   // in the textfiled and
                                                   // converts to string

                if (textEntered.length() > 1) // Checks to see if what has been
                                              // entered has more than one
                                              // letter
                    JOptionPane.showMessageDialog(null,
                        "Please enter only letter at a time!");
                else if (textEntered.length() == 0) // Checks to see if what has
                                                    // been entered is nothing
                    JOptionPane.showMessageDialog(null,
                        "Seems like you haven't entered anything!");

                else { // If those two cases arent met then it means we can use
                       // it!
                    charEntered = textEntered.charAt(0); // Converts string to a
                                                         // character
                    arrayLettersEntered[numberOfTries] = charEntered; // The
                                                                      // character
                                                                      // is
                                                                      // stored
                                                                      // in a
                                                                      // special
                                                                      // array
                                                                      // (contains
                                                                      // all
                                                                      // your
                                                                      // inputs)
                    Boolean beginCheckWord = true;

                    for (int x = 0; x < numberOfTries; x++) // Here I check if
                                                            // the letter has
                                                            // been entered
                                                            // already
                        if (arrayLettersEntered[x] == charEntered)
                            beginCheckWord = false;

                    if (beginCheckWord) { // So, if we havent entered the letter
                                          // before we can finally begin
                                          // checking if this letter is in the
                                          // hangman word!
                        numberOfTries++; // NumberOfTries is increased
                        turnNumber++; // So is the turn number naturally
                        Boolean match = false;
                        for (int x = 0; x < arrayOfTheWord.length; x++) { // this
                                                                          // goes
                                                                          // through
                                                                          // the
                                                                          // whole
                                                                          // word
                                                                          // and
                                                                          // compares
                                                                          // your
                                                                          // Char
                                                                          // to
                                                                          // the
                                                                          // word
                            if (arrayOfTheWord[x] == charEntered) {
                                arrayOfTheWordDashed[x] = charEntered;
                                System.out.println("One Match!");
                                correctTries++;
                                match = true;
                            }
                        }

                        String tempDashDisplay = ""; // This string will be
                                                     // replacing the "_ _ _ _ _
                                                     // " of the word
                        for (int x = 0; x < arrayOfTheWordDashed.length; x++) {
                            tempDashDisplay = " " + tempDashDisplay
                                + arrayOfTheWordDashed[x] + " ";
                        }

                        if (!match) // Number of tries left only decreases if
                                    // there is no match
                            triesLeft--;

                        // Labels get updated below
                        lblNumberOfTries.setText("Number of Tries: "
                            + numberOfTries);
                        lblTurnNumber.setText("Turn Number: " + turnNumber);
                        lblTriesLeft.setText("Tries Left: " + triesLeft);
                        textField.setText("");
                        hangWordLabel.setBounds(-10, 80, 438, 49);
                        hangWordLabel.setText(tempDashDisplay);
                        hangWordLabel.setHorizontalAlignment(
                            SwingConstants.CENTER);
                    }
                    else // This else only happens when beginCheckWord is false
                         // ( arrayLettersEntered already contains the input you
                         // are trying to enter)
                        JOptionPane.showMessageDialog(null,
                            "You already entered that letter!~");
                }

                if (wordLength == correctTries) {
                    JOptionPane.showMessageDialog(null,
                        "Congrats! You Won! Have a Cookie!");
                    btnEnter.hide();
                    textField.hide();
                    lblEnterLetter.hide();
                    lblTriesLeft.setText("You Won! Try Again? ");
                }

                if (triesLeft <= 0) { // When Gameover
                    JOptionPane.showMessageDialog(null,
                        "You have run out of tries! Gameover!");
                    btnEnter.hide();
                    textField.hide();
                    lblEnterLetter.hide();
                    lblNumberOfTries.setText("Try Again! Your Word: ");
                    lblTurnNumber.setText("" + currentWord);
                }
            }
        });
        btnPlayer1.addActionListener(new ActionListener() { // When 1 Player
                                                            // mode is clicked
            public void actionPerformed(ActionEvent e) { // This is the action
                                                         // trigger when you
                                                         // click 1 Player!
                arrayRandomNumber = (int)(Math.random() * 12);
                wordLength = arrayOfWords[arrayRandomNumber].length();
                currentWord = arrayOfWords[arrayRandomNumber];
                System.out.print("" + arrayOfWords[arrayRandomNumber]);
                lblWelcomeToHangman.setText("Your word has: " + wordLength
                    + " letters!");

                lblBottomBar.setText(
                    "Cristhian Benitez - 2016 - Made for EGR - 120");
                lblWelcomeToHangman.setBounds(6, 10, 438, 49);

                btnPlayer1.hide();
                btnPlayer2.hide();
                lblJavaVersion.hide();
                lblByCristhianBenitez.hide();
                lblEnterLetter.show();
                textField.show();
                lblNumberOfTries.show();
                lblTurnNumber.show();
                btnEnter.show();
                lblTriesLeft.show();

                arrayOfTheWordDashed = new Character[wordLength];
                arrayOfTheWord = new Character[wordLength];

                for (int x = 0; x < wordLength; x++) {
                    arrayOfTheWordDashed[x] = '_';
                    arrayOfTheWord[x] = arrayOfWords[arrayRandomNumber].charAt(
                        x);
                    hangWordLabel.setText(hangWordLabel.getText() + " _ ");
                }
            }
        });
        btnPlayer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblWelcomeToHangman.setText("2 Player Mode");

                lblBottomBar.setText(
                    "Cristhian Benitez - 2016 - Made for EGR - 120");
                lblWelcomeToHangman.setBounds(6, 10, 438, 49);

                btnPlayer1.hide();
                btnPlayer2.hide();
                lblJavaVersion.hide();
                lblByCristhianBenitez.hide();
                lblEnterLetter.show();
                textField.show();
                lblNumberOfTries.show();
                lblTurnNumber.show();
                btnSubmit.show();
                lblTriesLeft.show();
                lblEnterLetter.setText("Enter Word: ");
                lblNumberOfTries.setText("Player 1 Enters Word");
                lblTurnNumber.setText("Player 2 Guesses ");
                lblTriesLeft.setText("Press Submit to Continue");
            }
        });

        btnSubmit.addActionListener(new ActionListener() { // This is only in 2
                                                           // players - submit
                                                           // button
            public void actionPerformed(ActionEvent e) {
                currentWord = textField.getText();
                wordLength = currentWord.length();
                System.out.print("Current Word: " + currentWord);

                lblWelcomeToHangman.setText("Your word has: " + wordLength
                    + " letters!");

                // System.out.print("Word Length "+ wordLegth);

                lblBottomBar.setText(
                    "Cristhian Benitez - 2016 - Made for EGR - 120");
                lblWelcomeToHangman.setBounds(6, 10, 438, 49);

                btnPlayer1.hide();
                btnPlayer2.hide();
                lblJavaVersion.hide();
                lblByCristhianBenitez.hide();
                lblEnterLetter.show();
                textField.show();
                lblNumberOfTries.show();
                lblTurnNumber.show();
                btnEnter.show();
                lblTriesLeft.show();

                lblEnterLetter.setText("Player 2 Enter Letter: ");
                lblNumberOfTries.setText("Number of Tries: " + numberOfTries);
                lblTurnNumber.setText("Turn Number: " + turnNumber);
                lblTriesLeft.setText("Tries Left: " + triesLeft);
                textField.setText("");
                btnSubmit.hide();

                arrayOfTheWordDashed = new Character[wordLength];
                arrayOfTheWord = new Character[wordLength];

                for (int x = 0; x < wordLength; x++) {
                    arrayOfTheWordDashed[x] = '_';
                    arrayOfTheWord[x] = currentWord.charAt(x);
                    hangWordLabel.setText(hangWordLabel.getText() + " _ ");
                }
            }
        });

        frame = new JFrame();
        frame.getContentPane().setBackground(null);
        frame.setResizable(false);
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // This is where all labels/buttons/textfield are created, they defined
        // above actually, but everything else is below
        btnPlayer1.setBounds(78, 124, 117, 29);
        frame.getContentPane().add(btnPlayer1);

        btnPlayer2.setBounds(252, 124, 117, 29);
        frame.getContentPane().add(btnPlayer2);

        lblWelcomeToHangman.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcomeToHangman.setFont(new Font("Helvetica Neue", Font.PLAIN, 19));
        lblWelcomeToHangman.setBounds(6, 32, 438, 49);
        frame.getContentPane().add(lblWelcomeToHangman);

        lblJavaVersion.setBounds(175, 78, 100, 16);
        frame.getContentPane().add(lblJavaVersion);

        lblBottomBar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBottomBar.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
        lblBottomBar.setBounds(6, 306, 438, 16);
        frame.getContentPane().add(lblBottomBar);

        // Initialize Hidden
        textField.setBounds(165, 202, 117, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        textField.hide();

        // Initialize Hidden
        lblEnterLetter.setBounds(85, 207, 108, 16);
        frame.getContentPane().add(lblEnterLetter);
        lblEnterLetter.hide();

        lblByCristhianBenitez.setHorizontalAlignment(SwingConstants.CENTER);
        lblByCristhianBenitez.setFont(new Font("Helvetica Neue", Font.PLAIN,
            21));
        lblByCristhianBenitez.setBounds(6, 188, 438, 51);
        frame.getContentPane().add(lblByCristhianBenitez);

        hangWordLabel.setBackground(Color.RED);
        hangWordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        hangWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hangWordLabel.setBounds(6, 78, 438, 29);
        frame.getContentPane().add(hangWordLabel);

        lblNumberOfTries.setBounds(79, 146, 161, 16);
        frame.getContentPane().add(lblNumberOfTries);

        lblTurnNumber.setBounds(262, 146, 170, 16);
        frame.getContentPane().add(lblTurnNumber);

        lblNewLabel.setBounds(198, 174, 61, 16);
        frame.getContentPane().add(lblNewLabel);

        btnEnter.setBounds(280, 202, 82, 29);
        frame.getContentPane().add(btnEnter);

        btnSubmit.setBounds(280, 202, 82, 29);
        frame.getContentPane().add(btnSubmit);

        lblTriesLeft.setHorizontalAlignment(SwingConstants.CENTER);
        lblTriesLeft.setBounds(6, 251, 438, 16);
        frame.getContentPane().add(lblTriesLeft);

        lblNumberOfTries.hide();
        lblTurnNumber.hide();
        btnEnter.hide();
        btnSubmit.hide();
        lblTriesLeft.hide();
    }
}
