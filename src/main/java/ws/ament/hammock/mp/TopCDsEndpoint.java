package ws.ament.hammock.mp;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@RequestScoped
@Counted
public class TopCDsEndpoint {
    @Inject
    private CDsService cDsService;
    @GET
    @Timed
    public Response findCDs() {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        cDsService.getCds().stream().map(TopCDsEndpoint::toJson).forEach(arrayBuilder::add);
        return Response.ok(arrayBuilder.build().toString(), MediaType.APPLICATION_JSON_TYPE).build();
    }

    private static JsonObject toJson(CD cd) {
        return Json.createObjectBuilder().add("author", cd.getAuthor()).add("title", cd.getTitle()).build();
    }
}
