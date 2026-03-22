import java.util.*;
import java.time.*;
import java.io.*;

public class Sistema {

    private List<Evento> eventos = new ArrayList<>();
    private List<Evento> confirmados = new ArrayList<>();

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public void listarEventos() {
        eventos.sort(Comparator.comparing(Evento::getHorario));

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println(i + " - " + eventos.get(i));
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
                writer.println(e.getNome() + ";" + e.getHorario());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar.");
        }
    }
}