package com.gabrielmatos.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {
	
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
	
	public void instantiateTestDabase() throws ParseException {

		Categoria categoria1 = new Categoria(null, "Infomática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Came, Mesa e Banho");
		Categoria categoria4 = new Categoria(null, "Eletrônicos");
		Categoria categoria5 = new Categoria(null, "Jardinagem");
		Categoria categoria6 = new Categoria(null, "Decoração");
		Categoria categoria7 = new Categoria(null, "Perfumaria");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impresora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		Produto produto4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto produto5 = new Produto(null, "Toalha", 50.00);
		Produto produto6 = new Produto(null, "Colcha", 200.00);
		Produto produto7 = new Produto(null, "TV true color", 1200.00);
		Produto produto8 = new Produto(null, "Roçadeira", 800.00);
		Produto produto9 = new Produto(null, "Abajour", 100.00);
		Produto produto10 = new Produto(null, "Pendende", 180.00);
		Produto produto11 = new Produto(null, "Shampoo", 90.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList (produto2,produto4));
		categoria3.getProdutos().addAll(Arrays.asList (produto5,produto6));
		categoria4.getProdutos().addAll(Arrays.asList (produto1,produto2,produto3,produto7));
		categoria5.getProdutos().addAll(Arrays.asList (produto8));
		categoria6.getProdutos().addAll(Arrays.asList (produto9,produto10));
		categoria7.getProdutos().addAll(Arrays.asList (produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1,categoria4));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2, categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria1,categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2, categoria3, categoria4,
				categoria5,categoria6,categoria7));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3,produto4, produto5, produto6, produto7,produto8,produto9,produto10,produto11));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Gabriel Matos", "gabrielmouromatos@gmail.com", "13107722073", TipoCliente.PESSOAFISICA);
		
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
