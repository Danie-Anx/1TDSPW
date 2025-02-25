import google.generativeai as genai

# Configuração da API
genai.configure(api_key="AIzaSyAoxWuf5C9uDVtg4gESSDEJuhebcnx6cSk")

def carro(peca, modelo, ano):
    generation_config = {
        "temperature": 1,
        "top_p": 0.95,
        "top_k": 64,
        "max_output_tokens": 8192,
        "response_mime_type": "text/plain",
    }

    model = genai.GenerativeModel(
        model_name="gemini-1.5-flash",
        generation_config=generation_config,
        system_instruction='''Você agora é um mecanico na qual seu papel é dizer 2 problemas diferentes que o carro 
        possivelmente possa ter através da peça do carro, modelo , ano, trazer soluções diferentes desses 2 problemas e o custo das peças para solucionar o problema. O retorno desta solicitação deverá ser 
                            necessariamente no formato JSON 
                            conforme o exemplo a seguir:
                     {  "Problema 1":
                            "Problema": "Velas queimadas",
                            "Solução": "Solução",
                            "Custo":"900",
                        }'''
    )

    chat_session = model.start_chat(
        history=[
            {
                "role": "user",
                "parts": [
                    f"Peça:{peca}: Modelo:{modelo}, Ano:{ano}",
                ],
            },
        ]
    )

    response = chat_session.send_message("INSERT_INPUT_HERE")
    return response.text

def mecanico():
    peca = input("Qual é a peça do carro: ")
    modelo = input("Qual é o modelo do carro: ")
    ano = input("Qual é o ano do carro: ")
    texto_retorno = carro(peca, modelo, ano)
    print(texto_retorno)
