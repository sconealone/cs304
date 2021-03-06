/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PlaceHoldRequestPanel.java
 *
 * Created on 26-Nov-2011, 11:39:59 PM
 */
package GUI;

import javax.swing.JOptionPane;

/**
 *
 * @author Mitch
 */
public class PlaceHoldRequestPanel extends javax.swing.JPanel {

  /** Creates new form PlaceHoldRequestPanel */
  public PlaceHoldRequestPanel() {
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
        java.awt.GridBagConstraints gridBagConstraints;

        callNumberPanel = new javax.swing.JPanel();
        callNumberLabel = new javax.swing.JLabel();
        primaryNumber = new javax.swing.JTextField();
        secondaryNumber = new javax.swing.JTextField();
        year = new javax.swing.JTextField();
        bidField = new javax.swing.JTextField();
        bidLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Request book"));
        setLayout(new java.awt.BorderLayout());

        callNumberPanel.setLayout(new java.awt.GridBagLayout());

        callNumberLabel.setText("Call number: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNumberPanel.add(callNumberLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNumberPanel.add(primaryNumber, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNumberPanel.add(secondaryNumber, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNumberPanel.add(year, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNumberPanel.add(bidField, gridBagConstraints);

        bidLabel.setText("Account ID: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNumberPanel.add(bidLabel, gridBagConstraints);

        add(callNumberPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bidField;
    private javax.swing.JLabel bidLabel;
    private javax.swing.JLabel callNumberLabel;
    private javax.swing.JPanel callNumberPanel;
    private javax.swing.JTextField primaryNumber;
    private javax.swing.JTextField secondaryNumber;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets the call number from the text field box
     * @return 
     */
    public String getCallNumber()
    {
      String callNumber = primaryNumber.getText().trim().toUpperCase();
      callNumber += ' ';
      callNumber += secondaryNumber.getText().trim().toUpperCase();
      callNumber += ' ';
      callNumber += year.getText().trim().toUpperCase();
      return callNumber;
      
    }
    
    /**
     * Clears the textfields
     */
    public void clear()
    {
      primaryNumber.setText("");
      secondaryNumber.setText("");
      year.setText("");
      bidField.setText("");
    }
    
    /**
     * Gets the bid from the text field box
     * @return
     * @throws NumberFormatException 
     */
    public int getBid() throws NumberFormatException
    {
      int bid = Integer.parseInt(bidField.getText().trim());
      if (bid <= 0)
      {
        throw new NumberFormatException("The account ID " + bid + " does not exist.");
      }
      return bid;
    }
    /*
    // move to viewframe
    private void doButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
      try
      {
        controller.getSystemBorrower.placeHoldRequest(placeHoldRequestPanel.getCallNumber());
      }
      catch (SQLException e)
      {
        String holdRequestErrorMessage = "Could not place hold request at this time. Please try again later.\n"
                + e.getMessage();
        JOptionPane.showMessageDialog(this, holdRequestErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
     * 
     */

}

