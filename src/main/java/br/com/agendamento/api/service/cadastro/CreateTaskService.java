package br.com.agendamento.api.service.cadastro;

import br.com.agendamento.api.constantes.ConstantTask;
import br.com.agendamento.api.dto.CreateTaskDto;
import br.com.agendamento.api.exceptions.InternalErrorException;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.CreateTaskModel;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.repository.CreateTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe de Serviço para criação de uma tarefa.
 * @author LeoVizeu
 */
@Service
public class CreateTaskService {

    final CreateTaskRepository createTaskRepository;

    @Autowired
    private StatusService statusService;

    public CreateTaskService(CreateTaskRepository createTaskRepository) {
        this.createTaskRepository = createTaskRepository;
    }

    @Transactional
    public CreateTaskModel cadastrarTarefa(CreateTaskDto createTaskDto) throws ValidacaoException, InternalErrorException {
        if (createTaskDto.getDataVencimento().isBefore(LocalDateTime.now()) || createTaskDto.getDataVencimento().equals(LocalDateTime.now())){
            throw new ValidacaoException("A data não pode ser menor ou igual a data atual!");
        }

        Status status = statusService.buscarStatusTarefa(ConstantTask.TAREFA_ATIVA);

        CreateTaskModel obj = new CreateTaskModel(null, createTaskDto.getTitulo(), createTaskDto.getDescricao(),
                       createTaskDto.getDataVencimento(), LocalDateTime.now(), false, status);
        try {
            return createTaskRepository.save(obj);
        }
        catch (Exception e){
            throw new InternalErrorException("Erro ao tentar acessar a base de dados!");
        }
    }

    public List<CreateTaskModel> findAll() {
        return createTaskRepository.findAll();
    }

    public Optional<CreateTaskModel> findById(Long id) {
        return createTaskRepository.findById(id);
    }

    @Transactional
    public void delete(CreateTaskModel createTaskModel) {
        createTaskRepository.delete(createTaskModel);
    }


}
