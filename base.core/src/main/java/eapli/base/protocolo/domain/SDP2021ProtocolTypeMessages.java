package eapli.base.protocolo.domain;

public class SDP2021ProtocolTypeMessages {
    public static final int CODIGO_TESTE = 0;
    public static final int CODIGO_FIM= 1;
    public static final int CODIGO_ENTENDIDO = 2;
    public static final int CODIGO_INFO_DASHBOARD=3;
    public static final int CODIGO_RES_INFO_DASHBOARD=4;
    public static final int CODIGO_AVANCAR_FLUXO_PEDIDO =5;
    public static final int CODIGO_RES_AVANCAR_FLUXO_PEDIDO =6;
    public static final int CODIGO_SCRIPT = 7;
    public static final int CODIGO_RES_SCRIPT = 8;
    public static final int CODIGO_ERRO=254;

    public static final String ERROR_MESSAGE = "ERROR_IN_REQUEST";
    public static final String TEST_MESSAGE = "TEST_MESSAGE";
    public static final String EMPTY_MESSAGE = "";

    public static final String SUCCESSFUL_MESSAGE_TYPE_SCRIPT = "SCRIPT_EXECUTION_SUCCESS";
    public static final String ERROR_MESSAGE_TYPE_SCRIPT = "SCRIPT_EXECUTION_FAILED";
    public static final String SUCCESSFUL_MESSAGE_SCRIPT = "Automated script executed successfully";
    public static final String ERROR_MESSAGE_SCRIPT = "An Error occurred during Script Execution";

}
