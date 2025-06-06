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
import java.sql.ResultSet;
import br.com.avaliacao2.dto.FuncionarioDTO;
import br.com.avaliacao2.dao.FuncionarioDAO;
import br.com.avaliacao2.dao.ConexaoDAO;

public class FuncionarioCTR {

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    /**
    * Método construtor da classe
    */
    public FuncionarioCTR() {
    }
    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
    try {
        // Chama o método que está na classe DAO aguardando uma resposta (true ou false)
        if (funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
            return "Funcionário Cadastrado com Sucesso!!!";
        } else {
            return "Funcionário NÃO Cadastrado!!!";
        }
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
        return "Funcionario NÃO Cadastrado";
    }
    }
    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
            return "Funcionário Alterado com Sucesso!!!";
        } else {
            return "Funcionário NÃO Alterado!!!";
        }
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
        return "Funcionário NÃO Alterado!!!";
    }
}
    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
            return "Funcionário Excluído com Sucesso!!!";
        } else {
            return "Funcionário NÃO Excluído!!!";
        }
    }
    catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO Excluído!!!";
        }
}
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        return rs;
    }
    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        return funcionarioDAO.logarFuncionario(funcionarioDTO);
    }
    public void CloseDB () {
        ConexaoDAO.CloseDB();
    }
}
