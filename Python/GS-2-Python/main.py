import logging
from database import conectar_bd
from crud import (
    inserir_usuario, excluir_usuario, atualizar_usuario, consultar_usuarios,
    inserir_dispositivo, atualizar_dispositivo, excluir_dispositivo, consultar_dispositivos,
    inserir_estimativa, consultar_estimativas, atualizar_estimativa, excluir_estimativa,
    inserir_sugestao, consultar_sugestoes, atualizar_sugestao, excluir_sugestao
)
from export import exportar_para_json, exportar_para_csv

# Configuração de logging
logging.basicConfig(
    filename="logs/app.log",
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

def menu_principal():
    """
    Menu principal do sistema, com opções de CRUD e exportação.
    """
    conexao = conectar_bd()
    if not conexao:
        logging.error("Conexão ao banco de dados falhou.")
        print("Erro ao conectar ao banco de dados. Verifique a configuração.")
        return

    opcoes = {
        # Usuários
        "1": ("Inserir Usuário", lambda: inserir_usuario(conexao)),
        "2": ("Atualizar Usuário", lambda: atualizar_usuario(conexao)),
        "3": ("Excluir Usuário", lambda: excluir_usuario(conexao)),
        "4": ("Consultar Usuários", lambda: consultar_usuarios(conexao)),

        # Dispositivos
        "5": ("Inserir Dispositivo", lambda: inserir_dispositivo(conexao)),
        "6": ("Atualizar Dispositivo", lambda: atualizar_dispositivo(conexao)),
        "7": ("Excluir Dispositivo", lambda: excluir_dispositivo(conexao)),
        "8": ("Consultar Dispositivos", lambda: consultar_dispositivos(conexao)),

        # Estimativas de Consumo
        "9": ("Inserir Estimativa de Consumo", lambda: inserir_estimativa(conexao)),
        "10": ("Consultar Estimativas de Consumo", lambda: consultar_estimativas(conexao)),
        "11": ("Atualizar Estimativa de Consumo", lambda: atualizar_estimativa(conexao)),
        "12": ("Excluir Estimativa de Consumo", lambda: excluir_estimativa(conexao)),

        # Sugestões de Economia
        "13": ("Inserir Sugestão de Economia", lambda: inserir_sugestao(conexao)),
        "14": ("Consultar Sugestões de Economia", lambda: consultar_sugestoes(conexao)),
        "15": ("Atualizar Sugestão de Economia", lambda: atualizar_sugestao(conexao)),
        "16": ("Excluir Sugestão de Economia", lambda: excluir_sugestao(conexao)),

        # Exportação de Dados
        "17": ("Exportar Estimativas para JSON", lambda: exportar_para_json(conexao)),
        "18": ("Exportar Estimativas para CSV", lambda: exportar_para_csv(conexao)),

        # Sair
        "19": ("Sair", lambda: exit())
    }

    while True:
        print("\n" + "=" * 60)
        print("                SISTEMA DE GESTÃO DE CONSUMO")
        print("=" * 60)

        # Exibição organizada por categorias
        print("\nUSUÁRIOS")
        print("-" * 60)
        print("[1] - Inserir Usuário")
        print("[2] - Atualizar Usuário")
        print("[3] - Excluir Usuário")
        print("[4] - Consultar Usuários")

        print("\nDISPOSITIVOS")
        print("-" * 60)
        print("[5] - Inserir Dispositivo")
        print("[6] - Atualizar Dispositivo")
        print("[7] - Excluir Dispositivo")
        print("[8] - Consultar Dispositivos")

        print("\nESTIMATIVAS DE CONSUMO")
        print("-" * 60)
        print("[9] - Inserir Estimativa de Consumo")
        print("[10] - Consultar Estimativas de Consumo")
        print("[11] - Atualizar Estimativa de Consumo")
        print("[12] - Excluir Estimativa de Consumo")

        print("\nSUGESTÕES DE ECONOMIA")
        print("-" * 60)
        print("[13] - Inserir Sugestão de Economia")
        print("[14] - Consultar Sugestões de Economia")
        print("[15] - Atualizar Sugestão de Economia")
        print("[16] - Excluir Sugestão de Economia")

        print("\nEXPORTAÇÃO DE DADOS")
        print("-" * 60)
        print("[17] - Exportar Estimativas para JSON")
        print("[18] - Exportar Estimativas para CSV")

        print("\nOUTRAS OPÇÕES")
        print("-" * 60)
        print("[19] - Sair")
        print("=" * 60)

        opcao = input("Escolha uma opção: ").strip()
        print("=" * 60)

        if opcao in opcoes:
            try:
                logging.info(f"Opção escolhida: {opcoes[opcao][0]}")
                opcoes[opcao][1]()
            except Exception as e:
                logging.error(f"Erro ao executar a opção '{opcoes[opcao][0]}': {e}")
                print(f"Erro: {e}")
        else:
            print("Opção inválida! Por favor, escolha novamente.")
            print("=" * 60)

if __name__ == "__main__":
    menu_principal()
