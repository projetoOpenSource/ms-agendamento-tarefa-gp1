package br.com.agendamento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.constantes.ConstanteStatus;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.repository.StatusRepository;
/**
 * Classe de serviços de Status
 * @author moriartynho
 */
@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public Status buscarPorStatusETipoStatus(String tipoStatus, String status) throws ValidacaoException {
		return statusRepository.findByTipoStatusAndStatus(tipoStatus, status).orElseThrow(()-> new ValidacaoException("Status não encontrado"));
	}
	
	public Status buscarStatusUsuario(String status) throws ValidacaoException {
		return buscarPorStatusETipoStatus(ConstanteStatus.TIPO_USUARIO, status);
	}

}
