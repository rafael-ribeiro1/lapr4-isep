package eapli.base.pedidoservico.dto;

import eapli.base.servicemanagement.dto.ServiceDTO;

public class PedidoDTO {
    public String ticketId;
    public String requestDate;
    public String deadline;
    public ServiceDTO service;
    public String urgency;
    public String state;

    public PedidoDTO(String ticketId,String requestDate, String deadline, ServiceDTO service, String urgency, String state) {
        this.ticketId=ticketId;
        this.requestDate = requestDate;
        this.deadline = deadline;
        this.service = service;
        this.urgency = urgency;
        this.state = state;
    }

    @Override
    public String toString() {
        return  "ticket id:" + ticketId + "\n" +
                "request date:" + requestDate + "\n" +
                "deadline:" + deadline + "\n" +
                "service\n" +
                " -title: "+service.title+"\n"+
                " -code: "+service.serviceCode+"\n"+
                " -description: "+service.description+"\n"+
                "urgency:" + urgency + "\n" +
                "state:" + state + "\n"
                ;
    }
}
