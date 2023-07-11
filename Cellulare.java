/*
 * Questo esercizio è stato eseguito all'interno del corso di Generation Italy 
 * con l'affiancamento di Accademia del Levante. Ogni diritto è riservato.
 * 
 * Esercizio complesso
 * 
 * 23_06_12
 * 
 * Traccia base:
 * Simulare un telefono Cellulare con contratto a ricarica,
 * dotato di tre variabili d'istanza: credito disponibile (iniziale 20,0€),
 * chiamate effettuate (iniziale 0), tariffa.
 * Predisporre gli opportuni metodi per effettuare:
 *      - Ricariche del credito di un importo a piacere, con "sms" di conferma;
 *      - Impostazione di una tariffa di 20 centesimi/minuto;
 *      - Chiamate di durate in minuti a piacere, verificando se il credito è sufficiente e 
 *        bloccare la chiamata in corso all'esaurirsi del credito (un "sms" avvisa della situazione);
 *      - Visualizzazione del credito disponibile;
 *      - Visualizzazione del numero di chiamate effettuate;
 *      - Azzeramento del numero di chiamate effettuate, con messaggio di conferma.
 * Utilizzare dei println() per simulare messaggi su display e sms ricevuti.
 * 
 * Traccia avanzata:
 * Dopo aver svolto la traccia base e ottenuto conferma di completezza dal docente,
 * si potranno aggiungere al cellulare le seguenti funzionalità,
 * modificando i metodi già implementati laddove occorre:
 *      - Gestione di una rubrica, con aggiunta di nome e numero 
 *        nonché possibilità di cancellare contatti; 
 *        la rubrica può essere richiamata dalla funzione che effettua chiamate;
 *      - Potenziamento del visualizzatore di chiamate, che mostra anche i nomi
 *        dei contatti oltre che la quantità delle telefonate effettuate;
 *      - Creazione di una slot machine, da avviare scrivendo "Gioca" 
 *        dopo aver selezionato l'app, che sorteggia tre numeri da 1 a 5,
 *        conteggiando il numero di volte che si realizzano tre numeri uguali, 
 *        e predisporre un input per uscire dal gioco.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Cellulare {

    /* ---------- 
      VARIABILI 
    ---------- */

    int tastiera;
    double creditoDisponibile;
    int chiamateEffettuate = 0;
    double tariffa;
    double costoChiamata;
    Scanner input = new Scanner(System.in);

    ArrayList<String> rubrica = new ArrayList<String>();
    ArrayList<String> chiamate = new ArrayList<String>();
    ArrayList<String> durate = new ArrayList<String>();

    int nomi;
    int vittorie = 0;



    /* ---------- 
        METODI 
    ---------- */

    /*
     * ---- metodo per effettuare una ricarica con importo
     * deciso dall'utente tramite input su terminale ----
     */

    public void ricarica() {

        /* messaggio pre input */
        System.out.println("Qual è l'importo che vuoi ricaricare?");

        /* richesta di input da terminale */
        double ricarica = input.nextDouble();

        /* restituzione del nuovo credito dopo aver effettuato la ricarica */
        creditoDisponibile = ricarica + creditoDisponibile;

        /* stampa SMS di evvenuta ricarica */
        System.out.println("SMS da ENOFADOVE MOBILE: hai ricaricato " + ricarica + " euro. Il tuo saldo disponibile ora è di " + creditoDisponibile + " euro.");

        System.out.println(" ------------ ");

    }

    /* ---------- metodo per effettuare chiamate ---------- */
    public void chiamata() {

        if (tariffa <= 0) {

            System.out.println("Impossibile procedere. Verificare la vostra tariffa");

        } else if (tariffa > 0) {

            /* richiesta input da terminale per la rubrica */
            System.out.println("Selezionare il contatto tramite posizione in rubrica, oppure scrivere il numero");

            rubricaAccedi();

            int posizione = input.nextInt(); 
            String destinatario = rubrica.get(posizione - 1);

            if (posizione > rubrica.size() && posizione < 7) {
                System.out.println("Non esistono contatti in quella posizione");
                
            } else if (posizione < rubrica.size()) {

                /* richiesta input da terminale */
                System.out.println("Inserire la durata della chiamata");
                double minuti = input.nextDouble();

                /* setto costo chiamata */
                costoChiamata = tariffa*minuti;

                /* --- creo i casi ---  */

                /* costo della chiamata < del credito disponibile */
                if (costoChiamata < creditoDisponibile)     {
                    creditoDisponibile = creditoDisponibile - costoChiamata;
                    System.out.println("Hai chiamato " + destinatario);
                    System.out.println("La chiamata è stata effettuata. Durata: " + minuti + ". Il costo totale della chiamata è di: " + costoChiamata + ".");
                    System.out.println("Il credito residuo è: " + creditoDisponibile);

                    chiamate.add("Destinatario: " + destinatario);
                    durate.add("Durata: " + minuti);

                /* costo della chiamata > del credito disponibile */
                } else if (costoChiamata >= creditoDisponibile) {
                    minuti = creditoDisponibile/tariffa;
                    System.out.println("Hai chiamato " + destinatario);
                    System.out.println("Credito insufficiente. Durata: " + minuti + ". Il costo totale della chiamata è di: " + (minuti*tariffa) + ".");
                    creditoDisponibile = 0;
                    System.out.println("Il credito residuo è pari a: " + creditoDisponibile + ".");
                    chiamate.add("Destinatario: " + destinatario);
                    durate.add("Durata: " + minuti);
                }

            } else if (posizione > 7){
                /* richiesta input da terminale */
                System.out.println("Inserire la durata della chiamata");
                double minuti = input.nextDouble();
                /* setto costo chiamata */
                costoChiamata = tariffa*minuti;

                /* --- creo i casi ---  */

                /* costo della chiamata < del credito disponibile */
                if (costoChiamata < creditoDisponibile) {
                    creditoDisponibile = creditoDisponibile - costoChiamata;
                    System.out.println("Hai chiamato un numero non in rubrica: " + posizione);
                    System.out.println("La chiamata è stata effettuata. Durata: " + minuti + ". Il costo totale della chiamata è di: " + costoChiamata + ".");
                    System.out.println("Il credito residuo è: " + creditoDisponibile);
                    chiamate.add("Destinatario: " + posizione);
                    durate.add("Durata: " + minuti);

                /* costo della chiamata > del credito disponibile */
                } else if (costoChiamata >= creditoDisponibile) {
                    minuti = creditoDisponibile/tariffa;
                    System.out.println("Hai chiamato un numero non in rubrica: " + posizione);
                    System.out.println("Credito insufficiente. Durata: " + minuti + ". Il costo totale della chiamata è di: " + (minuti*tariffa) + ".");
                    creditoDisponibile = 0;
                    System.out.println("Il credito residuo è pari a: " + creditoDisponibile + ".");
                    chiamate.add("Destinatario: " + posizione);
                    durate.add("Durata: " + minuti);
                }
            }    

            /* contatore delle chiamate */
            chiamateEffettuate++;
        }

        System.out.println(" ------------ ");

    }

    /* ---------- metodo per visualizzare le chiamate effettuate ---------- */
    public void restituzioneChiamate() {

        System.out.println("Chiamate effettuate: " + chiamateEffettuate + ".");
        
            for (int j = 0; j < chiamate.size(); j++) {
        
                System.out.print((j + 1) + ". ");
                System.out.println(rubrica.get(j));
            }

            for (int k = 0; k < durate.size(); k++) {
        
                System.out.println(durate.get(k));
            }

        System.out.println(" ------------ ");

    }

    /* metodo per azzerare le chiamate effettuate */
    public void azzeramentoChiamate() {

        System.out.println("Chiamate effettuate: " + chiamateEffettuate + ". Vuoi resettare le chiamate? Rispondi si se vuoi resettare le chiamate, altrimenti rispondi no.");

        String response = (String) (input.nextLine());

        /* richiedo l'input fino a che non è uno dei due ammessi */
        while (!response.equalsIgnoreCase("si") && !response.equalsIgnoreCase("no")) {

            System.out.println("Input non valido. Ritentare.");
            response = (String) (input.nextLine());
            response = response.toLowerCase(); 
        }

        if (response.equalsIgnoreCase("si")) {
            chiamateEffettuate = 0;
            System.out.println("Le chiamate sono state resettate. Chiamate effettuate: " + chiamateEffettuate + ".");
        } else if (response.equalsIgnoreCase("no")) {
            System.out.println("le tue chiamate non sono state resettate. LE tue chiamate sono ancora: " + chiamateEffettuate + ".");
        }

        System.out.println(" ------------ ");

    }
    

    /* ---------- metodo per visualizzare il credito ---------- */
    public void restituzioneCredito() {

        System.out.println("Il tuo credito residuo è: " + creditoDisponibile);

        System.out.println(" ------------ ");

    }

    /* ---------- closing ------------ */
    public void chiusura() {

        /* chiudo gli input degli scanner */
        input.close();
    }

        /* ---------- 
            METODI DELLA RUBRICA
        ---------- */

            /* ---------- metodo per registrare i numeri ---------- */
            public void rubricaAggiungi() {
            
                 /* richiesta input da terminale */
                System.out.println("Inserire nome");
                String nome = input.nextLine();
                System.out.println("Inserire numero");
                String numero = input.nextLine();
            
                nomi++;
            
                rubrica.add(nome + " tel: " + numero);

                rubricaAccedi();

                System.out.println(" ------------ ");

            }
        
            /* ---------- metodo per cancellare i contatti ---------- */
            public void rubricaCancella() {
            
                System.out.println("Rimuovere il nome inserendo la posizione in rubrica");
                int posizione = input.nextInt();
            
                rubrica.remove((posizione - 1));
                nomi--;
            
                rubricaAccedi();

                System.out.println(" ------------ ");

            }
    
            /* ---------- metodo per accedere alla rubrica ---------- */
            public void rubricaAccedi() {
            
                for (int i = 0; i < rubrica.size(); i++) {
                
                    /* uso il metodo get() per prelevare gli elementi tramite il contatore/indice i */
                    System.out.println((i + 1) + ". " + rubrica.get(i));
                }

            }

            /* ---------- 
                SWITCH DELLA RUBRICA
            ---------- */

            public void rubrica() {

                boolean condizioneRubrica = true;

                while (condizioneRubrica) {

                    System.out.println("Rubrica. 1. Visualizza contatti 2. Aggiungi contatti 3. Rimuovi contatti 0. Menu principale");
                    
                    tastiera = input.nextInt();

                        switch(tastiera) {
                            case 1:
                                rubricaAccedi();
                                break;
                            case 2:
                                rubricaAggiungi();
                                break;
                            case 3:
                                rubricaCancella();
                                break;
                            case 0:
                                condizioneRubrica = false;
                                break;
                            default:
                                System.out.println("Input non corretto.");
                                break;
                        }
                }
            }
            
    /* ---------- gioco ------------ */
    public void gioca() {
        
        System.out.println("Ebnevnuto in Slot Machine!");
        System.out.println("Attenzione: il gioco d'azzardo può creare dipendenza patologica. Se il gioco diventa un problema, chiedi aiuto al numero verde 800 558 822");

    }

    /* ---------- 
        SWITCH 
    ---------- */

    public void accensione() {

        System.out.println("Benvenuti. Per utilizzare il dispositivo si prega di seguire le istruzioni seguenti.");

        boolean condizione = true;

            while (condizione) {

            System.out.println("1. Ricarica 2. Traffico residuo 3. Chiama 4. Numero di chiamate 5. Azzera chiamate 6. Rubrica 7. Gioca 0. Arresta");
            tastiera = input.nextInt();

                    switch(tastiera) {
                    case 1:
                        ricarica();
                        break;
                    case 2: 
                        restituzioneCredito();
                        break;
                    case 3:
                       chiamata();
                       break;
                    case 4:
                        restituzioneChiamate();
                        break;
                    case 5:
                        azzeramentoChiamate();
                        break;
                    case 6:
                        rubrica();
                        break;
                    case 7:
                        gioca();
                        break;
                    case 0:
                        chiusura();
                        condizione = false;
                        break;
                    default:
                        System.out.println("Input non corretto.");
                        break;
                }
            } 
    }
       
}
