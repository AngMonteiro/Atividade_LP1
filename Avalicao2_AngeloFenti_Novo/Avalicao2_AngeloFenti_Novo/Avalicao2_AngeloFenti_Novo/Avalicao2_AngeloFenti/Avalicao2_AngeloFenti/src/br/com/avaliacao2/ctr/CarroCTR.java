/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.avaliacao2.ctr;

/**
 *
 * @author angel
 */
import java.sql.ResultSet;
import br.com.avaliacao2.dto.CarroDTO;
import br.com.avaliacao2.dao.CarroDAO;
import br.com.avaliacao2.dao.ConexaoDAO;

public class CarroCTR {

    CarroDAO carroDAO = new CarroDAO();

    /**
     * Método construtor da classe
     */
    public CarroCTR() {
    }
    
    public String inserirCarro(CarroDTO carroDTO) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (carroDAO.inserirCarro(carroDTO)) {
            return "Carro Cadastrado com Sucesso!!!";
        } else {
            return "Carro NÃO Cadastrado!!!";
        }
    } //Caso tenha algum erro no codigo acima é enviado uma mensagem no
      //console com o que esta acontecendo.
    catch (Exception e) {
        System.out.println(e.getMessage());
        return "Carro NÃO Cadastrado";
    }
}
    public String alterarCarro(CarroDTO carroDTO) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (carroDAO.alterarCarro(carroDTO)) {
            return "Carro Alterado com Sucesso!!!";
        } else {
            return "Carro NÃO Alterado!!!";
        }
    } //Caso tenha algum erro no codigo acima é enviado uma mensagem no
      //console com o que esta acontecendo.
    catch (Exception e) {
        System.out.println(e.getMessage());
        return "Carro NÃO Alterado!!!";
    }
}
    public String excluirCarro(CarroDTO carroDTO) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (carroDAO.excluirCarro(carroDTO)) {
            return "Carro Excluído com Sucesso!!!";
        } else {
            return "Carro NÃO Excluído!!!";
        }
    } //Caso tenha algum erro no codigo acima é enviado uma mensagem no
      //console com o que esta acontecendo.
    catch (Exception e) {
        System.out.println(e.getMessage());
        return "Carro NÃO Excluído!!!";
    }
}
    public ResultSet consultarCarro(CarroDTO carroDTO, int opcao) {
    //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
    ResultSet rs = null;
    //O atributo rs recebe a consulta realizada pelo método da classe DAO
    rs = carroDAO.consultarCarro(carroDTO, opcao);
    return rs;
}
    public void CloseDB() {
    ConexaoDAO.CloseDB();
}
}

