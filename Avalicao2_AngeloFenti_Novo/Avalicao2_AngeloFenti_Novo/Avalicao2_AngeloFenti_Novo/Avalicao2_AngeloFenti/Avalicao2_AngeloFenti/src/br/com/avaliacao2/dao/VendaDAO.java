/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avaliacao2.dao;

/**
 *
 * @author Flavia
 */
import br.com.avaliacao2.dto.ClienteDTO;
import br.com.avaliacao2.dto.VendaDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
public class VendaDAO {
    
    public VendaDAO(){       
    }
    private ResultSet rs = null;
    
    Statement stmt = null;
    Statement stmtl = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean inserirVenda(VendaDTO vendaDTO, ClienteDTO clienteDTO, JTable carros){
        try{
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            stmtl = ConexaoDAO.con.createStatement();
        String comando1 = "Insert into Venda (dat_vend, val_vend, "
            + "id_cli) values ( "
            + "to_date('" + date.format(vendaDTO.getDat_vend()) + "', 'DD/MM/YYYY'), "
            + vendaDTO.getVal_vend() + ", "
            + clienteDTO.getId_cli() + ")";

        //Executa o comando SQL no banco de Dados
        stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
        rs = stmt.getGeneratedKeys();
        rs.next();
        
        for(int cont=0; cont < carros.getRowCount(); cont++){
    String comando2 = "Insert into carro_venda (id_vend, id_carro, "
        + "val_carro, qtd_carro) values ( "
        + rs.getInt("id_vend") + ", "
        + carros.getValueAt(cont, 0) + ", "
        + carros.getValueAt(cont, 3) + ", "
        + carros.getValueAt(cont, 4) + ");";

            stmtl.execute(comando2);
        }

        //Da um commit no banco de dados
        ConexaoDAO.con.commit();
        //Fecha o statement
        stmt.close();
        stmtl.close();
        rs.close();
        return true;
        
        
        }catch (Exception e) {
        System.out.println(e.getMessage());
            return false;
        } 
        //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }
}
