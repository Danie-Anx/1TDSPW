import Link from 'next/link';

export default function Custom404() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 text-center">
      <h1 className="text-6xl font-bold text-red-600">404</h1>
      <h2 className="text-2xl mt-4">Página Não Encontrada</h2>
      <p className="mt-2">Desculpe, mas a página que você está procurando não existe.</p>
      <Link href="/" className="mt-4 inline-block bg-red-600 text-white py-2 px-4 rounded hover:bg-red-700">
        Voltar para a Home
      </Link>
    </div>
  );
}
