# Backend Java do projeto EnergyX

## Desenvolvido por:

- Caroline S. do Amaral - RM 558012
- Leonardo Menezes Parpinelli Ribas - RM 557908
- Robert D. S. Coimbra - RM 555881

## Instruções para execução do projeto

É necessário ter o JDK 21, o Maven e o IntelliJ IDEA instalados na máquina.

Para testar a aplicação não é necessário executar scripts SQL de criação já que o projeto Java cria as tabelas sozinho. Porém, no diretório `src/main/resources/sql` há um arquivo `dml.sql` para inserir os dados de teste.

### Executando a aplicação

1. Defina as variáveis de ambiente ORACLE_DB_USERNAME e ORACLE_DB_PASSWORD com os respectivos usuário e senha do banco de dados Oracle.
2. Abra este diretório no IntelliJ IDEA.
3. Abra o arquivo pom.xml e certfique que o projeto foi reconhecido como um projeto Maven e as dependências foram baixadas.
4. Execute a classe App (no pacote br.com.portosafecar.application) para iniciar a aplicação.
5. Acesse a aplicação em http://localhost:8080.

## Testando a API

Para testar a API utilize a coleção de requests do Postman que pode ser importada do arquivo que está no diretório `src/main/resources/postman`.

## Links úteis

Link do repositorio:
https://github.com/Ribaas/energyx-java

[Vídeo de apresentação](https://fiapcom-my.sharepoint.com/:v:/g/personal/rm557908_fiap_com_br/EYWE072Y8QBGrKkfafYRW7wBXqBTDHFfNJ8AqGWTMg1iag?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D&e=AOTFuH)