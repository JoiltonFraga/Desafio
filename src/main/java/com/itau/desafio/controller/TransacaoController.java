package com.itau.desafio.controller;


import java.util.DoubleSummaryStatistics;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itau.desafio.model.Transacao;
import com.itau.desafio.service.TransacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/transacoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value="API REST Itaú")
public class TransacaoController {

	
	@Autowired
	private TransacaoService transacaoService;

	
	@PostMapping
	@ApiOperation(value="Faz uma transferência.")
	public ResponseEntity<Transacao> fazerTrasferencia( @Valid @RequestBody Transacao adicao){
		return transacaoService.trasferir(adicao);
	}
	
	@GetMapping("/estatisticas")
	@ApiOperation(value="Mostra as transações que aconteceram no período de 1 minuto, apos a requisição do método.")
	private ResponseEntity<DoubleSummaryStatistics> getEstatisticas(){
		
		return ResponseEntity.status(HttpStatus.OK).body(transacaoService.getUltimoMinuto());
	}
	
	@DeleteMapping
	@ApiOperation(value="Limpa todos os dados das transferências.")
	private ResponseEntity<Transacao> delete(){
		return transacaoService.limparLista();
		
	}
}
