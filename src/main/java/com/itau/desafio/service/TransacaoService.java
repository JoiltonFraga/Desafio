package com.itau.desafio.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.itau.desafio.model.Transacao;

@Service
public class TransacaoService {

	public List<Transacao> transacao = new ArrayList<>();
	
	
	public ResponseEntity<Transacao> trasferir ( @Valid @RequestBody Transacao adicao){
		
		transacao.add(adicao);
		adicao.setDataHora(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	

	public DoubleSummaryStatistics  getUltimoMinuto(){
		DoubleSummaryStatistics estatisticas = new DoubleSummaryStatistics();
		
		transacao.stream().filter(result->result.getDataHora().isAfter(LocalDateTime.now().minusSeconds(60))).
		forEach(resp -> estatisticas.accept(resp.getValor()));
		return  estatisticas;
	}
	 
	
	public ResponseEntity<Transacao> limparLista(){
		transacao.clear();
		return ResponseEntity.noContent().build();
}
}
