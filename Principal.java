
public class Principal {
    public static void main(String[] args){
        final var p1 = Jogador.of("Alisson");
        final var p2 = Jogador.of("Luan");
        final var p3 = Jogador.of("Vinicius");
        final var p4 = Jogador.of("Yago");

        final var sala = Sala.of(p1, ConfiguracaoDeAcesso.ofPrivate(4));

        sala.adicionarJogador(p2);
        sala.adicionarJogador(p3);
        sala.adicionarJogador(p4);

        sala.distribuirCartas();

        System.out.println("--------------");
        
        p1.getCartas().forEach(System.out::println);
        System.out.println(p1.getCartas().size());


        System.out.println("--------------");
        p2.getCartas().forEach(System.out::println);
        System.out.println(p2.getCartas().size());

        System.out.println("--------------");
        p3.getCartas().forEach(System.out::println);
        System.out.println(p3.getCartas().size());

        System.out.println("--------------");
        p4.getCartas().forEach(System.out::println);
        System.out.println(p4.getCartas().size());
    }
}
