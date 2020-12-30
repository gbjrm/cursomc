package com.gabrielmatos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielmatos.cursomc.domain.Categoria;
import com.gabrielmatos.cursomc.domain.Cidade;
import com.gabrielmatos.cursomc.domain.Cliente;
import com.gabrielmatos.cursomc.domain.Endereco;
import com.gabrielmatos.cursomc.domain.Estado;
import com.gabrielmatos.cursomc.domain.Produto;
import com.gabrielmatos.cursomc.domain.enums.TipoCliente;
import com.gabrielmatos.cursomc.repositories.CategoriaRepository;
import com.gabrielmatos.cursomc.repositories.CidadeRepository;
import com.gabrielmatos.cursomc.repositories.ClienteRepository;
import com.gabrielmatos.cursomc.repositories.EnderecoRepository;
import com.gabrielmatos.cursomc.repositories.EstadoRepository;
import com.gabrielmatos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria categoria1 = new Categoria(null, "Infomática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impresora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList (produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "mariasilva@gmail.com", "13107722073", TipoCliente.PESSOAFISICA);
		
		cl1.getTelefones().addAll(Arrays.asList("140927-4122", "2025550104"));
		
		Endereco e1 = new Endereco(null, "Rua São Raimundo", "963", "Apto 434", "João Eduardo II", "69911-504", cl1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "132", "Sala 8080", "Centro", "68903-518", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
