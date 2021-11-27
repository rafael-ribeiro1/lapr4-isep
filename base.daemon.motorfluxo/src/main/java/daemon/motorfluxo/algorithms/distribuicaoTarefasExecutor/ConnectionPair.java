package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import eapli.framework.strings.util.StringPredicates;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class ConnectionPair {
    public static final int MAX_PORT_NUMBER = 65535;
    private final String ip;
    private final int port;

    public ConnectionPair(String ip, int port) {
        StringPredicates.isNullOrWhiteSpace ( ip );
        if(!ip.matches ( "((25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})"))
            throw new IllegalArgumentException ("Invalid ip address: "+ip);
        if(port<0 || port> MAX_PORT_NUMBER){
            throw new IllegalArgumentException ("Invalid port: "+port);
        }
        this.ip = ip;
        this.port = port;
    }

    public String ip() {return ip;}

    public int port() {return port;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        ConnectionPair that = (ConnectionPair) o;
        return new EqualsBuilder ()
                .append ( port, that.port )
                .append ( ip, that.ip )
                .isEquals ();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder ( 17, 37 )
                .append ( ip )
                .append ( port )
                .toHashCode ();
    }
}
