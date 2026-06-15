package Progetto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import it.uniroma3.siw.backend.repository.TorneoRepository;
import it.uniroma3.siw.backend.TorneiCalcisticiApplication;
import it.uniroma3.siw.backend.model.Torneo;


@SpringBootTest(classes = TorneiCalcisticiApplication.class)
class TorneiCalcisticiApplicationTests {

@Autowired
private TorneoRepository torneoRepository;
@Autowired
private TransactionTemplate transactionTemplate;

    private void access(Torneo t) {
        t.getPartite().forEach(p -> {
            p.getSquadraHome().getNome();
            p.getSquadraAway().getNome();
            p.getArbitro().getNome();
        });
    }
    
private long lazy() {
    return transactionTemplate.execute(status -> {
        long start = System.currentTimeMillis();

        Torneo t = torneoRepository.findById(1).orElseThrow();
        t.getPartite().size();

        return System.currentTimeMillis() - start;
    });
}

    private long join() {
        long start = System.currentTimeMillis();

        Torneo t = torneoRepository.findJoinFetch(1);
        access(t);

        return System.currentTimeMillis() - start;
    }

    private long graph() {
        long start = System.currentTimeMillis();

        Torneo t = torneoRepository.findEntityGraph(1);
        access(t);

        return System.currentTimeMillis() - start;
    }

    @Test
    void mainTest() {

        int runs = 50;

        // warm-up JVM
        for (int i = 0; i < 5; i++) {
            lazy();
            join();
            graph();
        }

        long lazySum = 0;
        long joinSum = 0;
        long graphSum = 0;

        for (int i = 0; i < runs; i++) lazySum += lazy();
        for (int i = 0; i < runs; i++) joinSum += join();
        for (int i = 0; i < runs; i++) graphSum += graph();

        System.out.println("-----------------------------------------------------------------------------------------------\n");
        System.out.println("E' previsto LAZY sia il piu veloce apparentemente, ma non carica tutti i dati insieme, rischiando N+1 query\n");
        System.out.println("E' previsto JOIN FETCH sia piu lento, ma carica tutti i dati in una sola query\n");
        System.out.println("E' previsto ENTITY GRAPH sia piu veloce di JOIN FETCH, ma varia a seconda delle informazioni richieste\n");

        System.out.println("Risultato dei test: \n");

        System.out.println("LAZY TOTAL: " + lazySum + " ms");
        System.out.println("LAZY MEAN: " + lazySum / runs + " ms\n");

        System.out.println("JOIN FETCH TOTAL: " + joinSum + " ms");
        System.out.println("JOIN FETCH MEAN: " + joinSum / runs + " ms\n");

        System.out.println("ENTITY GRAPH TOTAL: " + graphSum + " ms");
        System.out.println("ENTITY GRAPH MEAN: " + graphSum / runs + " ms");

        System.out.println("-----------------------------------------------------------------------------------------------\n");
    }
}