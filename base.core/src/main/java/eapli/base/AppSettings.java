package eapli.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);

    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private static final String EMAIL_FROM = "email.from";
    private static final String EMAIL_PASSWORD = "email.password";
    private static final String EMAIL_HOST = "email.host";
    private static final String EMAIL_PORT = "email.port";
    private static final String MECH_NUM_MAX_LENGTH = "mechnum.maxlength";
    private static final String CRIT_MAX_VALUE = "crit.maxvalue";

    //servers
    private static final String SDP_PROTOCOL_VERSION="sdp.protocol.version";
    //engine
    private static final String ENGINE_SERVER_IP = "ip.server.engine";
    private static final String ENGINE_SERVER_PORT = "port.server.engine";
    private static final String ENGINE_EXECUTOR_THREADS = "engine.executor.threads";
    private static final String TASK_ALGORITHM = "task.distributor.algorithm";
    private static final String PORT_CONNECTION_MODE ="engine.executor.connection.port.mode";
    //executor
    private static final String EXECUTOR_SERVER_IP = "ip.server.executor";
    private static final String EXECUTOR_SERVER_PORT = "port.server.executor";
    private static final String DASHBOARD_PORT = "http.dashboard.port";
    //certificate
    private static final String CERT_CLIENT_NAME = "cert.client.name";

    private static final String AUTOMATICALLY_ATRIBUTE_COLLABORATORS = "automatically.atribute.collaborators";

    private static final String COLABORATOR_ALGORITHM = "colaborator.algorithm";


    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();
            LOGGER.warn("Loading default properties", exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.base.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli"
                + ".base");

        this.applicationProperties.setProperty(EMAIL_FROM, "lapr4_2di_g02@hotmail.com");
        this.applicationProperties.setProperty(EMAIL_PASSWORD, "lapr2dig02");
        this.applicationProperties.setProperty(EMAIL_HOST, "smtp-mail.outlook.com");
        this.applicationProperties.setProperty(EMAIL_PORT, "587");
        this.applicationProperties.setProperty(MECH_NUM_MAX_LENGTH, "6");
        this.applicationProperties.setProperty(CRIT_MAX_VALUE, "20");
        //servers
        this.applicationProperties.setProperty( ENGINE_SERVER_IP, "10.8.0.82");
        this.applicationProperties.setProperty( ENGINE_SERVER_PORT, "31942");
        this.applicationProperties.setProperty( EXECUTOR_SERVER_IP, "10.8.0.81");
        this.applicationProperties.setProperty( EXECUTOR_SERVER_PORT, "31942");
        this.applicationProperties.setProperty(SDP_PROTOCOL_VERSION, "1");
        this.applicationProperties.setProperty(DASHBOARD_PORT, "7531");
        this.applicationProperties.setProperty(CERT_CLIENT_NAME, "client_mf");
        this.applicationProperties.setProperty(AUTOMATICALLY_ATRIBUTE_COLLABORATORS,"false");
        this.applicationProperties.setProperty(TASK_ALGORITHM, "fcfs");
        this.applicationProperties.setProperty(COLABORATOR_ALGORITHM,"fcfs");
        this.applicationProperties.setProperty (PORT_CONNECTION_MODE,"true" );
    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public Properties getEmailProps() {
        Properties emailProps = new Properties();
        emailProps.setProperty(EMAIL_FROM, applicationProperties.getProperty(EMAIL_FROM));
        emailProps.setProperty(EMAIL_PASSWORD, applicationProperties.getProperty(EMAIL_PASSWORD));
        emailProps.setProperty(EMAIL_HOST, applicationProperties.getProperty(EMAIL_HOST));
        emailProps.setProperty(EMAIL_PORT, applicationProperties.getProperty(EMAIL_PORT));
        return emailProps;
    }

    public Integer getMechNumMaxLength() {
        try {
            return Integer.valueOf(applicationProperties.getProperty(MECH_NUM_MAX_LENGTH));
        } catch (NumberFormatException e) {
            return 6;
        }
    }

    public Integer getCritMaxValue() {
        try {
            return Integer.valueOf(applicationProperties.getProperty(CRIT_MAX_VALUE));
        } catch (NumberFormatException e) {
            return 20;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(String prop) {
        return this.applicationProperties.getProperty(prop);
    }

    public String getIpEngineServer() {
        return this.applicationProperties.getProperty( ENGINE_SERVER_IP );
    }

    public Integer getEngineServerPort() {
        try {
            return Integer.parseInt(this.applicationProperties.getProperty( ENGINE_SERVER_PORT ));
        }catch (NumberFormatException e) {
            System.out.println ("error getting engine port");
            return 31942;
        }
    }

    public byte getSdpProtocolVersion(){
        try{
            return Byte.parseByte ( this.applicationProperties.getProperty ( SDP_PROTOCOL_VERSION ));
        }catch (NumberFormatException e){
            LOGGER.warn ( "Could not read sdp version from properties file" );
            return 1;
        }
    }

    public Integer getDashboardPort() {
        try {
            return Integer.parseInt(this.applicationProperties.getProperty(DASHBOARD_PORT));
        } catch (NumberFormatException e) {
            return 7531;
        }
    }

    public  String getExecutorServerIp() {
        //return this.applicationProperties.getProperty( EXECUTOR_SERVER_IP );
        return getExecutorsServerIps()[0].trim ();
    }

    public String[] getExecutorsServerIps(){
        String[]ips= this.applicationProperties.getProperty(EXECUTOR_SERVER_IP).split ( "," );
        for (int i = 0; i < ips.length; i++) {
            ips[i]=ips[i].trim ();
        }
        return ips;
    }

    public Integer getExecutorServerPort() {
        try{
            return Integer.parseInt(this.applicationProperties.getProperty(EXECUTOR_SERVER_PORT));
        }catch (NumberFormatException e){
            return 31942;
        }
    }

    public int[] getExecutorServerPortList() {
        String[]portsStr=this.applicationProperties.getProperty(EXECUTOR_SERVER_PORT).split ( "," );
        int[] portsInt=new int[portsStr.length];
        for (int i = 0; i < portsStr.length; i++) {
            try{
                portsInt[i]=Integer.parseInt ( portsStr[i].trim () );
            }catch (NumberFormatException e){
                portsInt[i]=31942;
            }
        }
        return portsInt;
    }

    public int getEngineExecutorThreads(){
        try{
            return Integer.parseInt (this.applicationProperties.getProperty ( ENGINE_EXECUTOR_THREADS ).trim ());
        }catch (NumberFormatException e){
            return 1;
        }
    }

    public String getCertClientName() {
        return this.applicationProperties.getProperty(CERT_CLIENT_NAME);
    }

    public String getTaskAlgorithm() {
        return this.applicationProperties.getProperty(TASK_ALGORITHM);
    }
    public boolean getAutomaticallyAtributeCollaborators(){
            return Boolean.parseBoolean(this.applicationProperties.getProperty(AUTOMATICALLY_ATRIBUTE_COLLABORATORS));
    }

    public String getColaboratorAlgorithm (){
        return this.applicationProperties.getProperty(COLABORATOR_ALGORITHM);
    }

    public boolean getConnectionMode() {
        return Boolean.parseBoolean (this.applicationProperties.getProperty( PORT_CONNECTION_MODE ));
    }
}
