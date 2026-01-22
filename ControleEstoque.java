import java.util.Scanner;


public class ControleEstoque {


        public static String lerTextoNaoNulo(Scanner caixaDeTexto, String mensagem ) {
            
            // while(true) - repete até o usuário digitar corretamente.
            while (true) {
                
                // pedindo para  o cursor ficar na frente da pergunta
                System.out.print(mensagem);

                // leia a linha inteira sem espaços
                String texto = caixaDeTexto.nextLine();

                /*
                ** Validação
                retirar espaços das pontas e verificar se o campo esta vazio
                */
            if (texto != null && !texto.trim().isEmpty()) {
                    return texto.trim();
            } 
            System.out.println("Entrada inválida. Digite um texo não vazio.");
            }
        }
            /*
            ** Inteiro com mínimo de permissão - Quantidade proibindo valor negativo
            */
        private static int lerInt(Scanner caixaDeTexto, String mensagem, int minPermitido){
                
                while (true) {
                    // pedindo para  o cursor ficar na frente da pergunta
                    System.out.print(mensagem);

                    // Ler como texto primeiro para n'ao quebrar o scanner
                    String texto = caixaDeTexto.nextLine();

                    // Depois convertemos para int
                    // Try: Plano A (principal) - insere o código que pode dar erro 
                    // Catch: Plano B - Tratamento  
                    try {                
                        int valor = Integer.parseInt(texto.trim());
                        
                        if (valor < minPermitido) {
                            System.out.println("Valor inválido. O mínimo permito é: " + minPermitido);
                            continue;
                        }
                        return valor;

                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Digite um número inteiro (ex: 0, 10, 25..)");
                        // TODO: handle exception
                    }
                }

        }

        private static double lerDouble(Scanner caixaDeTexto, String mensagem, double minPermitido){

            while (true) {
                
                System.out.print(mensagem);
                String texto = caixaDeTexto.nextLine();

                try {
                    double valor = Double.parseDouble(texto.trim().replace(",", "."));

                    if (valor < minPermitido) {
                        System.out.println("Valor inválido. O mínimo permitido é: " + minPermitido);
                        continue;
                    }

                    return valor;
                    
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número (ex: 25.50, 11.38...)");
                    // TODO: handle exception
                }
            }

        }

        /*
        ** Metodo cadastrar produto
        Criado para evitar copiar e colar o mesmo codigo 3 vezes, ja que vamos cadastrar varios produto.
        */
        private static Produto cadastrarProduto(Scanner caixaDeTexto, String categoria){

            System.out.println("\n===  Cadastro: " + categoria + " ===");
            
            // Nome - texto obrigatório
            String nome = lerTextoNaoNulo(caixaDeTexto, "Nome do produto (campo obrigatório): ");

            // Preço - double >= 0
            double preco = lerDouble(caixaDeTexto, "O preço do produto (ex: 25.50 ou 25,50): R$ ", 0.0);
            
            // Quantidade - int >= 0
            int quantidade = lerInt(caixaDeTexto, "Quantidade em estoque (ex: número inteiro >=0): ", 0);
        
            /*
            ** Construtor
            Aqui usamos o construtor para o Produto nascer pronto, com os dados essenciais
            */
            return new Produto(nome, categoria, preco, quantidade);
        }

        /*
        ** Comparação Final
        Compara dois produtos
        Quem tem mais quantidade e quem tem maior valor total em estoque
        */
        private static void compararDoisProdutos(Produto a, Produto b){

            System.out.println("\n=== Comparação Final ===");

            System.out.println("\n--- Comparação de Quantidade em Estoque ---");

            // Utilizamos na condição getQuantidade() - getter porque quantidade é private
            if (a.getQuantidade() > b.getQuantidade()) {
                System.out.println(a.getNome() + " tem mais estoque que " + b.getNome());
            } else if (b.getQuantidade() > a.getQuantidade()) {
                System.out.println(b.getNome() + " tem mais estoque que " + a.getNome());
            } else {
                System.out.println("Os dois produtos possui a mesma quantidade em estoque.");
            }

            System.out.println("\n--- Comparação de Valor total em Estoque ---");

            // Cada produto sabe calcular seu total - método da classe Produto
            double totalA = a.calculaValorEmEstoque();
            double totalB = b.calculaValorEmEstoque();

            if (totalA > totalB) {
                System.out.printf("%s tem maior valor em estoque (R$ %.2f%n)", a.getNome(), totalA);
            } else if (totalB > totalA) {
                System.out.printf("%s tem maior valor em estoque (R$ %.2f%n)", b.getNome(), totalB);
            } else {
                System.out.println("Os dois produtos possui o mesmo valor em estoque.");
            }

        }

        /*
        ** Main - inicio do programa
        */
        public static void main(String[] args) {
            // Scanner le do teclado/terminal
            Scanner caixaDeTexto = new Scanner(System.in);

            System.out.println("Sistema de Controle de Estoque - T1 2601 - AS");
            System.out.println("Você vai cadastrar 3 produtos e ver depois o resumo um confronto.\n");

            // Declaramos variaveis do tipo produto para inserir tipos diferentes
            Produto comida1 = cadastrarProduto(caixaDeTexto, "Comida");
            Produto produtoLimpeza1 = cadastrarProduto(caixaDeTexto, "Produto de Limpeza");
            Produto produtoHigienePessoal1 = cadastrarProduto(caixaDeTexto, "Produto Higiene Pessoal");

            System.out.println("\n=== Resumo dos Produtos Cadastrados ===");

            System.out.println("Produto 1 - Comida");
            comida1.exibirResumo();
            System.out.println("\n");

            System.out.println("Produto 2 - Limpeza");
            produtoLimpeza1.exibirResumo();
            System.out.println("\n");

            System.out.println("Produto 3 - Higiene Pessoal");
            produtoHigienePessoal1.exibirResumo();

            /* 
            ** Confronto final
            Aqui comparamos comida1 vs produtoLimpeza1 - você pode trocar
            */
            compararDoisProdutos(comida1, produtoLimpeza1);

            // Fechamos Scanner é uma boa prática porque libera o recurso
            caixaDeTexto.close();
        }
}
