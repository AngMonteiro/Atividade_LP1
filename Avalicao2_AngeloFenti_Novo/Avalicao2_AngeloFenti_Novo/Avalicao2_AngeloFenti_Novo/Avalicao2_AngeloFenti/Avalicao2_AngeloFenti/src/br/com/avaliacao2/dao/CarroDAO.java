/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.avaliacao2.dao;

/**
 *
 * @author angel
 */
import java.sql.*;
import br.com.avaliacao2.dto.CarroDTO;
public class CarroDAO {
    
    public CarroDAO(){
        
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirCarro(CarroDTO carroDTO){
        
        try{
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into carro (marca, modelo, versao, cor, quilometragem, preco, blindagem, placa, anoModelo, anoFabricacao) values ( "
              + "'" + carroDTO.getMarca() + "', "
                    + "'" + carroDTO.getModelo()+ "', "
                    + "'" + carroDTO.getVersao()+ "', "
                    + "'" + carroDTO.getCor()+ "', "
                    + "'" + carroDTO.getQuilometragem()+ "', "
                    + "'" + carroDTO.getPreco()+ "', "
                    + "'" + carroDTO.getBlindagem()+ "', "
                    + "'" + carroDTO.getPlaca()+ "', "
                    + carroDTO.getAnoModelo()+ ", "
                    + carroDTO.getAnoFabricacao()+ ") ";
                    
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
        
    }
    public boolean alterarCarro(CarroDTO carroDTO){
        try{
            
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update carro set "
                    + "marca = '" + carroDTO.getMarca()+ "', "
                    + "modelo = '" + carroDTO.getModelo()+ "', "
                    + "versao = '" + carroDTO.getVersao()+ "', "
                    + "cor = '" + carroDTO.getCor()+ "', "
                    + "quilometragem = '" + carroDTO.getQuilometragem()+ "', "
                    + "preco = '" + carroDTO.getPreco()+ "', "
                    + "blindagem = '" + carroDTO.getBlindagem()+ "', "
                    + "placa = '" + carroDTO.getPlaca()+ "', "
                    + "anoModelo = " + carroDTO.getAnoModelo()+ ", "
                    + "anoFabricacao = " + carroDTO.getAnoFabricacao()
                    + "where id_carro = " + carroDTO.getId_carro();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    public boolean excluirCarro(CarroDTO carroDTO){
        try{
            
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from carro where id_carro = "
                    + carroDTO.getId_carro();
            
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        catch(Exception e){
            
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    public ResultSet consultarCarro(CarroDTO carroDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select c.* "+
                            "from carro c "+
                            "where marca like '" +carroDTO.getMarca()+ "%' " +
                            "order by c.marca";
                    break;
                case 2:
                    comando = "Select c.* "+
                            "from carro c "+
                            "where modelo like '" +carroDTO.getModelo()+ "%' " +
                            "order by c.modelo";
                    break;
                case 3:
                    comando = "Select c.* "+
                            "from carro c " +
                            "where c.id_carro = " + carroDTO.getId_carro();
                    break;
                case 4:
                    comando = "Select c.id_carro, c.marca, c.modelo "+
                            "from carro c ";
                    break;
                    
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception e){
        System.out.println(e.getMessage());
        return rs;
    }
    }
}
