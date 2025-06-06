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
import br.com.avaliacao2.dto.CarroDTO;
import br.com.avaliacao2.ctr.CarroCTR;
import br.com.avaliacao2.dao.CarroDAO;
public class CarroVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form ClienteView
     */
    public CarroVIEW() {
        initComponents();
        
        liberaCampos(false);
        liberaBotoes(true,false,false,false,true);
        modelo_jtl_consultar_carro = (DefaultTableModel) jtl_consultar_carro.getModel();
    }
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);

}
        CarroDTO carroDTO = new CarroDTO(); //Cria um objeto carroDTO
        CarroCTR carroCTR = new CarroCTR(); //Cria um objeto carroCTR

        int gravar_alterar; //Variavel usada para saber se esta alterando ou incluindo

        ResultSet rs; //Variavel usada para preenchimeto da tabela e dos campos
        DefaultTableModel modelo_jtl_consultar_carro;
        
        
        private void gravar(){
        try{
            carroDTO.setMarca(marca_carro.getSelectedItem().toString());
            carroDTO.setModelo(modelo_carro.getText());
            carroDTO.setVersao(versao_carro.getText());
            carroDTO.setCor(cor_carro.getText());
            carroDTO.setQuilometragem(quilometragem_carro.getText());
            carroDTO.setBlindagem(blindagem_carro.getSelectedItem().toString());
            carroDTO.setPreco(preco_carro.getText());
            carroDTO.setPlaca(placa_carro.getText());
            carroDTO.setAnoModelo(Integer.parseInt(anoModelo_carro.getText()));
            carroDTO.setAnoFabricacao(Integer.parseInt(anoFabricacao_carro.getText()));

            JOptionPane.showMessageDialog(null, 
                carroCTR.inserirCarro (carroDTO)
            );
        }
        catch (Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
}
        private void alterar(){
        try{
            carroDTO.setMarca(marca_carro.getSelectedItem().toString());
            carroDTO.setModelo(modelo_carro.getText());
            carroDTO.setVersao(versao_carro.getText());
            carroDTO.setCor(cor_carro.getText());
            carroDTO.setQuilometragem(quilometragem_carro.getText());
            carroDTO.setBlindagem(blindagem_carro.getSelectedItem().toString());
            carroDTO.setPreco(preco_carro.getText());
            carroDTO.setPlaca(placa_carro.getText());
            carroDTO.setAnoModelo(Integer.parseInt(anoModelo_carro.getText()));
            carroDTO.setAnoFabricacao(Integer.parseInt(anoFabricacao_carro.getText()));

            JOptionPane.showMessageDialog(null, 
                carroCTR.alterarCarro (carroDTO)
            );
        }
        catch (Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
}
        private void excluir() {
            if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Carro?", "Aviso",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null,
                        carroCTR.excluirCarro(carroDTO)
                );
            }
        }
        private void liberaCampos (boolean a){
            marca_carro.setEnabled(a);
            modelo_carro.setEnabled(a);
            versao_carro.setEnabled(a);
            cor_carro.setEnabled(a);
            quilometragem_carro.setEnabled(a);
            preco_carro.setEnabled(a);
            blindagem_carro.setEnabled(a);
            placa_carro.setEnabled(a);
            anoModelo_carro.setEnabled(a);
            anoFabricacao_carro.setEnabled(a);
        }
        private void liberaBotoes (boolean a, boolean b, boolean c, boolean d, boolean e){
            btnNovo.setEnabled(a);
            btnSalvar.setEnabled(b);
            btnCancelar.setEnabled(c);
            btnExcluir.setEnabled(d);
            btnSair.setEnabled(e);
        }
        private void limpaCampos (){
            modelo_carro.setText("");
            versao_carro.setText("");
            cor_carro.setText("");
            quilometragem_carro.setText("");
            preco_carro.setText("");
            placa_carro.setText("");
            anoModelo_carro.setText("");
            anoFabricacao_carro.setText("");
        }
        
        private void preencheTabela (String marca){
            try{
                //Limpa todas as linhas
                modelo_jtl_consultar_carro.setNumRows(0);
                //Enquanto tiver linhas - faça
                carroDTO.setMarca(marca);
                rs = carroCTR.consultarCarro(carroDTO, 1); //1 é a pesquisa por nome na classe DAO
                while(rs.next()){
                    modelo_jtl_consultar_carro.addRow(new Object[]{
                        rs.getString("id_carro"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                    });
                }
            }
            catch(Exception erTab) {
                System.out.println("Erro SQL: "+erTab);
            }
            finally{
                carroCTR.CloseDB();
            }
            
        }
        private void preencheCampos (int id_carro){
            try{
                carroDTO.setId_carro(id_carro);
                rs = carroCTR.consultarCarro(carroDTO, 2);
                if(rs.next()){
                    limpaCampos();
                    
                    marca_carro.setSelectedItem(rs.getString("marca"));
                    modelo_carro.setText(rs.getString("modelo"));
                    versao_carro.setText(rs.getString("versao"));
                    cor_carro.setText(rs.getString("cor"));
                    quilometragem_carro.setText(rs.getString("quilometragem"));
                    blindagem_carro.setSelectedItem(rs.getString("blindagem"));
                    preco_carro.setText(rs.getString("preco"));
                    placa_carro.setText(rs.getString("placa"));
                    anoModelo_carro.setText(rs.getString("anoModelo"));
                    anoFabricacao_carro.setText(rs.getString("anoFabricacao"));

                    gravar_alterar = 2;
                    liberaCampos (true);
                    }
                   }
            catch(Exception erTab) {
                System.out.println("Erro SQL: "+erTab);
            }
            finally{
                carroCTR.CloseDB();
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

        jLabel1 = new javax.swing.JLabel();
        marca_carro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        modelo_carro = new javax.swing.JTextField();
        anoModelo_carro = new javax.swing.JTextField();
        anoFabricacao_carro = new javax.swing.JTextField();
        versao_carro = new javax.swing.JTextField();
        cor_carro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        quilometragem_carro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        preco_carro = new javax.swing.JTextField();
        blindagem_carro = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        placa_carro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_carro = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        pesquisa_marca = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Preencha os dados do seu veículo");

        marca_carro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHEVROLET", "FIAT", "FORD", "HONDA", "HYUNDAI", "MITSUBISHI", "NISSAN", "PEUGEOT", "RENAULT", "TOYOTA", "VOLKSWAGEN", "VOLVO", "ACURA", "ADAMO", "AGNIS", "AGRALE", "ALFA ROMEO", "AMAZONAS", "AMERICAR", "ANGRA", "ASIA", "ASTON MARTIN", "AUDI", "AUSTIN-HEALEY", "AVALLONE", "BAJA", "BEACH", "BENTLEY", "BIANCO", "BMW", "BORGWARD", "BRASFIBRA", "BRILLIANCE", "BRM", "BUGATTI", "BUGRE", "BUGWAY", "BUICK", "BYD", "CADILLAC", "CAOA CHERY", "CAUYPE", "CBP", "CBT", "CHAMONIX", "CHANA", "CHANGAN", "CHEDA", "CHEVROLET", "CHRYSLER", "CITROËN", "CORD", "COYOTE", "CROSS LANDER", "D2D MOTORS", "DACON", "DAEWOO", "DAIHATSU", "DATSUN", "DE SOTO", "DE TOMASO", "DELOREAN", "DKW-VEMAG", "DODGE", "DUNNAS", "EAGLE", "EDSEL", "EFFA", "EGO", "EMIS", "ENGESA", "ENSEADA", "ENVEMO", "FARGO", "FARUS", "FERCAR BUGGY", "FERRARI", "FEVER", "FIAT", "FIBRARIO", "FIBRAVAN", "FISKER", "FNM", "FORD", "FOTON", "FYBER", "GEELY", "GEO", "GIANTS", "GLASPAC", "GMC", "GRANCAR", "GREAT WALL", "GURGEL", "GWM", "HAFEI", "HB", "HOFSTETTER", "HONDA", "HUDSON", "HUMMER", "HUPMOBILE", "HYUNDAI", "ICOMDA", "INCOFER", "INFINITI", "INTERNATIONAL", "ISUZU", "IVECO", "JAC", "JAGUAR", "JEEP", "JENSEN", "JINBEI", "JONWAY", "JPX", "KADRON", "KAISER", "KIA", "KOENIGSEGG", "L AUTOMOBILE", "L´AUTO CRAFT", "LADA", "LAMBORGHINI", "LANCIA", "LAND ROVER", "LANDWIND", "LEXUS", "LHM", "LIFAN", "LINCOLN", "LOBINI", "LOTUS", "LUCID", "MAGNATA", "MAHINDRA", "MARCOPOLO", "MARINA´S", "MASERATI", "MATRA", "MAYBACH", "MAZDA", "MCLAREN", "MENON", "MERCEDES-BENZ", "MERCURY", "MG", "MINI", "MITSUBISHI", "MIURA", "MOBBY", "MORGAN", "MORRIS", "MP LAFER", "MPLM", "NASH", "NETA", "NEWTRACK", "NISSAN", "NURBURGRING", "OLDSMOBILE", "OPEL", "PACKARD", "PAG", "PAGANI", "PEUGEOT", "PLYMOUTH", "PONTIAC", "PORSCHE", "PUMA", "RAM", "RDK", "RELY", "RENAULT", "RENO", "REVA-I", "RILEY", "RIVIAN", "ROLLS-ROYCE", "ROMI", "ROVER", "SAAB", "SANTA MATILDE", "SATURN", "SEAT", "SELVAGEM", "SERES", "SHELBY", "SHINERAY", "SHORT", "SHUANGHUAN", "SIMCA", "SMART", "SPYKER", "SSANGYONG", "STANDARD", "STUDEBAKER", "SUBARU", "SUNBEAM", "SUZUKI", "TAC", "TANDER", "TANGER", "TESLA", "TOY", "TOYOTA", "TRIUMPH", "TROLLER", "UNIMOG", "VENDETTA", "VOLKSWAGEN", "VOLVO", "WAKE", "WALK", "WAY BRASIL", "WHIPPET", "WIESMANN", "WILLYS", "WILLYS OVERLAND", "ZEEKR", "ZIMMER" }));
        marca_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marca_carroActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Marca*");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Modelo*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Ano do Modelo*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Ano da Fabricação*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Versão*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Cor*");

        modelo_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelo_carroActionPerformed(evt);
            }
        });

        versao_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                versao_carroActionPerformed(evt);
            }
        });

        cor_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cor_carroActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Quilometragem*");

        quilometragem_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quilometragem_carroActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Preço*");

        preco_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preco_carroActionPerformed(evt);
            }
        });

        blindagem_carro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Blindado*");

        placa_carro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placa_carroActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Placa*");

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

        jtl_consultar_carro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Marca", "Modelo"
            }
        ));
        jtl_consultar_carro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_carroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_carro);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Marca:");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(btnNovo)
                .addGap(113, 113, 113)
                .addComponent(btnSalvar)
                .addGap(125, 125, 125)
                .addComponent(btnCancelar)
                .addGap(111, 111, 111)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marca_carro, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(anoModelo_carro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(anoFabricacao_carro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modelo_carro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(versao_carro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cor_carro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quilometragem_carro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blindagem_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(placa_carro, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(preco_carro, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(pesquisa_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marca_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modelo_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(anoModelo_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(anoFabricacao_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(versao_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cor_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quilometragem_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preco_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(blindagem_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(placa_carro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pesquisa_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(btnPesquisar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSair))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marca_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marca_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marca_carroActionPerformed

    private void modelo_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelo_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modelo_carroActionPerformed

    private void versao_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_versao_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_versao_carroActionPerformed

    private void cor_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cor_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cor_carroActionPerformed

    private void quilometragem_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quilometragem_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quilometragem_carroActionPerformed

    private void preco_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preco_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preco_carroActionPerformed

    private void placa_carroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placa_carroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placa_carroActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_carro.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar=0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true,false,false,false,true);
        modelo_jtl_consultar_carro.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        preencheTabela(pesquisa_marca.getText());
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

    private void jtl_consultar_carroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_carroMouseClicked
        // TODO add your handling code here:
        preencheCampos(Integer.parseInt(String.valueOf(
        jtl_consultar_carro.getValueAt(
        jtl_consultar_carro.getSelectedRow(), 0))));
        liberaBotoes(false,true,true,true,true);
        
    }//GEN-LAST:event_jtl_consultar_carroMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anoFabricacao_carro;
    private javax.swing.JTextField anoModelo_carro;
    private javax.swing.JComboBox<String> blindagem_carro;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cor_carro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_carro;
    private javax.swing.JComboBox<String> marca_carro;
    private javax.swing.JTextField modelo_carro;
    private javax.swing.JTextField pesquisa_marca;
    private javax.swing.JTextField placa_carro;
    private javax.swing.JTextField preco_carro;
    private javax.swing.JTextField quilometragem_carro;
    private javax.swing.JTextField versao_carro;
    // End of variables declaration//GEN-END:variables
}
