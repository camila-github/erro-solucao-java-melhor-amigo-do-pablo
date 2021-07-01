import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MelhorAmigoPablo {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String linhaDeEntrada = br.readLine();
      List<Participante> participantes = new ArrayList<>();
    
      while (!linhaDeEntrada.equals("FIM")) {
        var participante = new Participante();
        var entrada = linhaDeEntrada.split(" ");
        participante.nome = entrada[0];
        participante.amigo = entrada[1].equals("SIM");
        participante.ordemInscricao = participantes.size();
            
        var participanteExistente = participantes
              .stream()
              .filter(p -> p.nome.equals(participante.nome))
              .findFirst()
              .orElse(null);
                    
        if(participanteExistente == null) participantes.add(participante);
            
        linhaDeEntrada = br.readLine();
      }
    
       var participantesOrdenados = participantes.stream().sorted(Comparator
                .comparing(Participante::isAmigo).reversed()
                .thenComparing(Participante::getNome)).collect(Collectors.toList());
    
      participantesOrdenados.forEach(p -> { System.out.println(p.nome); });
    
      List<Participante> amigos = participantesOrdenados
              .stream()
              .filter(Participante::isAmigo)
              .collect(Collectors.toList());
              
      Participante escolhido = null;
    
      for (Participante p : amigos) {
        if(escolhido == null) {
          escolhido = p;
        } else if (escolhido.nome.length() < p.nome.length()) {
          escolhido = p;
        } else if (escolhido.nome.length() == p.nome.length()
                  && escolhido.ordemInscricao > p.ordemInscricao) {
          escolhido = p;
        }
      }
    
      System.out.println("" + "\n" + "Amigo do Pablo:" + "\n" + escolhido.nome);
    }
    
    public static class Participante {
      private String nome;
      private boolean amigo;
      private int ordemInscricao;
      private boolean isAmigo;
        
      public String getNome() { return nome;  }
        
      public boolean isAmigo() {  return amigo; }
    }
}