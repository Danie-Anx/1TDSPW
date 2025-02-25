import re

def validar_email(email):
    """
    Valida se o email está no formato correto.
    Utiliza uma expressão regular para garantir o formato.

    Args:
        email (str): Email a ser validado.

    Returns:
        bool: True se o email for válido, False caso contrário.
    """
    padrao = r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
    return re.match(padrao, email) is not None


def validar_id(id):
    """
    Valida se o ID é numérico e positivo.

    Args:
        id (str): ID a ser validado.

    Returns:
        bool: True se o ID for válido, False caso contrário.
    """
    return id.isdigit() and int(id) > 0


def validar_nome(nome):
    """
    Valida se o nome contém apenas caracteres alfabéticos e espaços.

    Args:
        nome (str): Nome a ser validado.

    Returns:
        bool: True se o nome for válido, False caso contrário.
    """
    return bool(nome) and all(c.isalpha() or c.isspace() for c in nome)


def validar_numero(numero, min_val=None, max_val=None):
    """
    Valida se o valor fornecido é um número e, opcionalmente, dentro de uma faixa.

    Args:
        numero (str): Número a ser validado.
        min_val (float, optional): Valor mínimo permitido.
        max_val (float, optional): Valor máximo permitido.

    Returns:
        bool: True se o número for válido, False caso contrário.
    """
    try:
        num = float(numero)
        if min_val is not None and num < min_val:
            return False
        if max_val is not None and num > max_val:
            return False
        return True
    except ValueError:
        return False


def validar_senha(senha):
    """
    Valida se a senha atende aos critérios mínimos de segurança.
    Requer pelo menos 8 caracteres, com letras maiúsculas, minúsculas, números e símbolos.

    Args:
        senha (str): Senha a ser validada.

    Returns:
        bool: True se a senha for válida, False caso contrário.
    """
    if len(senha) < 8:
        return False
    if not any(c.islower() for c in senha):
        return False
    if not any(c.isupper() for c in senha):
        return False
    if not any(c.isdigit() for c in senha):
        return False
    if not any(c in "!@#$%^&*()-_=+[]{}|;:',.<>?/" for c in senha):
        return False
    return True
