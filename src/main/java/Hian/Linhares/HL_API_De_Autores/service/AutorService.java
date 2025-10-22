package Hian.Linhares.HL_API_De_Autores.service;

import Hian.Linhares.HL_API_De_Autores.model.Autor;
import Hian.Linhares.HL_API_De_Autores.repository.AutorRepository;
import Hian.Linhares.HL_API_De_Autores.validator.AutorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;

    public AutorService(AutorRepository repository, AutorValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

    public void atualizar(Autor autor){
        if(autor.getId()==null){
            throw new IllegalArgumentException("Para atualizar é necessário que o autor já esteja salvo na base");
        }
         repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void excluirPorId(Autor autor){
        repository.delete(autor);
    }

    public List<Autor> pesquisaDeAutor(String nome, String nacionalidade){
        if (nome!= null && nacionalidade != null){
            return repository.findByNomeAndNacionalidade(nome,nacionalidade);
        }if(nome!=null){
            return repository.findByNome(nome);
        }if (nacionalidade!=null){
            return  repository.findByNacionalidade(nacionalidade);
        }
        return repository.findAll();
    }

    public List<Autor> listarTodos(){
        return  repository.findAll();
    }




}
