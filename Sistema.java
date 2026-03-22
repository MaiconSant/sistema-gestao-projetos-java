import java.util.*;
import java.time.*;
import java.io.*;

public class Sistema {

    private List<Evento> eventos = new ArrayList<>();
    private List<Evento> confirmados = new ArrayList<>();

    public Sistema() {
        carregarEventos();
    }

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public void listarEventos() {
        eventos.sort(Comparator.comparing(Evento::getHorario));

        LocalDateTime agora = LocalDateTime.now();

        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);

            if (e.getHorario().isBefore(agora)) {
                System.out.println(i + " - " + e + " (JÁ OCORREU)");
            } else if (e.getHorario().isAfter(agora)) {
                System.out.println(i + " - " + e + " (FUTURO)");
            } else {
                System.out.println(i + " - " + e + " (AGORA)");
            }
        }
    }

    public void participarEvento(int index) {
        confirmados.add(eventos.get(index));
        System.out.println("Participação confirmada!");
    }

    public void cancelarParticipacao(int index) {
        confirmados.remove(index);
        System.out.println("Participação cancelada!");
    }

    public void meusEventos() {
        for (Evento e : confirmados) {
            System.out.println(e);
        }
    }

    private void salvarEventos() {
        try {
            PrintWriter writer = new PrintWriter("events.data");

            for (Evento e : eventos) {
                writer.println(e.getNome() + ";" +
                        e.getHorario() + ";" +
                        e.toString());
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar.");
        }
    }

    private void carregarEventos() {
        try {
            File file = new File("events.data");

            if (!file.exists()) return;

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] partes = linha.split(";");

                Evento evento = new Evento(
                        partes[0],
                        "Endereço",
                        "Categoria",
                        LocalDateTime.parse(partes[1]),
                        "Descrição"
                );

                eventos.add(evento);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar.");
        }
    }
}