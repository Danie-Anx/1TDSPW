export interface Avaliacao {
    id?: number; // id opcional
    nome: string;
    nota: number;
}

// Função para buscar todas as avaliações
export async function fetchAvaliacoes(): Promise<Avaliacao[]> {
    const response = await fetch('/api/avaliacoes');
    if (!response.ok) {
        throw new Error('Erro ao buscar avaliações');
    }
    return response.json();
}

// Função para adicionar uma nova avaliação
export async function adicionarAvaliacao(novaAvaliacao: Omit<Avaliacao, 'id'>): Promise<Avaliacao> {
    const response = await fetch('/api/avaliacoes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(novaAvaliacao),
    });
    if (!response.ok) {
        throw new Error('Erro ao adicionar avaliação');
    }
    return response.json();
}

// Função para atualizar uma avaliação existente
export async function atualizarAvaliacao(id: number, dadosAtualizados: Partial<Avaliacao>): Promise<void> {
    const response = await fetch('/api/avaliacoes', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, ...dadosAtualizados }),
    });
    if (!response.ok) {
        throw new Error('Erro ao atualizar avaliação');
    }
}

// Função para remover uma avaliação
export async function removerAvaliacao(id: number): Promise<void> {
    const response = await fetch('/api/avaliacoes', {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id }),
    });
    if (!response.ok) {
        throw new Error('Erro ao remover avaliação');
    }
}
