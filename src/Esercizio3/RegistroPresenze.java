package Esercizio3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RegistroPresenze {

    private List<Presenza> elencoPresenze;

    public RegistroPresenze() {
        this.elencoPresenze = new ArrayList<>();
    }

    public void aggiungiPresenza(Presenza presenza) {
        this.elencoPresenze.add(presenza);
    }

    public void salvaPresenzeSuFile(String nomeFile) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Presenza presenza : this.elencoPresenze) {
            stringBuilder.append(presenza.getNomeDipendente()).append("@").append(presenza.getGiorniPresenza()).append("#");
        }
        FileUtils.writeStringToFile(new File(nomeFile), stringBuilder.toString(), "UTF-8");
    }

    public void caricaPresenzeDaFile(String nomeFile) throws IOException {
        String presenzeString = FileUtils.readFileToString(new File(nomeFile), "UTF-8");
        String[] presenzeArray = presenzeString.split("#");
        for (String presenzaString : presenzeArray) {
            String[] presenzaSplit = presenzaString.split("@");
            String nomeDipendente = presenzaSplit[0];
            int giorniPresenza = Integer.parseInt(presenzaSplit[1]);
            Presenza presenza = new Presenza(nomeDipendente, giorniPresenza);
            this.elencoPresenze.add(presenza);
        }
    }

    public static void main(String[] args) {
        RegistroPresenze registroPresenze = new RegistroPresenze();
        registroPresenze.aggiungiPresenza(new Presenza("Mario Rossi", 5));
        registroPresenze.aggiungiPresenza(new Presenza("Giorgio Bianchi", 7));
        registroPresenze.aggiungiPresenza(new Presenza("Gianni Verdi", 7));
        try {
            registroPresenze.salvaPresenzeSuFile("presenze.txt");
            RegistroPresenze registroPresenze2 = new RegistroPresenze();
            registroPresenze2.caricaPresenzeDaFile("presenze.txt");
            System.out.println(registroPresenze2.elencoPresenze);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Presenza {

    private String nomeDipendente;
    private int giorniPresenza;

    public Presenza(String nomeDipendente, int giorniPresenza) {
        this.nomeDipendente = nomeDipendente;
        this.giorniPresenza = giorniPresenza;
    }

    public String getNomeDipendente() {
        return nomeDipendente;
    }

    public int getGiorniPresenza() {
        return giorniPresenza;
    }

    @Override
    public String toString() {
        return "Presenza [nomeDipendente=" + nomeDipendente + ", giorniPresenza=" + giorniPresenza + "]";
    }

}
