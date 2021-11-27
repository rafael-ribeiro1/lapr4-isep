package daemon.executoratividade.protocol;

public class ExecutarAtividadeRequestFactory {
    public static BaseAtividadeRequest create(int version, String script){
        switch(version){
            case 1:
                return new  ExecutarAtividadeRequestV1( script );
            case 0:
            default:
                return new ExecutarAtividadeRequestV0( script );
        }
    }
}
