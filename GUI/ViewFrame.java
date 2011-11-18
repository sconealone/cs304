/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import users.Controller;
/*
 * ViewFrame.java
 *
 * Created on 16-Nov-2011, 4:09:38 PM
 */

/**
 *
 * @author Mitch
 */
public class ViewFrame extends javax.swing.JFrame {
  /** Creates new form ViewFrame */
  public ViewFrame() 
  {
    this.controller = new Controller(this);
    statemap = new HashMap<String,State>();
    statemap.put(START, State.START);
    statemap.put(TABLES, State.TABLES);
    statemap.put(SEARCH, State.SEARCH);
    statemap.put(CHECK_ACCOUNT, State.CHECK_ACCOUNT);
    statemap.put(HOLD_REQUEST, State.HOLD_REQUEST);
    statemap.put(PAY_FINE, State.PAY_FINE);
    statemap.put(ADD_BORROWER, State.ADD_BORROWER);
    statemap.put(CHECK_OUT, State.CHECK_OUT);
    statemap.put(CHECK_OVERDUE, State.CHECK_OVERDUE);
    statemap.put(PROCESS_RETURN, State.PROCESS_RETURN);
    statemap.put(ADD_BOOK, State.ADD_BOOK);
    statemap.put(ADD_COPY, State.ADD_COPY);
    statemap.put(REMOVE_BOOK, State.REMOVE_BOOK);
    statemap.put(REMOVE_BORROWER, State.REMOVE_BORROWER);
    statemap.put(REPORT_POPULAR, State.REPORT_POPULAR);
    statemap.put(REPORT_CHECKED_OUT, State.REPORT_CHECKED_OUT);
    initComponents();
    CardLayout cl = (CardLayout) cardPanel.getLayout();
    state = State.START;
    cl.show(cardPanel, START);
    doButton.setText("Go");
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();
        startPanel = new javax.swing.JPanel();
        startComboBoxPanel = new javax.swing.JPanel();
        navigationComboBox = new javax.swing.JComboBox();
        tablesPanel = new javax.swing.JPanel();
        tableNamePanel = new javax.swing.JPanel();
        tablesComboBox = new javax.swing.JComboBox();
        viewTablePane = new javax.swing.JScrollPane();
        entitiesTable = new javax.swing.JTable();
        searchPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkAccountPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        payFinePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        holdRequestPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        checkOutPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        processReturnPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        addBorrowerPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        checkOverduePanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        addNewBookPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        addNewCopyPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        removeBorrowerPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        removeBookPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        popularReportPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        checkedOutReportPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        doButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        navigationMenu = new javax.swing.JMenu();
        borrowerMenu = new javax.swing.JMenu();
        searchMenuItem = new javax.swing.JMenuItem();
        checkAccountMenuItem = new javax.swing.JMenuItem();
        holdRequestMenuItem = new javax.swing.JMenuItem();
        payFineMenuItem = new javax.swing.JMenuItem();
        clerkMenu = new javax.swing.JMenu();
        addBorrowerMenuItem = new javax.swing.JMenuItem();
        checkOutMenuItem = new javax.swing.JMenuItem();
        processReturnMenuItem = new javax.swing.JMenuItem();
        checkOverdueMenuItem = new javax.swing.JMenuItem();
        librarianMenu = new javax.swing.JMenu();
        addMenu = new javax.swing.JMenu();
        addBookMenuItem = new javax.swing.JMenuItem();
        addBookCopyMenuItem = new javax.swing.JMenuItem();
        removeMenu = new javax.swing.JMenu();
        removeBorrowerMenuItem = new javax.swing.JMenuItem();
        removeBookMenuItem = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        checkedOutReportMenuItem = new javax.swing.JMenuItem();
        popularReportMenuItem = new javax.swing.JMenuItem();
        tableMenuItem = new javax.swing.JMenuItem();
        startMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        manualMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library");
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setLayout(new java.awt.BorderLayout());

        cardPanel.setLayout(new java.awt.CardLayout());

        startPanel.setLayout(new java.awt.BorderLayout());

        startComboBoxPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose a transaction"));

        navigationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            START,
            TABLES,
            SEARCH,
            CHECK_ACCOUNT,
            HOLD_REQUEST,
            PAY_FINE,
            ADD_BORROWER,
            CHECK_OUT,
            CHECK_OVERDUE,
            PROCESS_RETURN,
            ADD_BOOK,
            ADD_COPY,
            REMOVE_BOOK,
            REMOVE_BORROWER,
            REPORT_POPULAR,
            REPORT_CHECKED_OUT
        }));
        startComboBoxPanel.add(navigationComboBox);

        startPanel.add(startComboBoxPanel, java.awt.BorderLayout.NORTH);

        cardPanel.add(startPanel, "Start");

        tablesPanel.setLayout(new java.awt.BorderLayout());

        tableNamePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose table to view"));

        tablesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            "Borrower",
            "BorrowerType",
            "Book",
            "HasAuthor",
            "HasSubject",
            "BookCopy",
            "HoldRequest",
            "Borrowing",
            "Fine"
        }));
        tableNamePanel.add(tablesComboBox);

        tablesPanel.add(tableNamePanel, java.awt.BorderLayout.NORTH);

        entitiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [0][],
            new String [0]

        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewTablePane.setViewportView(entitiesTable);

        tablesPanel.add(viewTablePane, java.awt.BorderLayout.CENTER);

        cardPanel.add(tablesPanel, "View tables");

        searchPanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("search");
        searchPanel.add(jLabel2, java.awt.BorderLayout.CENTER);

        cardPanel.add(searchPanel, "Search for book");

        checkAccountPanel.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("check account");
        checkAccountPanel.add(jLabel3, java.awt.BorderLayout.CENTER);

        cardPanel.add(checkAccountPanel, "Check account");

        payFinePanel.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("pay fine");
        payFinePanel.add(jLabel4, java.awt.BorderLayout.CENTER);

        cardPanel.add(payFinePanel, "Pay a fine");

        holdRequestPanel.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("hold request");
        holdRequestPanel.add(jLabel5, java.awt.BorderLayout.CENTER);

        cardPanel.add(holdRequestPanel, "Place hold request");

        checkOutPanel.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("check out");
        checkOutPanel.add(jLabel7, java.awt.BorderLayout.CENTER);

        cardPanel.add(checkOutPanel, "Check-out books");

        processReturnPanel.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("process returns");
        processReturnPanel.add(jLabel8, java.awt.BorderLayout.CENTER);

        cardPanel.add(processReturnPanel, "Process a return");

        addBorrowerPanel.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("add new borrower");
        addBorrowerPanel.add(jLabel9, java.awt.BorderLayout.CENTER);

        cardPanel.add(addBorrowerPanel, "Add a new borrower");

        checkOverduePanel.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("check overdue items");
        checkOverduePanel.add(jLabel10, java.awt.BorderLayout.CENTER);

        cardPanel.add(checkOverduePanel, "Check overdue books");

        addNewBookPanel.setLayout(new java.awt.BorderLayout());

        jLabel20.setText("add new book");
        addNewBookPanel.add(jLabel20, java.awt.BorderLayout.CENTER);

        cardPanel.add(addNewBookPanel, "Add new book");

        addNewCopyPanel.setLayout(new java.awt.BorderLayout());

        jLabel19.setText("new copy");
        addNewCopyPanel.add(jLabel19, java.awt.BorderLayout.CENTER);

        cardPanel.add(addNewCopyPanel, "Add new book copy");

        removeBorrowerPanel.setLayout(new java.awt.BorderLayout());

        jLabel16.setText("remove borrower");
        removeBorrowerPanel.add(jLabel16, java.awt.BorderLayout.CENTER);

        cardPanel.add(removeBorrowerPanel, "Remove borrower");

        removeBookPanel.setLayout(new java.awt.BorderLayout());

        jLabel14.setText("remove books and copies");
        removeBookPanel.add(jLabel14, java.awt.BorderLayout.CENTER);

        cardPanel.add(removeBookPanel, "Remove books and copies");

        popularReportPanel.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("popular books");
        popularReportPanel.add(jLabel15, java.awt.BorderLayout.CENTER);

        cardPanel.add(popularReportPanel, "Popular books report");

        checkedOutReportPanel.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("checked out");
        checkedOutReportPanel.add(jLabel13, java.awt.BorderLayout.CENTER);

        cardPanel.add(checkedOutReportPanel, "Checked-out report");

        mainPanel.add(cardPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        doButton.setText("Do something");
        doButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(doButton);

        clearButton.setText("Clear");
        clearButton.setHideActionText(true);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        navigationMenu.setText("Navigation");

        borrowerMenu.setText("Borrower");

        searchMenuItem.setText(SEARCH);
        searchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(searchMenuItem);

        checkAccountMenuItem.setText(CHECK_ACCOUNT);
        checkAccountMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(checkAccountMenuItem);

        holdRequestMenuItem.setText(HOLD_REQUEST);
        holdRequestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(holdRequestMenuItem);

        payFineMenuItem.setText(PAY_FINE);
        payFineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(payFineMenuItem);

        navigationMenu.add(borrowerMenu);

        clerkMenu.setText("Clerk");

        addBorrowerMenuItem.setText(ADD_BORROWER);
        addBorrowerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(addBorrowerMenuItem);

        checkOutMenuItem.setText(CHECK_OUT);
        checkOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(checkOutMenuItem);

        processReturnMenuItem.setText(PROCESS_RETURN);
        processReturnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(processReturnMenuItem);

        checkOverdueMenuItem.setText(CHECK_OVERDUE);
        checkOverdueMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(checkOverdueMenuItem);

        navigationMenu.add(clerkMenu);

        librarianMenu.setText("Librarian");

        addMenu.setText("Add");

        addBookMenuItem.setText(ADD_BOOK);
        addBookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addBookMenuItem);

        addBookCopyMenuItem.setText(ADD_COPY);
        addBookCopyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addBookCopyMenuItem);

        librarianMenu.add(addMenu);

        removeMenu.setText("Remove");

        removeBorrowerMenuItem.setText(REMOVE_BORROWER);
        removeBorrowerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        removeMenu.add(removeBorrowerMenuItem);

        removeBookMenuItem.setText(REMOVE_BOOK);
        removeBookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        removeMenu.add(removeBookMenuItem);

        librarianMenu.add(removeMenu);

        reportMenu.setText("Report");

        checkedOutReportMenuItem.setText(REPORT_CHECKED_OUT);
        checkedOutReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        reportMenu.add(checkedOutReportMenuItem);

        popularReportMenuItem.setText(REPORT_POPULAR);
        popularReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        reportMenu.add(popularReportMenuItem);

        librarianMenu.add(reportMenu);

        navigationMenu.add(librarianMenu);

        tableMenuItem.setText(TABLES);
        tableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        navigationMenu.add(tableMenuItem);

        startMenuItem.setText(START);
        startMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        navigationMenu.add(startMenuItem);

        menuBar.add(navigationMenu);

        helpMenu.setText("Help");

        manualMenuItem.setText("User manual");
        manualMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(manualMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  /**
   * Opens the default browser to a web site hosting the user manual
   * @param evt                                             
   * reference:
   * http://docs.oracle.com/javase/tutorial/uiswing/misc/desktop.html
   */
  private void manualMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualMenuItemActionPerformed
    //http://docs.oracle.com/javase/tutorial/uiswing/misc/desktop.html
    final String manualUrl = "http://www.ugrad.cs.ubc.ca/~c7e8/";
    try
    {
      Desktop desktop = null;
      if (Desktop.isDesktopSupported())
      {
        desktop = Desktop.getDesktop();
        desktop.browse(new URI(manualUrl));
      }
      else
      {
        throw new IOException();
      }
    }
    catch (IOException ex) {
        String message = "The user manual is located at "+manualUrl;
        JOptionPane.showMessageDialog(this, message, "Manual", JOptionPane.INFORMATION_MESSAGE); 
    }
    catch (URISyntaxException ex) 
    {
      String msg = "URI syntax is incorrect";
      JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
  }//GEN-LAST:event_manualMenuItemActionPerformed

  /**
   * Do this when the window is being closed
   * Takes care of any clean up operations
   * @param evt 
   */
  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if (controller != null)
    {
      controller.shutdown();
    }
  }//GEN-LAST:event_formWindowClosing

  /**
   * Do this when one of the menu items is clicked
   * Displays the panel corresponding to the user's selection
   * @param evt 
   */
  private void navigationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navigationMenuItemActionPerformed
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    String key = ((JMenuItem) evt.getSource()).getText();
    cl.show(cardPanel, key);
    state = statemap.get(key);
    doButton.setText(key);
  }//GEN-LAST:event_navigationMenuItemActionPerformed

  
  /**
   * Chooses an action based on the current state of the GUI
   * Executes the functionality that the state is supposed to embody
   * i.e. In the SEARCH state, this should send a message to the Controller
   * to search for a book
   * @param evt 
   */
  private void doButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doButtonActionPerformed
    //String key = ((JButton) evt.getSource()).getText();
    switch (state)
    {
      case TABLES:
        String[][] table = 
                controller.displayTable((String)tablesComboBox.getSelectedItem());
        break;
      case START:
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        String key = ((String) navigationComboBox.getSelectedItem());
        State state = statemap.get(key);
        cl.show(cardPanel, key);
        this.state = state;
        String buttonText = generateButtonText();
        doButton.setText(buttonText);
        break;
      case SEARCH:
        break;
      case CHECK_ACCOUNT:
        break;
      case HOLD_REQUEST:
        break;
      case PAY_FINE:
        break;
      case ADD_BORROWER:
        break;
      case CHECK_OUT:
        break;
      case CHECK_OVERDUE:
        break;
      case PROCESS_RETURN:
        break;
      case ADD_BOOK:
        break;
      case ADD_COPY:
        break;
      case REMOVE_BOOK:
        break;
      case REMOVE_BORROWER:
        break;
      case REPORT_POPULAR:
        break;
      case REPORT_CHECKED_OUT:
        break;
      default:
    }
  }//GEN-LAST:event_doButtonActionPerformed

  /**
   * Generates text for the doButton based on GUI state.
   * @return 
   */
  private String generateButtonText()
  {
    String buttonText = null;
    switch (this.state)
    {
      case TABLES:
        buttonText = "View";
        break;
      case START:
        buttonText = "Go";
        break;
      case SEARCH:
        buttonText = "Search";
        break;
      case CHECK_ACCOUNT:
        buttonText = "Check account";
        break;
      case HOLD_REQUEST:
        buttonText = "Place request";
        break;
      case PAY_FINE:
        buttonText = "Pay";
        break;
      case ADD_BORROWER:
        buttonText = "Add";
        break;
      case CHECK_OUT:
        buttonText = "Check out";
        break;
      case CHECK_OVERDUE:
        buttonText = "Check";
        break;
      case PROCESS_RETURN:
        buttonText = "Process return";
        break;
      case ADD_BOOK:
      case ADD_COPY:
        buttonText = "Add";
        break;
      case REMOVE_BOOK:
      case REMOVE_BORROWER:
        buttonText = "Remove";
        break;
      case REPORT_POPULAR:
      case REPORT_CHECKED_OUT:
        buttonText = "Generate report";
        break;
      default:
    }
    return buttonText;
  }
  
  
  /**
   * Tells the GUI to clear the current panel
   * @param evt 
   */
  private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
    
    switch (state)
    {
      case TABLES:
        break;
      case START:
        navigationComboBox.setSelectedItem(this.START);
        break;
      case SEARCH:
        break;
      case CHECK_ACCOUNT:
        break;
      case HOLD_REQUEST:
        break;
      case PAY_FINE:
        break;
      case ADD_BORROWER:
        break;
      case CHECK_OUT:
        break;
      case CHECK_OVERDUE:
        break;
      case PROCESS_RETURN:
        break;
      case ADD_BOOK:
        break;
      case ADD_COPY:
        break;
      case REMOVE_BOOK:
        break;
      case REMOVE_BORROWER:
        break;
      case REPORT_POPULAR:
        break;
      case REPORT_CHECKED_OUT:
        break;
      default:
    }
  }//GEN-LAST:event_clearButtonActionPerformed

  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(ViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(ViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(ViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(ViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new ViewFrame().setVisible(true);
      }
    });
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addBookCopyMenuItem;
    private javax.swing.JMenuItem addBookMenuItem;
    private javax.swing.JMenuItem addBorrowerMenuItem;
    private javax.swing.JPanel addBorrowerPanel;
    private javax.swing.JMenu addMenu;
    private javax.swing.JPanel addNewBookPanel;
    private javax.swing.JPanel addNewCopyPanel;
    private javax.swing.JMenu borrowerMenu;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JMenuItem checkAccountMenuItem;
    private javax.swing.JPanel checkAccountPanel;
    private javax.swing.JMenuItem checkOutMenuItem;
    private javax.swing.JPanel checkOutPanel;
    private javax.swing.JMenuItem checkOverdueMenuItem;
    private javax.swing.JPanel checkOverduePanel;
    private javax.swing.JMenuItem checkedOutReportMenuItem;
    private javax.swing.JPanel checkedOutReportPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JMenu clerkMenu;
    private javax.swing.JButton doButton;
    private javax.swing.JTable entitiesTable;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem holdRequestMenuItem;
    private javax.swing.JPanel holdRequestPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu librarianMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem manualMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JComboBox navigationComboBox;
    private javax.swing.JMenu navigationMenu;
    private javax.swing.JMenuItem payFineMenuItem;
    private javax.swing.JPanel payFinePanel;
    private javax.swing.JMenuItem popularReportMenuItem;
    private javax.swing.JPanel popularReportPanel;
    private javax.swing.JMenuItem processReturnMenuItem;
    private javax.swing.JPanel processReturnPanel;
    private javax.swing.JMenuItem removeBookMenuItem;
    private javax.swing.JPanel removeBookPanel;
    private javax.swing.JMenuItem removeBorrowerMenuItem;
    private javax.swing.JPanel removeBorrowerPanel;
    private javax.swing.JMenu removeMenu;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JMenuItem searchMenuItem;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel startComboBoxPanel;
    private javax.swing.JMenuItem startMenuItem;
    private javax.swing.JPanel startPanel;
    private javax.swing.JMenuItem tableMenuItem;
    private javax.swing.JPanel tableNamePanel;
    private javax.swing.JComboBox tablesComboBox;
    private javax.swing.JPanel tablesPanel;
    private javax.swing.JScrollPane viewTablePane;
    // End of variables declaration//GEN-END:variables

    private Controller controller;
    private State state;
    private HashMap<String,State> statemap;
    
    /**
     * The possible states that the GUI can be in.  One state for every panel/
     * functional requirement.  There are 16 states in total.
     */
    private enum State {
        //System functionality
        TABLES, START,
        //Borrower functionality
        SEARCH, CHECK_ACCOUNT, HOLD_REQUEST, PAY_FINE,
        // Clerk functionality
        ADD_BORROWER, CHECK_OUT, CHECK_OVERDUE, PROCESS_RETURN,
        // Librarian functionality
        ADD_BOOK, ADD_COPY, REMOVE_BOOK, REMOVE_BORROWER,
        REPORT_POPULAR, REPORT_CHECKED_OUT
      }
    
    private static final String TABLES = "View tables";
    private static final String START = "Start";
    private static final String SEARCH = "Search for book";
    private static final String CHECK_ACCOUNT = "Check account";
    private static final String HOLD_REQUEST = "Place hold request";
    private static final String PAY_FINE="Pay a fine";
    private static final String ADD_BORROWER="Add a new borrower";
    private static final String CHECK_OUT="Check-out books";
    private static final String CHECK_OVERDUE="Check overdue books";
    private static final String PROCESS_RETURN="Process a return";
    private static final String ADD_BOOK="Add new book";
    private static final String ADD_COPY="Add new book copy";
    private static final String REMOVE_BOOK="Remove books and copies";
    private static final String REMOVE_BORROWER="Remove borrower";
    private static final String REPORT_POPULAR="Popular books report";
    private static final String REPORT_CHECKED_OUT="Checked-out report";
    
    
}
