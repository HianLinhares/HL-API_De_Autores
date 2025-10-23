package Hian.Linhares.HL_API_De_Autores.controller;

import Hian.Linhares.HL_API_De_Autores.dto.AutorDTO;
import Hian.Linhares.HL_API_De_Autores.dto.ErroResposta;
import Hian.Linhares.HL_API_De_Autores.exceptions.RegistroDuplicados;
import Hian.Linhares.HL_API_De_Autores.model.Autor;
import Hian.Linhares.HL_API_De_Autores.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
//http://localhost:8080/autores
@Tag(name="Autores")
@Slf4j
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }


    //@RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "Salvar autor", description = "cadastrar novo autor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "409", description = "Autor já cadastrado")
    })
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
        var autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);
        //http://localhost:8080/autores/"f22fd387-54de-4943-bcc7-3776ab571964"
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Buscar autor pelo ID", description = "Busca no banco de dados pelo autor através do fornecimento de API")
    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar Autores", description = "Exclusão de autores através do fornecimento do ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutores(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.excluirPorId(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Pesquisa de autores", description = "Pesquisa pelo autor através do fornecimento do nome ou nacionalidade")
    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisarAutores(@RequestParam(value = "nome", required = false) String nome,
                                                           @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
       log.trace("Pesquisa autores");
       log.debug("Pesquisa autores");
       log.info("Pesquisa autores");
       log.warn("Pesquisa autores");
       log.error("Pesquisa autores");
        List<Autor> resultado = service.pesquisaDeAutor(nome, nacionalidade);
        List<AutorDTO> lista = resultado.stream().map(autor -> new AutorDTO(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade())).collect(Collectors.toList());
        return ResponseEntity.ok(lista);

    }

    @Operation(summary = "Edição do autor", description = "Edição do autor através do fornecimento do ID")
    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody AutorDTO dto) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());
        service.atualizar(autor);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Listar todos os autores", description = "Listar todos os autores presentes no banco de dados")
    @GetMapping("/listartodos")
    public ResponseEntity<List<Autor>> listarTodos() {
        List<Autor> usuarios = service.listarTodos();
        return ResponseEntity.ok(usuarios);
    }



























    /*
      TRATATIVA PARA NÃO PERMITIR A INSERÇÃO DE AUTORES DUPLICADOS


    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor) {
        try {
            var autorEntidade = autor.mapearParaAutor();
            service.salvar(autorEntidade);
            //http://localhost:8080/autores/"f22fd387-54de-4943-bcc7-3776ab571964"
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicados e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

     */







}
