/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avaliacao2.ctr;

/**
 *
 * @author Flavia
 */
import br.com.avaliacao2.dao.ConexaoDAO;
import br.com.avaliacao2.dao.VendaDAO;
import br.com.avaliacao2.dto.VendaDTO;
import br.com.avaliacao2.dto.ClienteDTO;
import javax.swing.JTable;
public class VendaCTR {
    VendaDAO vendaDAO = new VendaDAO();
    
    public VendaCTR(){
    }
    public String inserirVenda(VendaDTO vendaDTO, ClienteDTO clienteDTO, JTable carros) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (vendaDAO.inserirVenda(vendaDTO, clienteDTO, carros)) {
            return "Venda Cadastrada com Sucesso!!!";
        } else {
            return "Venda NÃO Cadastrada!!!";
        }
    }catch(Exception e){
        System.out.println(e.getMessage());
        return "Venda NÃO Cadastrada";
    }
}
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
    
