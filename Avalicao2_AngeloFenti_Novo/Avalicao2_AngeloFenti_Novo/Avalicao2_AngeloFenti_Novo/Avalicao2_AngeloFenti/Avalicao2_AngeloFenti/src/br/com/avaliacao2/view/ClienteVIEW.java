/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avaliacao2.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.avaliacao2.dto.ClienteDTO;
import br.com.avaliacao2.ctr.ClienteCTR;
import br.com.avaliacao2.dao.ConexaoDAO;
/**
 *
 * @author Flavia
 */

public class ClienteVIEW extends javax.swing.JInternalFrame {
    
    ClienteDTO clienteDTO = new ClienteDTO();
    ClienteCTR clienteCTR = new ClienteCTR();

    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_cliente;
    /**
     * Creates new form ClienteVIEW
     */
    public ClienteVIEW() {
        initComponents();
        
        liberaCampos(false);
        liberaBotoes(true, false,false,false,true);
        modelo_jtl_consultar_cliente = (DefaultTableModel) jtl_consultar_cliente.getModel();
        
      
    }
    private void preencherTabela(String nome_cli){
        try{
            modelo_jtl_consultar_cliente.setNumRows(0);
            clienteDTO.setNome_cli(nome_cli);
            rs = clienteCTR.consultarCliente(clienteDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_cliente.addRow(new Object[]{
                    rs.getString("id_cli"),
                    rs.getString("nome_cli"),
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }
        finally{
            clienteCTR.CloseDB();
        }
    }

    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);

}
    
    
    private void gravar(){
            try{
                clienteDTO.setNome_cli(nome_cli.getText());
                clienteDTO.setLogradouro_cli(logradouro_cli.getText());
                clienteDTO.setNumero_cli(Integer.parseInt(numero_cli.getText()));
                clienteDTO.setBairro_cli(bairro_cli.getText());
                clienteDTO.setCidade_cli(cidade_cli.getText());
                clienteDTO.setEstado_cli(estado_cli.getSelectedItem().toString());
                clienteDTO.setCep_cli(cep_cli.getText());
                clienteDTO.setCpf_cli(cpf_cli.getText());
                clienteDTO.setRg_cli(rg_cli.getText());

                JOptionPane.showMessageDialog(null,
                    clienteCTR.inserirCliente(clienteDTO)
                );
            }
            catch(Exception e){
                System.out.println("Erro ao Gravar" + e.getMessage());
            }
    }
    private void liberaCampos(boolean a){
        nome_cli.setEnabled(a);
        logradouro_cli.setEnabled(a);
        numero_cli.setEnabled(a);
        bairro_cli.setEnabled(a);
        cidade_cli.setEnabled(a);
        estado_cli.setEnabled(a);
        cep_cli.setEnabled(a);
        cpf_cli.setEnabled(a);
        rg_cli.setEnabled(a);
    }
    private void limpaCampos(){
        nome_cli.setText("");
        logradouro_cli.setText("");
        numero_cli.setText("");
        bairro_cli.setText("");
        cidade_cli.setText("");
        cep_cli.setText("");
        cpf_cli.setText("");
        rg_cli.setText("");
    }
    private void liberaBotoes(boolean a,boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    private void preencheCampos(int id_cli){
        try{
            clienteDTO.setId_cli(id_cli);
            rs = clienteCTR.consultarCliente(clienteDTO,2);
            if(rs.next()){
                limpaCampos();
                nome_cli.setText(rs.getString("nome_cli"));
                logradouro_cli.setText(rs.getString("logradouro_cli"));
                numero_cli.setText(rs.getString("numero_cli"));
                bairro_cli.setText(rs.getString("bairro_cli"));
                cidade_cli.setText(rs.getString("cidade_Cli"));
                estado_cli.setSelectedItem(rs.getString("estado_cli"));
                cep_cli.setText(rs.getString("cep_cli"));
                cpf_cli.setText(rs.getString("cpf_cli"));
                rg_cli.setText(rs.getString("rg_cli"));
                
                gravar_alterar = 2;
                liberaCampos(true);
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
            
        }
        finally{
            clienteCTR.CloseDB();
        }
    }
    private void alterar(){
    try{
        clienteDTO.setNome_cli(nome_cli.getText());
        clienteDTO.setLogradouro_cli(logradouro_cli.getText());
        clienteDTO.setNumero_cli(Integer.parseInt(numero_cli.getText()));
        clienteDTO.setBairro_cli(bairro_cli.getText());
        clienteDTO.setCidade_cli(cidade_cli.getText());
        clienteDTO.setEstado_cli(estado_cli.getSelectedItem().toString());
        clienteDTO.setCep_cli(cep_cli.getText());
        clienteDTO.setCpf_cli(cpf_cli.getText());
        clienteDTO.setRg_cli(rg_cli.getText());

        JOptionPane.showMessageDialog(null,
            clienteCTR.alterarCliente(clienteDTO)
        );
    }
    catch(Exception e){
        System.out.println("Erro ao Alterar" + e.getMessage());
    }
}
    private void excluir(){
    if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Cliente?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(null, clienteCTR.excluirCliente(clienteDTO));
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

        label_0 = new javax.swing.JLabel();
        label_1 = new javax.swing.JLabel();
        nome_cli = new javax.swing.JTextField();
        label_2 = new javax.swing.JLabel();
        label_3 = new javax.swing.JLabel();
        label_4 = new javax.swing.JLabel();
        logradouro_cli = new javax.swing.JTextField();
        numero_cli = new javax.swing.JTextField();
        cidade_cli = new javax.swing.JTextField();
        cep_cli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bairro_cli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        estado_cli = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cpf_cli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rg_cli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pesquisa_nome_cli = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_cliente = new javax.swing.JTable();

        label_0.setText("Nome:");

        label_1.setText("Logradouro:");

        nome_cli.setNextFocusableComponent(logradouro_cli);
        nome_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_cliActionPerformed(evt);
            }
        });

        label_2.setText("Número:");

        label_3.setText("Cidade:");

        label_4.setText("CEP:");

        logradouro_cli.setNextFocusableComponent(numero_cli);
        logradouro_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logradouro_cliActionPerformed(evt);
            }
        });

        jLabel1.setText("Bairro:");

        jLabel2.setText("Estado:");

        estado_cli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "pB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        estado_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_cliActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF:");

        jLabel4.setText("RG:");

        rg_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rg_cliActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cliente");

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

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setNextFocusableComponent(pesquisa_nome_cli);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Consulta");

        jLabel7.setText("Nome:");

        pesquisa_nome_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_cliActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.setText("OK");
        btnPesquisar.setNextFocusableComponent(nome_cli);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jtl_consultar_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_clienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_cliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_3)
                            .addComponent(label_4)
                            .addComponent(label_2)
                            .addComponent(label_1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_0)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cidade_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(estado_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(321, 321, 321)
                        .addComponent(btnExcluir)
                        .addGap(47, 47, 47)
                        .addComponent(btnSair))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logradouro_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numero_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(bairro_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cep_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)
                                .addGap(15, 15, 15)
                                .addComponent(cpf_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(rg_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btnSalvar)
                                .addGap(35, 35, 35)
                                .addComponent(btnCancelar)))))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(24, 24, 24)
                        .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnPesquisar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_0))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(logradouro_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_1))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(numero_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addComponent(bairro_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cidade_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_3))
                            .addComponent(jLabel2)
                            .addComponent(estado_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cep_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_4))
                            .addComponent(cpf_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rg_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNovo)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancelar)
                                .addComponent(btnSalvar))
                            .addComponent(btnExcluir)
                            .addComponent(btnSair)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_cliActionPerformed

    private void logradouro_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logradouro_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logradouro_cliActionPerformed

    private void estado_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_cliActionPerformed

    private void rg_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rg_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rg_cliActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
                liberaBotoes(false,true,true,false,true);
                gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(gravar_alterar==1){
            gravar();
            gravar_alterar=0;
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
        liberaBotoes(true,false,false,false,true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void pesquisa_nome_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_cliActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        // TODO add your handling code here:
        preencherTabela(pesquisa_nome_cli.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_clienteMouseClicked
        // TODO add your handling code here:
        preencheCampos(Integer.parseInt(String.valueOf(
        jtl_consultar_cliente.getValueAt(
        jtl_consultar_cliente.getSelectedRow(), 0))));
        liberaBotoes(false,true,true,true,true);
    }//GEN-LAST:event_jtl_consultar_clienteMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cliente.setNumRows(0);
        liberaBotoes(true, false,false,false,true);
        gravar_alterar=0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_cliente.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_cli;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cep_cli;
    private javax.swing.JTextField cidade_cli;
    private javax.swing.JTextField cpf_cli;
    private javax.swing.JComboBox<String> estado_cli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_cliente;
    private javax.swing.JLabel label_0;
    private javax.swing.JLabel label_1;
    private javax.swing.JLabel label_2;
    private javax.swing.JLabel label_3;
    private javax.swing.JLabel label_4;
    private javax.swing.JTextField logradouro_cli;
    private javax.swing.JTextField nome_cli;
    private javax.swing.JTextField numero_cli;
    private javax.swing.JTextField pesquisa_nome_cli;
    private javax.swing.JTextField rg_cli;
    // End of variables declaration//GEN-END:variables
}
