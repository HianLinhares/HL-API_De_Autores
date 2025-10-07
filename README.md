200 - OK
201 - Created
202 - Accepted (Requisição foi um sucesso e foi aceita)
204 - No Content (Cenário, exclusão de um usuário, foi um sucesso e não contem mais o sucesso)
400 - Bad Request (Erro por parte do cliente onde faltou alguma parametro ou existe algum parametro errado na requisição)
401 - Unauthorized (Erro na autenticação)
403 - Forbidden (Existe a autenticação, mas não existe a permissão para acessar tal recurso)
404 - Not found (Tentando acessar um recurso que não existe)
405 - Method Not Allowed (O servidor espera um metodo/verbo http diferente do solicitado)
409 - Conflict (Cenário: onde se tenta cadastrar um cliente já cadastrado, gerando um conflito)
422 - Unprocessable Entity (Cenário: erro nas validações, enviando cpf inválidos)
500 - Erro de Server (Falha no servidor)
