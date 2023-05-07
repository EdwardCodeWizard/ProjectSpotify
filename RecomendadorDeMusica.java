import java.util.Scanner;

public class RecomendadorDeMusica {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Pedimos al usuario que ingrese su estado de ánimo
    System.out.println("¿Cómo te sientes? (feliz, triste, cansado, enojado, aburrido)");
    String estadoDeAnimo = scanner.nextLine();

    // Llamamos a la clase correspondiente según el estado de ánimo
    if (estadoDeAnimo.equals("feliz")) {
      RecomendadorDeMusicaFeliz recomendador = new RecomendadorDeMusicaFeliz();
      recomendador.recomendarCancion();
    } else if (estadoDeAnimo.equals("triste")) {
      RecomendadorDeMusicaTriste recomendador = new RecomendadorDeMusicaTriste();
      recomendador.recomendarCancion();
    } else if (estadoDeAnimo.equals("cansado")) {
      RecomendadorDeMusicaCansado recomendador = new RecomendadorDeMusicaCansado();
      recomendador.recomendarCancion();
    } else if (estadoDeAnimo.equals("enojado")) {
      RecomendadorDeMusicaEnojado recomendador = new RecomendadorDeMusicaEnojado();
      recomendador.recomendarCancion();
    } else if (estadoDeAnimo.equals("aburrido")) {
      RecomendadorDePodcasts recomendador = new RecomendadorDePodcasts();
      recomendador.recomendarPodcast();
    } else {
      System.out.println("Estado de ánimo no reconocido");
    }

    scanner.close();
  }
}