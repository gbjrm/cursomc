package com.gabrielmatos.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabrielmatos.cursomc.domain.Cliente;
import com.gabrielmatos.cursomc.dto.ClienteDTO;
import com.gabrielmatos.cursomc.repositories.ClienteRepository;
import com.gabrielmatos.cursomc.services.execeptions.DataIntegrityException;
import com.gabrielmatos.cursomc.services.execeptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj =  repo.findById(id);
		
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
		
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente porque há entidades relacionadas.");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();	
	}
	
	public Page<Cliente>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO (ClienteDTO objDto) {
		return  new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	/* Atualizar o objeto "newObj" com os novos dados que vieram do "obj". Nesse
	 * caso apenas nome e email.*/
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
