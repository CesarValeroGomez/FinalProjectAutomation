package apiTests;

import builders.CardBuilder;
import entities.Card;
import entities.ResponseLists;
import helpers.ReadPropertiesRequest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestsManager.EndpointManager;
import requestsManager.RequestOperations;


import java.util.List;
import java.util.Map;


public class RestFullCard extends BaseTest {

    private Logger logger = LogManager.getLogger( RestFullCard.class);

    @Test(priority = 1, description = "Validate the Lists in a Project Kanban are bigger than 5")
    public void validateLists() {
        List<ResponseLists> listsResponse = requestOperations.getLists();
        Assert.assertTrue(listsResponse.size() > 5, "there are more lists than 5");
        Assert.assertEquals(listsResponse.get(1).getName(), "Design",
                "the second list in the Project is Design");
        Assert.assertEquals(listsResponse.get(2).getIdBoard(), readProperties.getIdBoard(),
                "the third list belongs to the current Project");
        logger.trace("API Test 1 - Passed - ValidateLists ");
        logger.info("The Number of lists in the Kanban project is: "+listsResponse.size());
        logger.info("The lists in Kanban are: ");
        for (ResponseLists responseLists : listsResponse) {
            logger.info( responseLists.getName() );
        }
    }

    @Test(priority = 2, description = "Validate the Information of the Card using its ID")
    public void validateCard() {
        String idCard = "5f774bf2729f58605d3c2352";
        Map<String,Object> cardMap = requestOperations.validateCard(idCard);
        Assert.assertEquals(cardMap.get("id"), idCard, "is the same CardID");
        Assert.assertEquals(cardMap.get("idBoard"), readProperties.getIdBoard(),
                "The Card belong to the current Board");
        logger.info("API Test 2 - Passed - ValidateCard ");
        logger.info("The Name of the Card validated is: "+cardMap.get("name"));
    }

    @Test(priority = 3, description = "Create a new card in the List selected;  Could be: Backlog, Design, To Do, " +
                        "Doing, Code Review, Testing or Done.")
    public void createNewCard() {
        Card cardBody = new CardBuilder().withName("Prueba RestAsured").
                withDescription("Card created from Rest Assured with IntelliJ").
                withDueDate("2020-10-12").
                withEmail("cesar.valero@gyp.com").build();
        Map<String,Object> newCardMap = requestOperations.createCard(cardBody,"Testing");
        Assert.assertNotNull(newCardMap.get("id"), "The idCard is not null");
        Assert.assertEquals(newCardMap.get("name"), "Prueba RestAsured",
                "It has the same name that was sent");
        Assert.assertEquals(newCardMap.get("idBoard"), readProperties.getIdBoard(),
                "The Card belong to the current Board");
        logger.info("API Test 3 - Passed - CreateNewCard ");
        logger.info("The Id of the New Card is: "+newCardMap.get("id"));
    }

    @Test(priority = 4, description = "Delete a Card")
    public void validateDeleteCard() {
        Card cardBody = new CardBuilder().withName("Card to be Deleted").
                withDescription("This Card created is going to be deleted").
                withDueDate("2020-10-12").
                withEmail("cesar.valero@gyp.com").build();
        Map<String,Object> newCardMap = requestOperations.createCard(cardBody,"Code Review");

        String idCard = String.valueOf(newCardMap.get("id"));
        Response deleteCardResponse = requestOperations.deleteCard(idCard);
        Assert.assertEquals(deleteCardResponse.getStatusCode(), 200, "Card deleted");
        Response deleteCardResponse2 = requestOperations.deleteCard(idCard);
        Assert.assertEquals(deleteCardResponse2.getStatusCode(), 404,
                "Card not found because it was deleted previously");
        Assert.assertEquals(deleteCardResponse2.asString(), "The requested resource was not found.",
                "Card not found because it was deleted previously");
        logger.info("API Test 4 - Passed - ValidateDeleteCard ");
        logger.info("The Card was Deleted ");
    }

    @Test(priority = 5, description = "Adding a new Comment in a Card")
    public void validateAddNewComment() {
        Map<String,Object> addCommentResponse = requestOperations.addComment("5f7d1f1340802349ec44a5df",
                                 "Type a Comment from IntelliJ using Rest Assured");
        Assert.assertEquals(addCommentResponse.get("type"), "commentCard");
        Assert.assertNotNull(addCommentResponse.get("data"), "The Comment is not Null");
        logger.info("API Test 5 - Passed - ValidateAddNewComment ");
        logger.info("New Comment added to the Card "+addCommentResponse.get("id"));
    }

    @Test(priority = 6, description = "Move the Card task from one List to Other")
    public void validateMoveCardToList() {
        String idCard = "5f7d1f1340802349ec44a5df";
        Map<String,Object> moveCardResponse = requestOperations.moveCardToList(idCard, "To Do");
        Assert.assertEquals(moveCardResponse.get("id"), idCard, "is the same CardID");
        Assert.assertEquals(moveCardResponse.get("idList"), readProperties.getIdListToDo());
        logger.info("API Test 6 - Passed - validateMoveCardToList ");
        logger.info("The Card was move to the List To Do ");
    }

    @Test(priority = 7, description = "Update the Card")
    public void validateUpdateCard() {
        String idCard = "5f7d1f1340802349ec44a5df";
        Card cardBody = new CardBuilder().
                withDescription("The Description has been modified from IntelliJ").
                withDueDate("2020-11-08").
                build();
        Map<String,Object> updateCardResponse = requestOperations.updateCard(cardBody, idCard);
        Assert.assertEquals(updateCardResponse.get("id"), idCard, "is the same CardID");
        Assert.assertEquals(updateCardResponse.get("desc"), "The Description has been modified from IntelliJ");
        logger.info("API Test 7 - Passed - validateUpdateCard ");
        logger.info("Description updated:  "+updateCardResponse.get("desc"));
        logger.info("New Due Date updated:  "+updateCardResponse.get("due"));
    }

    @Test(priority = 8, description = "Close a Card in any time")
    public void ValidateToCloseCard() {
        Card cardBodyNew = new CardBuilder().withName("Card to be Close").
                withDescription("This Card created is going to be Closed").
                withDueDate("2020-10-12").
                withEmail("cesar.valero@gyp.com").build();
        Map<String,Object> newCardMap = requestOperations.createCard(cardBodyNew,"Code Review");
        Assert.assertEquals(newCardMap.get("closed"), false, "The Card is Not closed");

        String idCard = String.valueOf(newCardMap.get("id"));
        Card cardBodyClose = new CardBuilder().
                withIsClosed(true).build();
        Map<String,Object> updateCardResponse = requestOperations.updateCard(cardBodyClose, idCard);
        Assert.assertEquals(updateCardResponse.get("id"), idCard, "is the same CardID");
        Assert.assertEquals(updateCardResponse.get("closed"), true, "The Card is closed");
        logger.info("API Test 8 - Passed - ValidateToCloseCard ");
        logger.info("Is the Card closed?:  "+updateCardResponse.get("closed"));
    }
}
