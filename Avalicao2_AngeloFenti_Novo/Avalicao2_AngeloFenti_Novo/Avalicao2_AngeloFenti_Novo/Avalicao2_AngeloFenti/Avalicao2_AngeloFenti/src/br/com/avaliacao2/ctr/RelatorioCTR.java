/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.avaliacao2.ctr;

/**
 *
 * @author Flavia
 */
import java.sql.*;
import br.com.avaliacao2.dao.ConexaoDAO;
import java.io.InputStream;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class RelatorioCTR {
    
    public RelatorioCTR(){
        
    }
    public Connection getConexao(){
        return ConexaoDAO.ConnectDBRels();
    } //Fecha o metodo ConectDB
    public JFrame abrirRelatorio(String relatorio, String titulo, Map parametros){

    try{

        // Cria um Stream com os dados do relatório
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("br/com/avaliacao2/rels/"+relatorio);

        // Gerando a impressao do relatório
        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, 
                                getConexao());
        //Fechando o banco de dados que foi aberto para gerar o relatório
        CloseDB();

        // montando a visualização do relatório
        JRViewer viewer = new JRViewer( print );

        // criando um FRAME para exibir o relatório
        JFrame frameRelatorio = new JFrame( titulo );
        
        frameRelatorio.add( viewer );

        // maximiza o JFrame para ocupar a tela toda.
        frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );

        // configura a operação padrão quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // retorna o JFrame com o relatório para ser mostrado na view
        return frameRelatorio;
    }
    catch (Exception e){
    System.out.println("Entrou erro metodo abrirRelatorio");
    System.out.println(e.getMessage());
    return null;
}

    }
    
    public void CloseDB(){
    ConexaoDAO.CloseDB();
} //Fecha o metodo CloseDB


    
}
