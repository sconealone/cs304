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
    initComponents();
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        welcomePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tablesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        welcomeMenuItem = new javax.swing.JMenuItem();
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

        cardPanel.setLayout(new java.awt.CardLayout());

        welcomePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("welcome");
        welcomePanel.add(jLabel6, java.awt.BorderLayout.CENTER);

        cardPanel.add(welcomePanel, "Welcome page");

        tablesPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("tables");
        tablesPanel.add(jLabel1, java.awt.BorderLayout.CENTER);

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

        getContentPane().add(cardPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        navigationMenu.setText("Navigation");

        borrowerMenu.setText("Borrower");

        searchMenuItem.setText("Search for book");
        searchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(searchMenuItem);

        checkAccountMenuItem.setText("Check account");
        checkAccountMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(checkAccountMenuItem);

        holdRequestMenuItem.setText("Place hold request");
        holdRequestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(holdRequestMenuItem);

        payFineMenuItem.setText("Pay a fine");
        payFineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        borrowerMenu.add(payFineMenuItem);

        navigationMenu.add(borrowerMenu);

        clerkMenu.setText("Clerk");

        addBorrowerMenuItem.setText("Add a new borrower");
        addBorrowerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(addBorrowerMenuItem);

        checkOutMenuItem.setText("Check-out books");
        checkOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(checkOutMenuItem);

        processReturnMenuItem.setText("Process a return");
        processReturnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(processReturnMenuItem);

        checkOverdueMenuItem.setText("Check overdue books");
        checkOverdueMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        clerkMenu.add(checkOverdueMenuItem);

        navigationMenu.add(clerkMenu);

        librarianMenu.setText("Librarian");

        addMenu.setText("Add");

        addBookMenuItem.setText("Add new book");
        addBookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addBookMenuItem);

        addBookCopyMenuItem.setText("Add new book copy");
        addBookCopyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addBookCopyMenuItem);

        librarianMenu.add(addMenu);

        removeMenu.setText("Remove");

        removeBorrowerMenuItem.setText("Remove borrower");
        removeBorrowerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        removeMenu.add(removeBorrowerMenuItem);

        removeBookMenuItem.setText("Remove books and copies");
        removeBookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        removeMenu.add(removeBookMenuItem);

        librarianMenu.add(removeMenu);

        reportMenu.setText("Report");

        checkedOutReportMenuItem.setText("Checked-out report");
        checkedOutReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        reportMenu.add(checkedOutReportMenuItem);

        popularReportMenuItem.setText("Popular books");
        popularReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        reportMenu.add(popularReportMenuItem);

        librarianMenu.add(reportMenu);

        navigationMenu.add(librarianMenu);

        tableMenuItem.setText("View tables");
        tableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        navigationMenu.add(tableMenuItem);

        welcomeMenuItem.setText("Welcome page");
        welcomeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigationMenuItemActionPerformed(evt);
            }
        });
        navigationMenu.add(welcomeMenuItem);

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
    cl.show(cardPanel, ((JMenuItem) evt.getSource()).getText());
  }//GEN-LAST:event_navigationMenuItemActionPerformed

  /**
   * Displays the search panel, where the borrower can search for books
   * @param evt 
   */
  /**
   * Displays the 
   * @param evt 
   */
  /**
   * Test
   * TODO delete this
   * @param evt 
   */  
  
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
    private javax.swing.JMenu borrowerMenu;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JMenuItem checkAccountMenuItem;
    private javax.swing.JPanel checkAccountPanel;
    private javax.swing.JMenuItem checkOutMenuItem;
    private javax.swing.JPanel checkOutPanel;
    private javax.swing.JMenuItem checkOverdueMenuItem;
    private javax.swing.JPanel checkOverduePanel;
    private javax.swing.JMenuItem checkedOutReportMenuItem;
    private javax.swing.JMenu clerkMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem holdRequestMenuItem;
    private javax.swing.JPanel holdRequestPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu librarianMenu;
    private javax.swing.JMenuItem manualMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu navigationMenu;
    private javax.swing.JMenuItem payFineMenuItem;
    private javax.swing.JPanel payFinePanel;
    private javax.swing.JMenuItem popularReportMenuItem;
    private javax.swing.JMenuItem processReturnMenuItem;
    private javax.swing.JPanel processReturnPanel;
    private javax.swing.JMenuItem removeBookMenuItem;
    private javax.swing.JMenuItem removeBorrowerMenuItem;
    private javax.swing.JMenu removeMenu;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JMenuItem searchMenuItem;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JMenuItem tableMenuItem;
    private javax.swing.JPanel tablesPanel;
    private javax.swing.JMenuItem welcomeMenuItem;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables

    private Controller controller;
}
