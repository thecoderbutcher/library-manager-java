/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import librarymanager.Book;
import librarymanager.Files;
import librarymanager.Loan;
import librarymanager.Partner;


/**
 *
 * @author nacho
 */
public class LoanAdd extends javax.swing.JFrame {

    Files bookfile    = new Files("books"),
          partnerfile = new Files("partners");
    List <Book> books; 
    List <Partner> partners;
    /**
     * Creates new form PrestamoWindow
     */
    public LoanAdd(){
        initComponents();
       
        try {
            books = new ArrayList<>(bookfile.readFile("books"));
            partners = new ArrayList<>(partnerfile.readFile("partners"));
            for (int i = 0; i < partners.size(); i++) {
                if(!partners.get(i).getDefaulter()){
                    partnerSelect.addItem(partners.get(i).getName() + " " + partners.get(i).getLastname() + " - " + partners.get(i).getDni());
                }
                
            }
            for (int i = 0; i < books.size(); i++) {
                if(books.get(i).getStatus()){
                    bookSelect.addItem(books.get(i).getTitle() + " - " + books.get(i).getIdBook());
                }
            }
            
        } 
        catch (IOException ex) {
            Logger.getLogger(LoanAdd.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        partnerSelect = new javax.swing.JComboBox<>();
        accept = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        bookSelect = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar prestamo");
        setType(java.awt.Window.Type.UTILITY);

        accept.setText("Aceptar");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        jLabel1.setText("Socios");

        jLabel2.setText("Libros");

        jToggleButton1.setText("Cancelar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(partnerSelect, 0, 440, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(bookSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(accept)
                        .addGap(115, 115, 115)
                        .addComponent(jToggleButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partnerSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accept)
                    .addComponent(jToggleButton1))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        // TODO add your handling code here:
        Loan loan = null;
        Partner partner = null;
        Book book = null;
        
        List <Loan> loans;
        Files loanfile = new Files("loans");
        
        int init = (partnerSelect.getSelectedItem().toString()).indexOf("-") + 2;
        String code = (partnerSelect.getSelectedItem().toString()).substring(init);
        for (int i = 0; i < partners.size(); i++) {
            if(partners.get(i).search(code) != null){
                partner = partners.get(i).search(code);
                partners.get(i).setDefaulter();
                try {
                    partnerfile.writeFile(partners);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoanAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        init = (bookSelect.getSelectedItem().toString()).indexOf("-") + 2;
        code = (bookSelect.getSelectedItem().toString()).substring(init);
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).search(code) != null){
                book = books.get(i).search(code);
                books.get(i).setStatus();
                try {
                    bookfile.writeFile(books);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoanAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            loan = new Loan(partner, book);
            loans = new ArrayList<>(loanfile.readFile("loans"));
            loans.add(loan); 
            loanfile.writeFile(loans); 
            
            this.setVisible(false);
        }
        catch (IOException ex) {
            Logger.getLogger(LoanAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_acceptActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LoanAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoanAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoanAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoanAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoanAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton accept;
    private javax.swing.JComboBox<String> bookSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> partnerSelect;
    // End of variables declaration//GEN-END:variables
}