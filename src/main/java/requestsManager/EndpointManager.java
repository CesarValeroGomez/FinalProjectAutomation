package requestsManager;

import helpers.ReadPropertiesRequest;

public class EndpointManager extends ReadPropertiesRequest {

    public EndpointManager() {
        super();
    }

    public String getOnlyDomain() {
        return getDomain();
    }

    public String getListsEndpoint() {
        return "board/"+getIdBoard()+"/lists";
    }

    public String getInfoCard(String idCard) {
        return "cards/"+idCard+"?token="+getToken()+"&key="+getApiKey();
    }

    public String getCardsListEndpoint(String list) {
        return "lists/"+getListCode(list)+"/cards";
    }

    public String getCreateCardLink(String list) {
        return "cards?token="+getToken()+"&key="+getApiKey()+"&idList="+getListCode(list);
    }

    public String getAddCommentLink(String idCard) {
        return "cards/"+idCard+"/actions/comments?token="+getToken()+"&key="+getApiKey();
    }

    public String getUpdateCardLink(String idCard) {
        return "cards/"+idCard+"?token="+getToken()+"&key="+getApiKey();
    }

    public String getMoveCardToList(String idCard, String toList) {
        return "cards/"+idCard+"?token="+getToken()+"&key="+getApiKey()+"&idList="+getListCode(toList);
    }

    public String getListCode(String list) {
        String listCode = null;
        switch (list) {
            case "Backlog": listCode = getIdListBacklog();
                break;
            case "Design": listCode = getIdListDesign();
                break;
            case "To Do": listCode = getIdListToDo();
                break;
            case "Doing": listCode = getIdListDoing();
                break;
            case "Code Review": listCode = getIdListCodeReview();
                break;
            case "Testing": listCode = getIdListTesting();
                break;
            case "Done": listCode = getIdListDone();
                break;
            default:
                break;
        }
        return listCode;
    }
}
