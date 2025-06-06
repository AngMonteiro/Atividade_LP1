/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avaliacao2.view;

/**
 *
 * @author Flavia
 */import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.avaliacao2.dto.VendaDTO;
import br.com.avaliacao2.ctr.VendaCTR;
import br.com.avaliacao2.dto.CarroDTO;
import br.com.avaliacao2.ctr.CarroCTR;
import br.com.avaliacao2.dto.ClienteDTO;
import br.com.avaliacao2.ctr.ClienteCTR;
import java.util.Date;


public class VendaVIEW extends javax.swing.JInternalFrame {

    VendaCTR vendaCTR = new VendaCTR(); 
    VendaDTO vendaDTO = new VendaDTO(); 
    CarroCTR carroCTR = new CarroCTR(); 
    CarroDTO carroDTO = new CarroDTO(); 
    ClienteDTO clienteDTO = new ClienteDTO(); 
    ClienteCTR clienteCTR = new ClienteCTR(); 

    ResultSet rs; 
    DefaultTableModel modelo_jtl_consultar_cli;
    DefaultTableModel modelo_jtl_consultar_carro;
    DefaultTableModel modelo_jtl_consultar_carro_selecionado;
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    public VendaVIEW() {
        initComponents();
        liberaCampos(false);
        liberaBotoes(true, false, false, true);
        modelo_jtl_consultar_cli = (DefaultTableModel) jtl_consultar_cli.getModel();
        modelo_jtl_consultar_carro = (DefaultTableModel) jtl_consultar_carro.getModel();
        modelo_jtl_consultar_carro_selecionado = (DefaultTableModel) jtl_consultar_carro_selecionado.getModel();
    }
    private void gravar () {
        vendaDTO.setDat_vend(new Date());
        vendaDTO.setVal_vend(Double.parseDouble(TotalVenda.getText()));
        clienteDTO.setId_cli(Integer.parseInt(String.valueOf(
                jtl_consultar_cli.getValueAt(jtl_consultar_cli.getSelectedRow(), 0))));

        JOptionPane.showMessageDialog(null,
                vendaCTR.inserirVenda(vendaDTO, clienteDTO, jtl_consultar_carro_selecionado));
    } 
    private void preencheTabelaCliente(String nome_cliente) {
        try {
            // Limpa todas as linhas
            modelo_jtl_consultar_cli.setNumRows(0);
            clienteDTO.setNome_cli(nome_cliente);
            rs = clienteCTR.consultarCliente(clienteDTO, 1);
            // Enquanto tiver linhas - faça
            while (rs.next()) {
                modelo_jtl_consultar_cli.addRow(new Object[]{
                        rs.getString("id_cli"),
                        rs.getString("nome_cli")
                });
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    } 
    private void preencheTabelaCarro(String marca) {
        try {
            // Limpa todas as linhas
            modelo_jtl_consultar_carro.setNumRows(0);
            carroDTO.setMarca(marca);
            rs = carroCTR.consultarCarro(carroDTO, 1);
            // Enquanto tiver linhas - faça
            while (rs.next()) {
                modelo_jtl_consultar_carro.addRow(new Object[]{
                        rs.getString("id_carro"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("preco")
                });
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }
    private void adicionaCarroSelecionado(int id_carro, String marca,String modelo, double preco) {
        try {
            modelo_jtl_consultar_carro_selecionado.addRow(new Object[]{
                    id_carro,
                    marca,
                    modelo,
                    preco
            });
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    } 
    private void removeCarroSelecionado(int linha_selecionada) {
        try {
            if (linha_selecionada >= 0) {
                modelo_jtl_consultar_carro_selecionado.removeRow(linha_selecionada);
                calculaTotalVenda();
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }
    private void calculaTotalVenda () {
        try {
            double total = 0;
            for (int cont = 0; cont < jtl_consultar_carro_selecionado.getRowCount(); cont++) {
                total += (Double.parseDouble(String.valueOf(
                        jtl_consultar_carro_selecionado.getValueAt(cont, 3))) *
                          Integer.parseInt(String.valueOf(
                                  jtl_consultar_carro_selecionado.getValueAt(cont, 4))));
            }
            TotalVenda.setText(String.valueOf(total));
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }
    private void liberaCampos (boolean a) {
        pesquisa_nome_cli.setEnabled(a);
        btnPesquisarCli.setEnabled(a);
        jtl_consultar_cli.setEnabled(a);
        pesquisa_nome_carro.setEnabled(a);
        btnPesquisarCarro.setEnabled(a);
        jtl_consultar_carro.setEnabled(a);
        btnCarroAdd.setEnabled(a);
        btnCarroRem.setEnabled(a);
        jtl_consultar_carro_selecionado.setEnabled(a);
        TotalVenda.setText("0.00");
    } 
    private void limpaCampos () {
        pesquisa_nome_cli.setText("");
        modelo_jtl_consultar_cli.setNumRows(0);
        pesquisa_nome_carro.setText("");
        modelo_jtl_consultar_carro.setNumRows(0);
        modelo_jtl_consultar_carro_selecionado.setNumRows(0);
    }
    private void liberaBotoes ( boolean a , boolean b , boolean c , boolean d ) {
        btnNovo.setEnabled ( a ) ;
        btnSalvar.setEnabled ( b ) ;
        btnCancelar.setEnabled ( c ) ;
        btnSair.setEnabled ( d ) ;
    } 
    private boolean verificaPreenchimento () {
        if (jtl_consultar_cli.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um Cliente!");
            jtl_consultar_cli.requestFocus();
            return false;
        } else {
            if (jtl_consultar_carro_selecionado.getRowCount() < 1) {
                JOptionPane.showMessageDialog(null, "É necessário adicionar pelo menos um carro ao pedido!");
                jtl_consultar_carro_selecionado.requestFocus();
                return false;
            } else {
                int verifica = 0;
                for (int cont = 0; cont < jtl_consultar_carro_selecionado.getRowCount(); cont++) {
                    if (String.valueOf(jtl_consultar_carro_selecionado.getValueAt(
                            cont, 3)).equalsIgnoreCase("null")) {
                        verifica++;
                    }
                }
                if (verifica > 0) {
                    JOptionPane.showMessageDialog(null,
                            "A quantidade de cada carro vendido deve ser informado");
                    jtl_consultar_carro_selecionado.requestFocus();
                    return false;
                }
                else{
                    return true;
                }
            }
        }
    }    
    /**
     * Creates new form VendaVIEW
     */
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pesquisa_nome_cli = new javax.swing.JTextField();
        btnPesquisarCli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_cli = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        TotalVenda = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_carro_selecionado = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_carro = new javax.swing.JTable();
        pesquisa_nome_carro = new javax.swing.JTextField();
        btnPesquisarCarro = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCarroRem = new javax.swing.JButton();
        btnCarroAdd = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Dados");

        jLabel2.setText("Cliente:");

        pesquisa_nome_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_cliActionPerformed(evt);
            }
        });

        btnPesquisarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCliActionPerformed(evt);
            }
        });

        jtl_consultar_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ));
        jScrollPane1.setViewportView(jtl_consultar_cli);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Total Venda:");

        TotalVenda.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TotalVenda.setForeground(new java.awt.Color(51, 255, 51));
        TotalVenda.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addComponent(btnPesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43)
                        .addComponent(TotalVenda)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TotalVenda))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Vendas");

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

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtl_consultar_carro_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Marca", "Modelo", "Valor", "QTD"
            }
        ));
        jtl_consultar_carro_selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_carro_selecionadoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_carro_selecionado);

        jtl_consultar_carro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Marca", "Modelo", "Valor"
            }
        ));
        jScrollPane2.setViewportView(jtl_consultar_carro);

        pesquisa_nome_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_carroActionPerformed(evt);
            }
        });

        btnPesquisarCarro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCarroActionPerformed(evt);
            }
        });

        jLabel6.setText("Descrição:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Carros");

        btnCarroRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/prod_rem.png"))); // NOI18N
        btnCarroRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarroRemActionPerformed(evt);
            }
        });

        btnCarroAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/prod_add.png"))); // NOI18N
        btnCarroAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarroAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pesquisa_nome_carro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPesquisarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(btnCarroRem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnCarroAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPesquisarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(pesquisa_nome_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCarroRem)
                    .addComponent(btnCarroAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(btnNovo)
                        .addGap(103, 103, 103)
                        .addComponent(btnSalvar)
                        .addGap(104, 104, 104)
                        .addComponent(btnCancelar)
                        .addGap(138, 138, 138)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar)
                        .addComponent(btnSair)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisa_nome_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_cliActionPerformed

    private void pesquisa_nome_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_carroActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(verificaPreenchimento()){
            gravar();
            limpaCampos();
            liberaCampos(false);
            liberaBotoes(true, false, false, true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        liberaBotoes(false, true, true, true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cli.setNumRows(0);
        modelo_jtl_consultar_carro.setNumRows(0);
        liberaBotoes(true, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPesquisarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCliActionPerformed
        // TODO add your handling code here:
        preencheTabelaCliente(pesquisa_nome_cli.getText());
    }//GEN-LAST:event_btnPesquisarCliActionPerformed

    private void btnPesquisarCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCarroActionPerformed
        // TODO add your handling code here:
        preencheTabelaCarro(pesquisa_nome_carro.getText());
    }//GEN-LAST:event_btnPesquisarCarroActionPerformed

    private void jtl_consultar_carro_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_carro_selecionadoKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            calculaTotalVenda();
        }
    }//GEN-LAST:event_jtl_consultar_carro_selecionadoKeyReleased

    private void btnCarroRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarroRemActionPerformed
        // TODO add your handling code here:
        removeCarroSelecionado(jtl_consultar_carro_selecionado.getSelectedRow());
    }//GEN-LAST:event_btnCarroRemActionPerformed

    private void btnCarroAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarroAddActionPerformed
        // TODO add your handling code here:
                adicionaCarroSelecionado(
                Integer.parseInt(String.valueOf(jtl_consultar_carro.getValueAt(
                    jtl_consultar_carro.getSelectedRow(), 0))),
                String.valueOf(jtl_consultar_carro.getValueAt(jtl_consultar_carro.getSelectedRow(), 1)),
                String.valueOf(jtl_consultar_carro.getValueAt(jtl_consultar_carro.getSelectedRow(), 2)),
                Double.parseDouble(String.valueOf(jtl_consultar_carro.getValueAt(
                    jtl_consultar_carro.getSelectedRow(), 3)))
            );
    }//GEN-LAST:event_btnCarroAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalVenda;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCarroAdd;
    private javax.swing.JButton btnCarroRem;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarCarro;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_carro;
    private javax.swing.JTable jtl_consultar_carro_selecionado;
    private javax.swing.JTable jtl_consultar_cli;
    private javax.swing.JTextField pesquisa_nome_carro;
    private javax.swing.JTextField pesquisa_nome_cli;
    // End of variables declaration//GEN-END:variables
}
