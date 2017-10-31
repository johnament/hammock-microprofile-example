package ws.ament.hammock.mp;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@RequestScoped
public class CDsService {
    @Inject
    @ConfigProperty(name = "cds.max.reslts", defaultValue = "2")
    private int maxResults;

    private List<CD> cds;
    @PostConstruct
    public void initCDs() {
        cds = asList(new CD("Thriller", "Michael Jackson"),
                new CD("Synchronicity", "The Police"),
                new CD("Rattle and Hum", "U2"),
                new CD("The Battle of Los Angeles", "Rage Against the Machine"));
    }

    public List<CD> getCds() {
        if(cds.size() > maxResults) {
            return Collections.unmodifiableList(cds.subList(0, maxResults));
        }
        return Collections.unmodifiableList(cds);
    }
}
