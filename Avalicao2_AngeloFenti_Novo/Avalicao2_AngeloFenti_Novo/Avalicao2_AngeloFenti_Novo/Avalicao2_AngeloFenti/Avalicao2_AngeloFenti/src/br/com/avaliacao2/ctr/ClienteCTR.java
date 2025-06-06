
package br.com.avaliacao2.ctr;

import java.sql.ResultSet;
import br.com.avaliacao2.dto.ClienteDTO;
import br.com.avaliacao2.dao.ClienteDAO;
import br.com.avaliacao2.dao.ConexaoDAO;

public class ClienteCTR {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public ClienteCTR(){
    
}
    public String inserirCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.inserirCliente(clienteDTO)){
                return "Cliente Cadastrado com Sucesso!!";
            }else{
                return "Cliente NÃO Cadastrado!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return"Cliente NÃO Cadastrado!!";
        }
    }
    public String alterarCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.alterarCliente(clienteDTO)){
                return"Cliente Alterado com Sucesso!!!";
            }else{
                return "Cliente NÂO Alterado!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Cliente NÂO Alterado!!!";
        }
    }
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){
        ResultSet rs = null;
        
        rs = clienteDAO.consultarCliente(clienteDTO, opcao);
        
        return rs;
    }
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    public String excluirCliente(ClienteDTO clienteDTO) {

    try {

        if (clienteDAO.excluirCliente(clienteDTO)) {

            return "Cliente Excluído com Sucesso!!!";

        } else {

            return "Cliente NÃO Excluído!!!";

        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

        return "Cliente NÃO Excluído!!!";

    }

}
}
