/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avaliacao2.view;

/**
 *
 * @author Flavia
 */

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.avaliacao2.dto.FuncionarioDTO;
import br.com.avaliacao2.ctr.FuncionarioCTR;

public class FuncionarioVIEW extends javax.swing.JInternalFrame {
    
    FuncionarioDTO funcionarioDTO = new FuncionarioDTO(); //Cria um objeto funcionarioDTO
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR(); //Cria um objeto funcionarioCTR

    int gravar_alterar; //Variável usada para saber se esta alterando ou incluindo

    ResultSet rs; //Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_funcionario;
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar(){
        try{
            funcionarioDTO.setNome_fun(nome_fun.getText());
            funcionarioDTO.setCpf_fun(cpf_fun.getText());
            funcionarioDTO.setLogin_fun(login_fun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            funcionarioDTO.setTipo_fun(tipo_fun.getSelectedItem().toString());

            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.inserirFuncionario(funcionarioDTO)
            );
        }
        catch (Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }
    private void alterar(){
        try{
            funcionarioDTO.setNome_fun(nome_fun.getText());
            funcionarioDTO.setCpf_fun(cpf_fun.getText());
            funcionarioDTO.setLogin_fun(login_fun.getText());
            funcionarioDTO.setTipo_fun(tipo_fun.getSelectedItem().toString());

            if((checkAlterarSenha.isSelected()) && (senha_fun.getPassword().length != 0)){
                funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            }
            else{
                funcionarioDTO.setSenha_fun(null);
            }
            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.alterarFuncionario(funcionarioDTO)
            );
        }
        catch (Exception e){
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Funcionário?","Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.excluirFuncionario(funcionarioDTO)
            );
        }
    }
    private void liberaCampos(boolean a){
        nome_fun.setEnabled(a);
        cpf_fun.setEnabled(a);
        login_fun.setEnabled(a);
        tipo_fun.setEnabled(a);
        senha_fun.setEnabled(a);
        checkAlterarSenha.setEnabled(a);
    }
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    private void limpaCampos(){
        nome_fun.setText("");
        cpf_fun.setText("");
        login_fun.setText("");
        senha_fun.setText("");
        checkAlterarSenha.setSelected(false);
    }
    private void preencheTabela(String nome_fun){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_funcionario.setNumRows(0);
            //Enquanto tiver linhas - faça
            funcionarioDTO.setNome_fun(nome_fun);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 1); //1 é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_funcionario.addRow(new Object[]{
                    rs.getString("id_fun"),
                    rs.getString("nome_fun")
                });
            }
        }
        catch (Exception e){
            System.out.println("Erro SQL: " +e);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }
    private void preencheCampos(int id_fun){
        try{
            funcionarioDTO.setId_fun(id_fun);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 2);
              if(rs.next()){
                limpaCampos();

                nome_fun.setText(rs.getString("nome_fun"));
                cpf_fun.setText(rs.getString("cpf_fun"));
                login_fun.setText(rs.getString("login_fun"));
                tipo_fun.setSelectedItem(rs.getString("tipo_fun"));

                gravar_alterar = 2;
                liberaCampos(true);
                senha_fun.setEnabled(false);
                checkAlterarSenha.setSelected(false);
            }//fecha if(rs.next)
        }
        catch (Exception e){
            System.out.println("Erro SQL: " +e);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }

    /**
     * Creates new form FuncionarioVIEW
     */
    public FuncionarioVIEW() {
        initComponents();
        
        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario = (DefaultTableModel) jtl_consultar_funcionario.getModel();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome_fun = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cpf_fun = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        login_fun = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        senha_fun = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        tipo_fun = new javax.swing.JComboBox<>();
        checkAlterarSenha = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_funcionario = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        pesquisa_nome_fun = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Funcionario");

        jLabel2.setText("Nome:");

        nome_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_funActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF:");

        cpf_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpf_funActionPerformed(evt);
            }
        });

        jLabel4.setText("Login:");

        jLabel5.setText("Senha:");

        jLabel6.setText("Tipo:");

        tipo_fun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMUM", "ADMINISTRADOR" }));

        checkAlterarSenha.setText("Alterar Senha");
        checkAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkAlterarSenhaMouseClicked(evt);
            }
        });
        checkAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAlterarSenhaActionPerformed(evt);
            }
        });

        jtl_consultar_funcionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_funcionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_funcionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_funcionario);

        jLabel7.setText("Nome:");

        pesquisa_nome_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_funActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Consulta");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(senha_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkAlterarSenha))
                                    .addComponent(login_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cpf_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tipo_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pesquisa_nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPesquisar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnNovo)
                                .addGap(132, 132, 132)
                                .addComponent(btnSalvar)
                                .addGap(131, 131, 131)
                                .addComponent(btnCancelar)
                                .addGap(136, 136, 136)
                                .addComponent(btnExcluir)
                                .addGap(139, 139, 139)
                                .addComponent(btnSair)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cpf_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(login_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(senha_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkAlterarSenha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(tipo_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(btnPesquisar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSair)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar)
                        .addComponent(btnNovo)
                        .addComponent(btnExcluir)
                        .addComponent(btnSalvar)))
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_funActionPerformed

    private void cpf_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpf_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpf_funActionPerformed

    private void checkAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAlterarSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkAlterarSenhaActionPerformed

    private void pesquisa_nome_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_funActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        checkAlterarSenha.setEnabled(false);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        preencheTabela(pesquisa_nome_fun.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_funcionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_funcionarioMouseClicked
        // TODO add your handling code here:
        //Pega o id do cliente selecionado e chama preencheCampos
        preencheCampos(Integer.parseInt(String.valueOf(
            jtl_consultar_funcionario.getValueAt(
                jtl_consultar_funcionario.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_funcionarioMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_funcionario.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void checkAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAlterarSenhaMouseClicked
        // TODO add your handling code here:
        if(checkAlterarSenha.isSelected()){
            senha_fun.setEnabled(true);
        }
        else{
            senha_fun.setEnabled(false);
            senha_fun.setText(null);
        }
    }//GEN-LAST:event_checkAlterarSenhaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox checkAlterarSenha;
    private javax.swing.JTextField cpf_fun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_funcionario;
    private javax.swing.JTextField login_fun;
    private javax.swing.JTextField nome_fun;
    private javax.swing.JTextField pesquisa_nome_fun;
    private javax.swing.JPasswordField senha_fun;
    private javax.swing.JComboBox<String> tipo_fun;
    // End of variables declaration//GEN-END:variables
}
