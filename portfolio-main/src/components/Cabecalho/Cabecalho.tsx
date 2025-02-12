import Link from 'next/link';

const Cabecalho = () => {
  return (
    <header className="bg-gray-900 text-white py-4 shadow-lg">
      <nav className="container mx-auto flex justify-between items-center px-4">
        <h1 className="text-3xl font-bold text-red-600">FIAP</h1>
        <div className="flex space-x-6">
          <Link href="/" className="text-lg font-medium hover:text-red-500 transition duration-300">
            Home
          </Link>
          <Link href="/avaliacoes" className="text-lg font-medium hover:text-red-500 transition duration-300">
            Avaliacoes
          </Link>
        </div>
      </nav>
    </header>
  );
};

export default Cabecalho;
