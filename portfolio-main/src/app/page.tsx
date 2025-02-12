"use client";

import { useState } from 'react';
import { alunos, Disciplina } from '../data/alunos';

function calcularMediaComRegraDeNegocio(disciplina: Disciplina) {

  // Ordena as notas dos CPs e descarta a menor
  const cps = [disciplina.cp1.nota, disciplina.cp2.nota, disciplina.cp3.nota];
  cps.sort((a, b) => a - b); // Ordena em ordem crescente
  const mediaCP = Math.round(((cps[1] + cps[2]) / 2) * 0.2);

  // Nota de GS com peso de 60%
  const mediaGS = Math.round(disciplina.gs.nota * 0.6);

  // Média das duas notas de CS com peso de 20%
  const mediaCS = Math.round(((disciplina.cs1.nota + disciplina.cs2.nota) / 2) * 0.2);

  // Soma das notas ponderadas
  return (mediaCP + mediaGS + mediaCS);
}

export default function Home() {
  
  const [selectedAluno, setSelectedAluno] = useState<typeof alunos[0] | null>(null);
  const [showDisciplinas, setShowDisciplinas] = useState(false); // Estado para mostrar/esconder disciplinas

  const openBoletim = (aluno: typeof alunos[0]) => {
    setSelectedAluno(aluno);
  };

  const closeBoletim = () => {
    setSelectedAluno(null);
  };

  return (
    <div className="container mx-auto p-6 min-h-screen flex flex-col home-background"> {/* Adicionando classe para fundo */}
      <h1 className="text-4xl font-bold mb-4 text-center" style={{ color: 'white', textShadow: '2px 2px 4px rgba(255, 0, 0, 0.7)' }}>
          PORTFOLIO
      </h1>

      <h2 
        className="disciplinas-titulo mb-4 cursor-pointer" 
        onClick={() => setShowDisciplinas(!showDisciplinas)} // Toggle para mostrar/esconder disciplinas
      >
        DISCIPLINAS
      </h2>
      {showDisciplinas && (
        <ul className="list-disc pl-6 mb-6">
          {alunos[0].disciplinas.map((disciplina, index) => (
            <li key={index} className="text-lg">{disciplina.nome}</li>
          ))}
        </ul>
      )}

      <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mb-auto">
        {alunos.map((aluno) => (
          <div key={aluno.id} className="card-aluno">
            <img src={aluno.imagem} alt={aluno.nome} className="h-32 w-32 object-cover rounded-full mx-auto mb-4" />
            <h2 className="text-2xl font-bold text-gray-800 mb-2">{aluno.nome}</h2>
            <p className="mb-4">
              <strong>Resumo das Notas:</strong><br />
              Média Ponderada: {aluno.disciplinas ? calcularMediaComRegraDeNegocio(aluno.disciplinas[0]) : "N/A"}
            </p>
            <button
              onClick={() => openBoletim(aluno)}
              className="bg-red-600 text-white py-2 px-4 rounded-lg hover:bg-red-700"
            >
              Ver Boletim
            </button>
          </div>
        ))}
      </div>

      {selectedAluno && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white rounded-lg p-8 w-full max-w-4xl">
            <button onClick={closeBoletim} className="text-gray-600 hover:text-gray-800 float-right">
              X
            </button>
            <h2 className="text-3xl font-bold text-gray-800 mb-4">
              Boletim de {selectedAluno.nome}
            </h2>
            <table className="w-full text-left">
              <thead>
                <tr>
                  <th className="py-2 px-4 border-b">Disciplina</th>
                  <th className="py-2 px-4 border-b">CP</th>
                  <th className="py-2 px-4 border-b">GS</th>
                  <th className="py-2 px-4 border-b">CS</th>
                  <th className="py-2 px-4 border-b">FA</th>
                  <th className="py-2 px-4 border-b">MD</th>
                  <th className="py-2 px-4 border-b">PR (%)</th>
                </tr>
              </thead>
              <tbody>
                {selectedAluno.disciplinas.map((disciplina, index) => (
                  <tr key={index} className="hover:bg-gray-100">
                    <td className="py-2 px-4 border-b">{disciplina.nome}</td>
                    <td className="py-2 px-4 border-b text-center">
                      {Math.round(disciplina.cp1.nota)}, {Math.round(disciplina.cp2.nota)}, {Math.round(disciplina.cp3.nota)}
                    </td>
                    <td className="py-2 px-4 border-b text-center">
                      {Math.round(disciplina.gs.nota)} <span className="text-sm text-gray-500">({disciplina.gs.data})</span>
                    </td>
                    <td className="py-2 px-4 border-b text-center">
                      {Math.round(disciplina.cs1.nota)}, {Math.round(disciplina.cs2.nota)}
                    </td>
                    <td className="py-2 px-4 border-b text-center">{disciplina.fa}</td>
                    <td className="py-2 px-4 border-b text-center">{Math.round(disciplina.md)}</td>
                    <td className="py-2 px-4 border-b text-center">{Math.round(disciplina.pr)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
            <div className="mt-4 text-center">
              <button onClick={closeBoletim} className="bg-red-600 text-white py-2 px-4 rounded-lg hover:bg-red-700">
                Fechar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
