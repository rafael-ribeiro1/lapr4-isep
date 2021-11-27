package daemon.motorfluxo.protocol;

import daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.ExecutorsThreadsData;
import org.junit.Test;

import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;

public class ExecutorsThreadsDataTest {


    @Test
    public void correctlyGetsSemaphoreByThreadId() {
        String ticketId="ticket1";
        ExecutorsThreadsData.putData(ticketId,0);
        Semaphore sem = ExecutorsThreadsData.getSemaphoreByTicketId (ticketId);
        assertEquals(0, sem.availablePermits());
    }

    @Test
    public void correctlyGetsSemaphoreByTicketId() {
        ExecutorsThreadsData.putData("ticket1", 0 );
        ExecutorsThreadsData.putData("ticket2", 1 );
        Semaphore sem = ExecutorsThreadsData.getSemaphoreByTicketId("ticket1");
        assertEquals(0, sem.availablePermits());
    }

    @Test
    public void correctlyRemovesData() {
        String ticket="ticket1";
        ExecutorsThreadsData.putData(ticket, 0 );
        ExecutorsThreadsData.addTicketExecutionCode ( ticket,3 );
        int response = ExecutorsThreadsData.removeData("ticket1");
        assertEquals(3, response);
    }

}