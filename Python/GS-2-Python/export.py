import json
import csv
import logging

def exportar_para_json(conexao):
    """
    Exporta estimativas de consumo para um arquivo JSON.
    """
    try:
        cursor = conexao.cursor()
        cursor.execute("SELECT * FROM t_gs_estimativa_consumo")
        dados = cursor.fetchall()
        colunas = [desc[0] for desc in cursor.description]

        registros = [dict(zip(colunas, linha)) for linha in dados]
        with open("estimativas_consumo.json", "w", encoding="utf-8") as f:
            json.dump(registros, f, ensure_ascii=False, indent=4)
        print("Dados exportados para estimativas_consumo.json")
        logging.info("Dados exportados para JSON.")
    except Exception as e:
        logging.error(f"Erro ao exportar para JSON: {e}")
        print("Erro ao exportar para JSON:", e)

def exportar_para_csv(conexao):
    """
    Exporta estimativas de consumo para um arquivo CSV.
    """
    try:
        cursor = conexao.cursor()
        cursor.execute("SELECT * FROM t_gs_estimativa_consumo")
        dados = cursor.fetchall()
        colunas = [desc[0] for desc in cursor.description]

        with open("estimativas_consumo.csv", "w", newline="", encoding="utf-8") as f:
            writer = csv.writer(f)
            writer.writerow(colunas)  # Cabeçalho
            writer.writerows(dados)  # Dados
        print("Dados exportados para estimativas_consumo.csv")
        logging.info("Dados exportados para CSV.")
    except Exception as e:
        logging.error(f"Erro ao exportar para CSV: {e}")
        print("Erro ao exportar para CSV:", e)
