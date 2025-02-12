"use client";

import { useEffect, useState } from "react";
import { fetchAvaliacoes, adicionarAvaliacao, atualizarAvaliacao, removerAvaliacao, Avaliacao } from "../../services/avaliacoesService";
import { FaEdit, FaTrashAlt } from "react-icons/fa"; // Importa os ícones de edição e remoção

export default function Avaliacoes() {
    const [avaliacoes, setAvaliacoes] = useState<Avaliacao[]>([]);
    const [novaAvaliacao, setNovaAvaliacao] = useState<Avaliacao>({ id: undefined, nome: '', nota: 0 });

    const carregarAvaliacoes = async () => {
        try {
            const data = await fetchAvaliacoes();
            setAvaliacoes(data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleAdicionarAvaliacao = async () => {
        try {
            const nova = await adicionarAvaliacao(novaAvaliacao);
            setAvaliacoes([...avaliacoes, nova]);
            setNovaAvaliacao({ id: undefined, nome: '', nota: 0 });
        } catch (error) {
            console.error(error);
        }
    };

    const handleEditarAvaliacao = (id: number) => {
        const avaliacaoParaEditar = avaliacoes.find(avaliacao => avaliacao.id === id);
        if (avaliacaoParaEditar) setNovaAvaliacao(avaliacaoParaEditar);
    };

    const handleAtualizarAvaliacao = async () => {
        if (novaAvaliacao.id !== undefined) {
            try {
                await atualizarAvaliacao(novaAvaliacao.id, novaAvaliacao);
                carregarAvaliacoes();
                setNovaAvaliacao({ id: undefined, nome: '', nota: 0 });
            } catch (error) {
                console.error(error);
            }
        }
    };

    const handleRemoverAvaliacao = async (id: number) => {
        try {
            await removerAvaliacao(id);
            setAvaliacoes(avaliacoes.filter(avaliacao => avaliacao.id !== id));
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        carregarAvaliacoes();
    }, []);

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6 text-white">Gerenciar Avaliações</h1>
            <div className="flex items-center space-x-2 mb-4">
                <input
                    placeholder="Nome"
                    value={novaAvaliacao.nome}
                    onChange={(e) => setNovaAvaliacao({ ...novaAvaliacao, nome: e.target.value })}
                    className="border border-gray-400 p-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
                <input
                    placeholder="Nota"
                    type="number"
                    value={novaAvaliacao.nota}
                    onChange={(e) => setNovaAvaliacao({ ...novaAvaliacao, nota: parseFloat(e.target.value) })}
                    className="border border-gray-400 p-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
                <button
                    onClick={novaAvaliacao.id ? handleAtualizarAvaliacao : handleAdicionarAvaliacao}
                    className="bg-blue-500 hover:bg-blue-600 text-white font-semibold px-4 py-2 rounded-lg transition duration-300"
                >
                    {novaAvaliacao.id ? "Atualizar Avaliação" : "Adicionar Avaliação"}
                </button>
            </div>

            <table className="min-w-full bg-gray-800 text-white shadow-md rounded-lg overflow-hidden">
                <thead>
                    <tr className="bg-gray-700">
                        <th className="py-3 px-4 text-left text-sm font-semibold uppercase tracking-wide">Nome</th>
                        <th className="py-3 px-4 text-left text-sm font-semibold uppercase tracking-wide">Nota</th>
                        <th className="py-3 px-4 text-left text-sm font-semibold uppercase tracking-wide">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {avaliacoes.map((avaliacao, index) => (
                        <tr key={avaliacao.id} className={`border-b ${index % 2 === 0 ? 'bg-gray-800' : 'bg-gray-700'} hover:bg-gray-600 transition duration-200`}>
                            <td className="py-3 px-4">{avaliacao.nome}</td>
                            <td className="py-3 px-4">{avaliacao.nota}</td>
                            <td className="py-3 px-4 flex space-x-4">
                                <button onClick={() => handleEditarAvaliacao(avaliacao.id!)} className="text-blue-400 hover:text-blue-300 font-semibold transition duration-300 flex items-center space-x-1">
                                    <FaEdit />
                                    <span>Editar</span>
                                </button>
                                <button onClick={() => handleRemoverAvaliacao(avaliacao.id!)} className="text-red-400 hover:text-red-300 font-semibold transition duration-300 flex items-center space-x-1">
                                    <FaTrashAlt />
                                    <span>Remover</span>
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
