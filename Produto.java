public class Produto {
    private String nome;
    private String categoria;
    private double preco;
    private int quantidade;

    public Produto(String nome, String categoria, double preco, int quantidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;

        // Aqui usamos o setter para validar as regras do estoque.
        setQuantidade(quantidade);
    }

/*
 ** Getters/Setters 
Com private + getters/setters, o objeto vira uma caixa protegida:
    você lê com get()
    você altera com set() (que valida regras)
 **Ganho: dados consistentes e sistema mais difícil de quebrar. 
*/


    //getters - ver ou ler os dados privados com segurança.
    public String getNome(){
       return nome;
    }

    public String getCategoria(){
        return categoria;
    }

    public double getPreco(){
        return preco;
    }

    public int getQuantidade(){
        return quantidade;
    }

    // setters (alterar dados privados com segurança). 
    // vamos escrever aqui a regra de negócio para quantidade.
    public void setQuantidade(int quantidade){
        if  (quantidade < 0) {
            this.quantidade = 0;
        } else {
            this.quantidade = quantidade;
        }
    }
    
    /*
     ** Método de negócio - Regras de negócio do produto.
        estaDisponivel() - Responde se o produto  pode ser vendido.
     ** Por que usar um método e não um atributo boolean?
        Porque a disponibilidade depende de estoque - quantidade.
        Desta forma evitamos inconsistência do tipo:
        quantidade = 0, mas disponivel = true (da erro de lógica).
    */
    public boolean estaDisponivel(){
        return quantidade > 0;
    }

    /*
    **Método - getStatusEstoque()
    Classificar o estoque (baixo/normal/alto). 
    */
   public String getStatusEstoque(){
    // se estoque não tem nada - Sem estoque
    if (quantidade == 0) return "Sem estoque";

    // se estoque até 5 unidades - Estoque baixo
    if (quantidade <= 5) return "Estoque baixo";

    // se estoque tem até 20 unidades - Estoque normal
    if (quantidade <= 20) return "Estoque normal";

    // se estoque for maior que 20 unidades - Estoque alto 
    return "Estoque Alto";
}

public double calculaValorEmEstoque(){
    return preco * quantidade;
}

    /*
    ** exibirResumo
    Tráz resultado de todas as informações do produto
    */
   public void exibirResumo(){

    System.out.println("Categoria: " + categoria);

    System.out.println("Produto: " + nome);

    /*
    ** printf - imprime com formatação
    ** %2.f - float/double - imprimir com duas casas decimais
    ** %n - quebra de linha 
    */
    System.out.printf("Preço: R$ %.2f%n", preco);

    System.out.println("Quantidade: " + quantidade);

    /*
    ** Operador
    Criamos uma condição ? - valorSeVerdadeiro:valorSeFalso
    */
    System.out.println("Diponível: " + (estaDisponivel() ? "Sim" : "Não"));

    System.out.println("Status do estoque: " + getStatusEstoque());

    /*
    ** printf - imprime com formatação
    ** %2.f - float/double - imprimir com duas casas decimais
    ** %n - quebra de linha 
    */
    System.out.printf("Valor total em estoque: R$ %.2f%n", calculaValorEmEstoque());
   }

}
