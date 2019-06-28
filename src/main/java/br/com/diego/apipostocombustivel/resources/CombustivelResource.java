package br.com.diego.apipostocombustivel.resources;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.apipostocombustivel.models.Combustivel;
import br.com.diego.apipostocombustivel.repository.CombustivelRepository;

/**Classe de códigos do combustivel
* @author Diego Rangel
*/
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class CombustivelResource {
	
	@Autowired
	private CombustivelRepository cRepository;
	
	public CombustivelResource() {
		//insertCSVBanco();
	}
	
	/**Código lista todos os combustiveis
	* @author Diego Rangel
	* @return ResponseEntity<?> - Retorna todos os combustiveis
	*/
	@GetMapping(path = "combustiveis")
	public ResponseEntity<?> listAll(){
		//cRepository.findByRegiao("Teste");
		return new ResponseEntity<>(cRepository.findAll(), HttpStatus.OK);
	}
	
	/**Código para retornar média
	* @author Diego Rangel
	* @return String - Retorna a media de preço do combstivel
	*/
	@GetMapping( path="combustiveis/{municipio}")
	public ResponseEntity<?> getMedia(@PathVariable("municipio") String municipio){
		List <Combustivel> combustiveis = cRepository.findAll();
		//fazer código para calcular media
		String med = retornMedia(combustiveis);
		return new ResponseEntity<>(med	,HttpStatus.OK);
	}
	
	/**Código para retornar média de venda e compra por municipio
	* @author Diego Rangel
	* @return String - Retorna a media de preço de compra e venda do combstivel
	*/
	@GetMapping( path="combustiveis/MediaCompraVendaPorMunicipio/{municipio}")
	public ResponseEntity<?> getMediaCompraVenda(@PathVariable("municipio") String municipio){
		List <Combustivel> combustiveis = cRepository.findAll();
		List <Combustivel> combustiveisMunicipio = new ArrayList<>();
		for (int i = 0; i < combustiveis.size(); i++) {
			if (combustiveis.get(i).getMunicipio().equals(municipio.toUpperCase())) {
				combustiveisMunicipio.add(combustiveis.get(i));
			}
		}
		//fazer código para calcular media
		String med = retornMedia(combustiveisMunicipio);
		String medCompra = retornMediaCompra(combustiveisMunicipio);
		String retorno = "Média de Compra: "+medCompra + "  Média de Venda: "+med;
		return new ResponseEntity<>(retorno	,HttpStatus.OK);
	}
	
	/**Código para retornar média de venda e compra por bandeira
	* @author Diego Rangel
	* @return String - Retorna a media de preço de compra e venda do combstivel
	*/
	@GetMapping( path="combustiveis/MediaCompraVendaPorBandeira/{bandeira}")
	public ResponseEntity<?> getMediaCompraVendaPorBandeira(@PathVariable("bandeira") String bandeira){
		List <Combustivel> combustiveis = cRepository.findAll();
		List <Combustivel> combustiveisMunicipio = new ArrayList<>();
		for (int i = 0; i < combustiveis.size(); i++) {
			if (combustiveis.get(i).getBandeira().equals(bandeira.toUpperCase())) {
				combustiveisMunicipio.add(combustiveis.get(i));
			}
		}
		//fazer código para calcular media
		String med = retornMedia(combustiveisMunicipio);
		String medCompra = retornMediaCompra(combustiveisMunicipio);
		String retorno = "Média de Compra: "+medCompra + "  Média de Venda: "+med;
		return new ResponseEntity<>(retorno	,HttpStatus.OK);
	}
	
	public String retornMedia (List <Combustivel> combustiveis){
		DecimalFormat df = new DecimalFormat ("#0.000", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
		int quantReg = 0;
		double totalPrecos = 0;
		for (int i = 0; i < combustiveis.size(); i++) {
			double preco1 = 0;
			String valorVenda = combustiveis.get(i).getValorDaVenda();
			//Tratar se tem preço de venda preenchido
			if (!valorVenda.equals("")){
		        try {
		        	preco1 = df.parse (valorVenda).doubleValue(); // isto deve dar o número "1234.56"	 
		        	//preco1 = Double.parseDouble(valorVenda);    
		            totalPrecos = totalPrecos + preco1;
		            quantReg++;
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
				//System.out.println("Municipio: "+combustiveis.get(i).getMunicipio()+"\nPreço: "+combustiveis.get(i).getValorDaVenda()+"\n");
			}
		}
		totalPrecos = totalPrecos / quantReg;
        String TotalString = df.format (totalPrecos); // deve retornar a string "1.234,56"     
        return TotalString;
    }
	
	public String retornMediaCompra (List <Combustivel> combustiveis){
		DecimalFormat df = new DecimalFormat ("#0.000", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
		int quantReg = 0;
		double totalPrecos = 0;
		for (int i = 0; i < combustiveis.size(); i++) {
			double preco1 = 0;
			String valorCompra = combustiveis.get(i).getValoDaCompra();
			//Tratar se tem preço de venda preenchido
			if (!valorCompra.equals("")){
		        try {
		        	preco1 = df.parse (valorCompra).doubleValue(); // isto deve dar o número "1234.56"	 
		        	//preco1 = Double.parseDouble(valorVenda);    
		            totalPrecos = totalPrecos + preco1;
		            quantReg++;
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
			}
		}
		totalPrecos = totalPrecos / quantReg;
        String TotalString = df.format (totalPrecos); // deve retornar a string "1.234,56"     
        return TotalString;
    }
    
    
	
	/**Código para retornar historico de combustivel
	* @author Diego Rangel
	* @return String - Retorna uma lista de historico
	*/
	@GetMapping( path="combustiveis/")
	public ResponseEntity<?> getHitoricoCombustivel(){
		List <Combustivel> combustiveis = cRepository.findAll();
		List <String> historico = new ArrayList<>();
		for (int i = 0; i < combustiveis.size(); i++) {
			historico.add("Data da Coleta:"+combustiveis.get(i).getDataDaColeta() + " Preço de Venda: "+combustiveis.get(i).getValorDaVenda());
		}
		return new ResponseEntity<>(historico	,HttpStatus.OK);
	}
	
	
	/**Combustivel importado por sigla de regiao
	* @author Diego Rangel
	* @return String - Retorna uma lista de combustivel
	*/
	@GetMapping( path="combustiveis/importadoPorRegiao/{regiao}")
	public ResponseEntity<?> getImportadoPorSigla(@PathVariable("regiao") String regiao){
		List <Combustivel> combustiveis = cRepository.findAll();
		List <Combustivel> combustiveisSigla = new ArrayList<>();
		for (int i = 0; i < combustiveis.size(); i++) {
			if (combustiveis.get(i).getRegiao().equals(regiao.toUpperCase())) {
				combustiveisSigla.add(combustiveis.get(i));
			}
		}
		return new ResponseEntity<>(combustiveisSigla,HttpStatus.OK);
	}
	
	/**Combustivel Agrupado por Bandeira
	* @author Diego Rangel
	* @return String - Retorna uma lista de combustivel agrupado por bandeira
	*/
	@GetMapping( path="combustiveis/AgrupadoPorDistribuidora")
	public ResponseEntity<?> getAgrupadoPorDistribuidora(){
		List <Combustivel> combustiveis = cRepository.ReturnPorBandeira();
		return new ResponseEntity<>(combustiveis	,HttpStatus.OK);
	}
	
	/**Combustivel Agrupado por Data da Coleta
	* @author Diego Rangel
	* @return String - Retorna uma lista de combustivel agrupado por data da coleta
	*/
	@GetMapping( path="combustiveis/AgrupadoPorDataDaColeta")
	public ResponseEntity<?> getAgrupadoPorDataDaColeta(){
		List <Combustivel> combustiveis = cRepository.ReturnPorDataDaColeta();
		return new ResponseEntity<>(combustiveis	,HttpStatus.OK);
	}


}
