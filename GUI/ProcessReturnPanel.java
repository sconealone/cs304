/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProcessReturnPanel.java
 *
 * Created on 27-Nov-2011, 8:58:44 PM
 */
package GUI;

import javax.swing.JTextArea;

/**
 *
 * @author Mitch
 */
public class ProcessReturnPanel extends javax.swing.JPanel {

  /** Creates new form ProcessReturnPanel */
  public ProcessReturnPanel() {
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

        callNoPanel = new javax.swing.JPanel();
        callNoLabel = new javax.swing.JLabel();
        pNumber = new javax.swing.JTextField();
        sNumber = new javax.swing.JTextField();
        year = new javax.swing.JTextField();
        copyNoLabel = new javax.swing.JLabel();
        copyNo = new javax.swing.JTextField();
        logPanel = new javax.swing.JPanel();
        logScrollPane = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        callNoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Catalog number"));
        callNoPanel.setLayout(new java.awt.GridBagLayout());

        callNoLabel.setText("Call number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNoPanel.add(callNoLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNoPanel.add(pNumber, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNoPanel.add(sNumber, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNoPanel.add(year, gridBagConstraints);

        copyNoLabel.setText("Copy number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNoPanel.add(copyNoLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        callNoPanel.add(copyNo, gridBagConstraints);

        add(callNoPanel, java.awt.BorderLayout.PAGE_START);

        logPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Return log"));
        logPanel.setLayout(new java.awt.BorderLayout());

        logArea.setColumns(20);
        logArea.setEditable(false);
        logArea.setRows(5);
        logScrollPane.setViewportView(logArea);

        logPanel.add(logScrollPane, java.awt.BorderLayout.CENTER);

        add(logPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel callNoLabel;
    private javax.swing.JPanel callNoPanel;
    private javax.swing.JTextField copyNo;
    private javax.swing.JLabel copyNoLabel;
    private javax.swing.JTextArea logArea;
    private javax.swing.JPanel logPanel;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JTextField pNumber;
    private javax.swing.JTextField sNumber;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables

    public String getCallNumber()
    {
      return pNumber.getText().trim().toUpperCase() + ' ' 
              + sNumber.getText().trim().toUpperCase() + ' '
              + year.getText().trim().toUpperCase();
    }
    
    public String getCopyNo()
    {
      return copyNo.getText().trim().toUpperCase();
    }
    
    public void clear()
    {
      pNumber.setText("");
      sNumber.setText("");
      year.setText("");
      copyNo.setText("");
      logArea.setText("");
    }
    
    public void clearCatalogNumber()
    {
      pNumber.setText("");
      sNumber.setText("");
      year.setText("");
      copyNo.setText("");
      
    }
    
    public void append(String str)
    {
      logArea.append(str);
      logArea.append("\n");
    }
}
