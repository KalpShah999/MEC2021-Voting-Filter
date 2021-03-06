import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserInterface extends JFrame {

    static UserInterface profile = new UserInterface();
    
    private ArrayList<String> initialFirstNames = new ArrayList();
    private ArrayList<String> initialLastNames = new ArrayList();
    private ArrayList<String> initialPartyNames = new ArrayList();
    
    private ArrayList<String> firstName = new ArrayList();
    private ArrayList<String> lastName = new ArrayList();
    private ArrayList<String> partyName = new ArrayList();
    private ArrayList<String> votersList = new ArrayList();
    
    private ArrayList<Integer> partyCounts = new ArrayList();
    private ArrayList<String> partyNamesList = new ArrayList();
    
    private void ReadData() {
        //https://docs.oracle.com/javase/tutorial/essential/io/index.html
        String userDirectory = System.getProperty("user.dir");
        Path file = Paths.get(userDirectory + "/data/MEC_Competition_Voting_Data.csv");
        
        try (InputStream in = Files.newInputStream(file);
                BufferedReader reader
                = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals("First Name,Last Name,Vote")) {
                    String[] Vote = line.split(",");
                    initialFirstNames.add(Vote[0]);
                    initialLastNames.add(Vote[1]);
                    String newLine = line.replace(Vote[0] + "," + Vote[1] + ",", "");
                    initialPartyNames.add(newLine);
                }
                line = reader.readLine();
            }
        } catch (IOException x) {
            jTextArea2.setText("~Error: Voters Database Not Found~\n");
            System.err.println(x);
        }
        for (int i = 0; i < initialFirstNames.size(); i++) {
            profile.AddVote(initialFirstNames.get(i), initialLastNames.get(i), initialPartyNames.get(i), i);
        }
        profile.UpdateTexts(false);
    }
    
    private void UpdateTexts(boolean wipe) {
        if (wipe) {
            jTextArea2.setText("");
        }
        jTextArea1.setText("");
        
        jLabel2.setText("Filtered List of Voters");
        for (String vote : votersList) {
            jTextArea2.append(vote);
        }
        
        for (String vote : partyNamesList) {
            jTextArea1.append(vote + ": " + partyCounts.get(partyNamesList.indexOf(vote)) + "\n");
        }
    }  
    
    private boolean AddVote(String newName, String newSurName, String newPartyName, int index) {
        boolean temp1 = false;
        if (partyName.contains(",")) {
            temp1 = true;
        } else {
            for (int i = 0; i < initialFirstNames.size(); i++) {
                if (i != index) {
                    if (newName.equals(initialFirstNames.get(i)) && newSurName.equals(initialLastNames.get(i))) {
                        temp1 = true;
                        break;
                    }
                }
            }
        }
        
        if (!temp1) {
            boolean temp2 = false;
            if (!partyNamesList.isEmpty()) {
                for (String party : partyNamesList) {
                    if (newPartyName.equals(party)) {
                        temp2 = true;
                    }
                }
            }

            if (!temp2) {
                partyNamesList.add(newPartyName);
                partyCounts.add(0);
            }
            partyCounts.set(partyNamesList.indexOf(newPartyName), partyCounts.get(partyNamesList.indexOf(newPartyName)) + 1);
            firstName.add(newName);
            lastName.add(newSurName);
            partyName.add(newPartyName);
            initialFirstNames.add(newName);
            initialLastNames.add(newSurName);
            initialPartyNames.add(newPartyName);
            votersList.add((votersList.size() + 1) + ". " + newName + " " + newSurName + " (" + newPartyName + ")\n");
        } else {
            return false;
        }
        return true;
    }
    
    public UserInterface() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        label1 = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jAddButton = new javax.swing.JButton();
        jAddTextField1 = new javax.swing.JTextField();
        jAddText1 = new javax.swing.JLabel();
        jAddText2 = new javax.swing.JLabel();
        jAddTextField2 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jAddText3 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jCreateVoteText = new javax.swing.JLabel();
        jErrorMessageText = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton4 = new javax.swing.JRadioButton();
        jTextFieldOther = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        label1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        label1.setText("MEC Elections");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Filtered List of Voters (Total Number of Votes: 0)");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Current Elections Standings:");

        jAddButton.setText("Add Vote");
        jAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddButtonActionPerformed(evt);
            }
        });

        jAddText1.setText("First Name:");

        jAddText2.setText("Last Name:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Pineapple Pizza Party");

        jAddText3.setText("Choose Election Party:");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Socks and Crocs Reform League");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Pronounced Jiff Union");

        jCreateVoteText.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jCreateVoteText.setText("Create New Vote:");

        jErrorMessageText.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Other");
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });

        jTextFieldOther.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jAddText1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jAddTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jAddText2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jAddTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jAddText3)
                            .addComponent(jCreateVoteText)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton3)
                                            .addComponent(jRadioButton1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jRadioButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldOther, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jAddButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jErrorMessageText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCreateVoteText)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAddTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddText1)
                    .addComponent(jAddText2)
                    .addComponent(jAddTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAddText3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jTextFieldOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAddButton)
                    .addComponent(jErrorMessageText, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("title1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddButtonActionPerformed
        String name = jAddTextField1.getText().replaceAll("\\s","");
        String surName = jAddTextField2.getText().replaceAll("\\s","");
        String party = "";
        if (jRadioButton1.isSelected()) {
            party = "Pineapple Pizza Party";
        } else if (jRadioButton2.isSelected()) {
            party = "Socks and Crocs Reform League";
        } else if (jRadioButton3.isSelected()) {
            party = "Pronounced Jiff Union";
        } else if (jRadioButton4.isSelected()) {
            party = jTextFieldOther.getText();
        }
        
        boolean temp = false;
        if (party.replaceAll("\\s","").equals("")) {
            temp = true;
        } 
        
        if (!name.equals("") && !surName.equals("") && !party.equals("") && !temp) {
            temp = profile.AddVote(name, surName, party, -1);
            if (temp) {
                jErrorMessageText.setText("(Vote added successfully)");
                profile.UpdateTexts(true);
            } else {
                jErrorMessageText.setText("(Error - vote could not be added)");
            }
        } else {
            jErrorMessageText.setText("(Error - vote could not be added)");
        }
    }//GEN-LAST:event_jAddButtonActionPerformed

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
        if (jRadioButton4.isSelected()) {
            jTextFieldOther.setEnabled(true);
        } else {
            jTextFieldOther.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

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
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                profile.ReadData();
                profile.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jAddButton;
    private javax.swing.JLabel jAddText1;
    private javax.swing.JLabel jAddText2;
    private javax.swing.JLabel jAddText3;
    private javax.swing.JTextField jAddTextField1;
    private javax.swing.JTextField jAddTextField2;
    private javax.swing.JLabel jCreateVoteText;
    private javax.swing.JLabel jErrorMessageText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextFieldOther;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
