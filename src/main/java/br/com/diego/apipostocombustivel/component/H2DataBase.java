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
                    //System.out.println("Tamanho: "+column.length);
                	System.out.println("Carregando informações do combustivel "+contadorLinha+", aguarde..!");
                    //Com 11 colunas 
                    if (column.length == 11){
                        //contadorTamanho11++;
                        String regiao = column[0];
                        String estado = column[1];
                        String municipio = column[2];
                        String revenda = column[3];
                        String instalacao = column[4];
                        String produto = column[5];
                        String dataDaColeta = column[6];
                        String valoDaCompra = column[7];
                        String ValorDaVenda = column[8];
                        String unidadeDeMedida = column[9].replace(" / litro", "");
                        String bandeira = column[10];  
                        Combustivel combustivel = new Combustivel(0, regiao, estado, municipio, revenda, instalacao, produto, dataDaColeta, valoDaCompra, ValorDaVenda, unidadeDeMedida, bandeira,dataImportacao);
                        combustiveis.add(combustivel);
                        //Imprime no console para teste
//                        System.out.println("Tamanho 11");
//	                System.out.println("Regiao: " + combustivel.getRegiao() + "\nestado: " + combustivel.getEstado() + "\nmunicipio: "+ combustivel.getMunicipio()+ "\nrevenda: "+ combustivel.getRevenda()+ "\ninstalacao: "+ combustivel.getInstalacao()
//	                 +"\nproduto: " + combustivel.getProduto() + "\ndataDaColeta: " + combustivel.getDataDaColeta() + "\nvalorDaCompra: "+ combustivel.getValoDaCompra()+ "\nValorDaVenda: "+ combustivel.getValorDaVenda()+ "\nunidadeDeMedida: "+ combustivel.getUnidadeDeMedida()
//	                 +"\nbandeira: " + combustivel.getBandeira() + "\ndataImportacao: " + combustivel.getDataImportacao()+"\n");
                    }
                    if (column.length == 12){
                        //contadorTamanho12++;
                        String regiao = column[0];
                        String estado = column[1];
                        String municipio = column[2];
                        String revenda = column[3];
                        String instalacao = column[5];
                        String produto = column[6];
                        String dataDaColeta = column[7];
                        String valoDaCompra = column[8];
                        String ValorDaVenda = column[9];
                        String unidadeDeMedida = column[10].replace(" / litro", "");
                        String bandeira = column[11];  
                        Combustivel combustivel = new Combustivel(0, regiao, estado, municipio, revenda, instalacao, produto, dataDaColeta, valoDaCompra, ValorDaVenda, unidadeDeMedida, bandeira,dataImportacao);
                        combustiveis.add(combustivel);
                        //Imprime no console para teste
//                        System.out.println("Tamanho 12");
//	                System.out.println("Regiao: " + combustivel.getRegiao() + "\nestado: " + combustivel.getEstado() + "\nmunicipio: "+ combustivel.getMunicipio()+ "\nrevenda: "+ combustivel.getRevenda()+ "\ninstalacao: "+ combustivel.getInstalacao()
//	                 +"\nproduto: " + combustivel.getProduto() + "\ndataDaColeta: " + combustivel.getDataDaColeta() + "\nvalorDaCompra: "+ combustivel.getValoDaCompra()+ "\nValorDaVenda: "+ combustivel.getValorDaVenda()+ "\nunidadeDeMedida: "+ combustivel.getUnidadeDeMedida()
//	                 +"\nbandeira: " + combustivel.getBandeira() + "\ndataImportacao: " + combustivel.getDataImportacao()+"\n");
                    }
                    if (column.length == 13){
                        //contadorTamanho13++;
                        String regiao = column[0];
                        String estado = column[1];
                        String municipio = column[2];
                        String revenda = column[3];
                        String instalacao = column[6];
                        String produto = column[7];
                        String dataDaColeta = column[8];
                        String valoDaCompra = column[9];
                        String ValorDaVenda = column[10];
                        String unidadeDeMedida = column[11].replace(" / litro", "");
                        String bandeira = column[12];  
                        Combustivel combustivel = new Combustivel(0, regiao, estado, municipio, revenda, instalacao, produto, dataDaColeta, valoDaCompra, ValorDaVenda, unidadeDeMedida, bandeira,dataImportacao);
                        combustiveis.add(combustivel);
                        //Imprime no console para teste
//                        System.out.println("Tamanho 13");
//	                System.out.println("Regiao: " + combustivel.getRegiao() + "\nestado: " + combustivel.getEstado() + "\nmunicipio: "+ combustivel.getMunicipio()+ "\nrevenda: "+ combustivel.getRevenda()+ "\ninstalacao: "+ combustivel.getInstalacao()
//	                 +"\nproduto: " + combustivel.getProduto() + "\ndataDaColeta: " + combustivel.getDataDaColeta() + "\nvalorDaCompra: "+ combustivel.getValoDaCompra()+ "\nValorDaVenda: "+ combustivel.getValorDaVenda()+ "\nunidadeDeMedida: "+ combustivel.getUnidadeDeMedida()
//	                 +"\nbandeira: " + combustivel.getBandeira() + "\ndataImportacao: " + combustivel.getDataImportacao()+"\n");
                    }
                    if (column.length == 17){
                        //contadorTamanho17++;
                        String regiao = column[0];
                        String estado = column[1];
                        String municipio = column[2];
                        String revenda = column[3] + " "+column[4] + " "+column[5];
                        String instalacao = column[10];
                        String produto = column[11];
                        String dataDaColeta = column[12];
                        String valoDaCompra = column[13];
                        String ValorDaVenda = column[14];
                        String unidadeDeMedida = column[15].replace(" / litro", "");
                        String bandeira = column[16];
                        Combustivel combustivel = new Combustivel(0, regiao, estado, municipio, revenda, instalacao, produto, dataDaColeta, valoDaCompra, ValorDaVenda, unidadeDeMedida, bandeira,dataImportacao);
                        combustiveis.add(combustivel);
                        //Imprime no console para teste
//                        System.out.println("Tamanho 17");
//	                System.out.println("Regiao: " + combustivel.getRegiao() + "\nestado: " + combustivel.getEstado() + "\nmunicipio: "+ combustivel.getMunicipio()+ "\nrevenda: "+ combustivel.getRevenda()+ "\ninstalacao: "+ combustivel.getInstalacao()
//	                 +"\nproduto: " + combustivel.getProduto() + "\ndataDaColeta: " + combustivel.getDataDaColeta() + "\nvalorDaCompra: "+ combustivel.getValoDaCompra()+ "\nValorDaVenda: "+ combustivel.getValorDaVenda()+ "\nunidadeDeMedida: "+ combustivel.getUnidadeDeMedida()
//	                 +"\nbandeira: " + combustivel.getBandeira() + "\ndataImportacao: " + combustivel.getDataImportacao()+"\n");
                    }
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
	
	public boolean temNumero(String valor) {
		if (valor.contains("0") || valor.contains("1") || valor.contains("2") || valor.contains("3")
				 || valor.contains("4") || valor.contains("5") || valor.contains("6")
				 || valor.contains("7") || valor.contains("8") || valor.contains("9")) { 
			return true;
		}else {
			return false;
		}
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
			crRepository.save(combustiveis);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
