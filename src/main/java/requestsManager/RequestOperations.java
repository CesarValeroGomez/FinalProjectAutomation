package requestsManager;

import builders.CardBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entities.Card;
import entities.ResponseLists;
import helpers.ReadPropertiesFile;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestOperations {

    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();
    private final ReadPropertiesFile readPropertiesFile;
    private final EndpointManager endpointManager;


    public RequestOperations() {
        readPropertiesFile = new ReadPropertiesFile();
        endpointManager = new EndpointManager();
    }

    public List<ResponseLists> getAllLists(Response response) {
        JsonElement jsonResponse = parser.parse(response.body().asString());
        return Arrays.asList(gson.fromJson(jsonResponse, ResponseLists[].class));
    }

    public Map<String,Object> getMap(Response response) {
        JsonElement element = parser.parse(response.body().asString());
        return gson.fromJson(element, Map.class);
    }

    public void authenticate() {
        RestAssured.baseURI = endpointManager.getOnlyDomain();
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(readPropertiesFile.getUser());
        authScheme.setPassword(readPropertiesFile.getPassword());
        RestAssured.authentication = authScheme;
    }

    public List<ResponseLists> getLists() {
        Response response =
                given().contentType( ContentType.JSON).
                        log().uri().
                        when().get(endpointManager.getListsEndpoint()).
                        andReturn();

        Assert.assertEquals(200, response.getStatusCode());
        return getAllLists(response);
    }

    public Map<String,Object> validateCard(String idCard) {
        Response response =
                given().contentType( ContentType.JSON).
                        log().uri().
                        when().get(endpointManager.getInfoCard(idCard)).
                        andReturn();
        response.then().assertThat().statusCode(200);
        return getMap(response);
    }

    public Map<String,Object> createCard(Card cardBody, String list) {
        Response response =
                given().contentType( ContentType.JSON).
                        body(cardBody).
                        log().body().
                        when().post(endpointManager.getCreateCardLink(list)).
                        andReturn();
        response.then().assertThat().statusCode(200);
        return getMap(response);
    }

    public Response deleteCard(String idCard) {
        return
                given().contentType( ContentType.JSON).
                        log().uri().
                        when().delete(endpointManager.getInfoCard(idCard)).
                        andReturn();
    }

    public Map<String,Object> addComment(String idCard, String comment) {
        String bodyComment = "{\"text\": \""+comment+"\"}";
        Response response =
                given().contentType( ContentType.JSON).
                        body(bodyComment).
                        log().body().
                        when().post(endpointManager.getAddCommentLink(idCard)).
                        andReturn();
        response.then().assertThat().statusCode(200);
        return getMap(response);
    }

    public Map<String,Object>  moveCardToList(String idCard, String toList) {
        Response response =
                given().contentType( ContentType.JSON).
                        log().uri().
                        when().put(endpointManager.getMoveCardToList(idCard, toList)).
                        andReturn();
        response.then().assertThat().statusCode(200);
        return getMap(response);
    }
    public Map<String,Object> updateCard(Card cardBody, String idCard) {
        Response response =
                given().contentType( ContentType.JSON).
                        body(cardBody).
                        log().body().
                        when().put(endpointManager.getUpdateCardLink(idCard)).
                        andReturn();
        response.then().assertThat().statusCode(200);
        return getMap(response);
    }
}
