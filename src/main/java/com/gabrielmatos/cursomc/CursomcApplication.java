package com.gabrielmatos.cursomc;

import java.text.SimpleDateFormat;
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
import com.gabrielmatos.cursomc.domain.ItemPedido;
import com.gabrielmatos.cursomc.domain.Pagamento;
import com.gabrielmatos.cursomc.domain.PagamentoComBoleto;
import com.gabrielmatos.cursomc.domain.PagamentoComCartao;
import com.gabrielmatos.cursomc.domain.Pedido;
import com.gabrielmatos.cursomc.domain.Produto;
import com.gabrielmatos.cursomc.domain.enums.EstadoPagamento;
import com.gabrielmatos.cursomc.domain.enums.TipoCliente;
import com.gabrielmatos.cursomc.repositories.CategoriaRepository;
import com.gabrielmatos.cursomc.repositories.CidadeRepository;
import com.gabrielmatos.cursomc.repositories.ClienteRepository;
import com.gabrielmatos.cursomc.repositories.EnderecoRepository;
import com.gabrielmatos.cursomc.repositories.EstadoRepository;
import com.gabrielmatos.cursomc.repositories.ItemPedidoRepository;
import com.gabrielmatos.cursomc.repositories.PagamentoRepository;
import com.gabrielmatos.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido p1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, e1);
		Pedido p2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, p1, 6);
		p1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, p2, sdf.parse("20/10/2017 00:00"), null);
		p2.setPagamento(pagto2);
		
		cl1.getPedidos().addAll(Arrays.asList(p1, p2));
		
		pedidoRepository.saveAll(Arrays.asList(p1,p2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(p1, produto1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(p1, produto3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(p2, produto2, 100.00, 1, 800.00);
		
		p1.getItens().addAll(Arrays.asList(ip1,ip2));
		p2.getItens().addAll(Arrays.asList(ip3));
		
		produto1.getItens().addAll(Arrays.asList(ip1));
		produto2.getItens().addAll(Arrays.asList(ip3));
		produto3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
	}

}
