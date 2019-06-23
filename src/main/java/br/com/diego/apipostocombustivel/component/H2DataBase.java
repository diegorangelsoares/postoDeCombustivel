package br.com.diego.apipostocombustivel.component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.diego.apipostocombustivel.models.Combustivel;
import br.com.diego.apipostocombustivel.repository.CombustivelRepository;

@Component
public class H2DataBase {
	@Autowired
	private CombustivelRepository crRepository;
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {

		insertCSVBanco();
    }
	
	/**Código abaixo lê o arquivo CSV
	* @author Diego Rangel
	* @param  filePath - Caminho do arquivo CSV
	* @param  separatorCSV - Tipo de serparador - Identificardor das colunas Ex.: ; , .
	* @return List <Combustivel> - Lista com todos os registros de combustíveis do arquivo
	*/
	public List <Combustivel> loadCSV (String filePath, String separatorCSV){   
        List <Combustivel> combustiveis = new ArrayList<>();
        //String filePath = "C:\\Users\\DIEGO\\Desktop\\comb.csv";
        BufferedReader br = null;
        String line = "";
        try {

            br = new BufferedReader(new FileReader(filePath));
            int contadorLinha = 0;
            while ((line = br.readLine()) != null) {
                // Usar o separador
                String[] column = line.split(separatorCSV);
                String dataImportacao = "";
                Date data = new Date();
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy");
                dataImportacao = formatador.format(data);   
                //Não pegar linha 0
                if (contadorLinha != 0){                  
                    Combustivel combustivel = new Combustivel(0,column[0],column[1],column[2],column[3],column[4],column[5],column[6], column[7],column[8], column[9].replace("R$ / ", ""),column[10],dataImportacao);
                    combustiveis.add(combustivel);
                    System.out.println("Carregando informações do combustivel " + contadorLinha + ", aguarde..!");
                    //Imprime no console para teste
//	                    System.out.println("Regiao: " + combustivel.getRegiao() + "\nestado: " + combustivel.getEstado() + "\nmunicipio: "+ combustivel.getMunicipio()+ "\nrevenda: "+ combustivel.getRevenda()+ "\ninstalacao: "+ combustivel.getInstalacao()
//	                     +"\nproduto: " + combustivel.getProduto() + "\ndataDaColeta: " + combustivel.getDataDaColeta() + "\nvalorDaCompra: "+ combustivel.getValoDaCompra()+ "\nValorDaVenda: "+ combustivel.getValorDaVenda()+ "\nunidadeDeMedida: "+ combustivel.getUnidadeDeMedida()
//	                    +"\nbandeira: " + combustivel.getBandeira() + "\ndataImportacao: " + combustivel.getDataImportacao()+"\n");
                }
                contadorLinha++;
            }
            System.out.println("Informações carregadas com sucesso!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return combustiveis;
    }
	
	/**Código abaixo grava o conteudo do CSV no banco
	* @author Diego Rangel
	* @return 
	*/
	public void insertCSVBanco() {
		String filePath = "2018-1_CA.csv";
		String separatorCSV = "  ";
		List <Combustivel> combustiveis = loadCSV(filePath,separatorCSV);
		
		//Inserir no banco
		try {
			crRepository.save(combustiveis);//acho que vai ter que mudar pra o tipo iterable, mais faz o teste pra ve
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
