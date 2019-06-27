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
	
	public String retornMedia (List <Combustivel> combustiveis){
		DecimalFormat df = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
		int quantReg = 0;
		double totalPrecos = 0;
		for (int i = 0; i < combustiveis.size(); i++) {
			double preco1 = 0;
	        try {
	        	preco1 = df.parse (combustiveis.get(i).getValorDaVenda()).doubleValue(); // isto deve dar o número "1234.56"	           
	            totalPrecos = totalPrecos + preco1;
	            quantReg++;
	        } catch (ParseException ex) {
	            ex.printStackTrace();
	        }
			//System.out.println("Municipio: "+combustiveis.get(i).getMunicipio()+"\nPreço: "+combustiveis.get(i).getValorDaVenda()+"\n");
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
	
	
	/**Código combustivel importado por sigla de regiao
	* @author Diego Rangel
	* @return String - Retorna uma lista de combustivel
	*//*
	@GetMapping( path="combustiveis/{regiao}")
	public ResponseEntity<?> getImportadoPorSigla(@PathVariable("regiao") String regiao){
		List <Combustivel> combustiveis = cRepository.findAll();
		List <Combustivel> combustiveisSigla = new ArrayList<>();
		for (int i = 0; i < combustiveis.size(); i++) {
			if (combustiveis.get(i).equals(regiao)) {
				combustiveisSigla.add(combustiveis.get(i));
			}
		}
		return new ResponseEntity<>(combustiveisSigla,HttpStatus.OK);
	}
	*/
	
	

	
	
	

}
