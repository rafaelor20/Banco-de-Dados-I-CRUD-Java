# Banco-de-Dados-I-CRUD-Java

Rota que registra usuario:

@RequestMapping("/usuarios")
{
    "cpf": "13", // cpf unique
    "nome": "rafael",
    "dataNascimento": "1992-12-24" // yyyy-mm-dd
}

Rota que pega informações de um usuário através do cpf:

@GetMapping("/{cpf}") // cpf é um numero inteiro
