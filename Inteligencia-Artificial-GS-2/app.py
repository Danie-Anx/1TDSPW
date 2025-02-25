from flask import Flask, request, jsonify
import joblib
import numpy as np

app = Flask(__name__)

# Carregar os modelos e o scaler
regressor = joblib.load('regressor_updated.pkl')
classifier = joblib.load('classifier_updated.pkl')
scaler = joblib.load('scaler_updated.pkl')

@app.route('/')
def home():
    return jsonify({"message": "API para Modelos de Regressão e Classificação"})

# Endpoint para Regressão
@app.route('/predict_regression', methods=['POST'])
def predict_regression():
    data = request.get_json()
    if 'Year' not in data:
        return jsonify({"error": "O campo 'Year' está ausente no corpo da requisição."}), 400
    try:
        year = np.array([data['Year']]).reshape(-1, 1)
        year_scaled = scaler.transform(year)
        prediction = regressor.predict(year_scaled)
        return jsonify({"predicted_kW_AC": prediction[0]})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# Endpoint para Classificação
@app.route('/predict_classification', methods=['POST'])
def predict_classification():
    data = request.get_json()
    if 'Year' not in data:
        return jsonify({"error": "O campo 'Year' está ausente no corpo da requisição."}), 400
    try:
        year = np.array([data['Year']]).reshape(-1, 1)
        year_scaled = scaler.transform(year)
        prediction = classifier.predict(year_scaled)
        return jsonify({"predicted_class": prediction[0]})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
