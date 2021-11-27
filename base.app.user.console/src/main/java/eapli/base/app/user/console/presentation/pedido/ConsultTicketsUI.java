package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.pedidoservico.application.ConsultTicketsController;
import eapli.base.pedidoservico.dto.PedidoDTO;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class ConsultTicketsUI extends AbstractUI {
    private final ConsultTicketsController controller=new ConsultTicketsController ();
    private final Collection<PedidoDTO> pendingTicketsDTO=new ArrayList<> ();
    private final Collection<PedidoDTO> concludedTicketsDTO=new ArrayList<> ();

    @Override
    protected boolean doShow() {
        try{
            this.controller.getUserTickets (this.pendingTicketsDTO,this.concludedTicketsDTO );
            Map<String,PedidoDTO> map= buildSelectionMenu ();
            SingleSelectionWidget<String> widget=new SingleSelectionWidget<> ( map.keySet () );
            String ticket;
            widget.doSelection ();
            ticket= widget.selectedItem ();
            while(ticket!=null){
                System.out.println (map.get ( ticket ));
                Console.readLine ("Press enter to exit");
                widget.doSelection ();
                ticket= widget.selectedItem ();
            }
            return true;
        }catch (IllegalArgumentException | ConcurrencyException e){
            e.printStackTrace ();
            return true;
        }
    }

    @Override
    public String headline() {
        return "Consult Tickets";
    }

    private Map<String,PedidoDTO> buildSelectionMenu(){
        Map<String,PedidoDTO> mergedMap=new TreeMap<> ();
        this.pendingTicketsDTO.forEach ( dto->mergedMap.put ( "(Pending) id "+dto.ticketId+": "+dto.service.description,dto ) );
        this.concludedTicketsDTO.forEach ( dto->mergedMap.put ("(Concluded) id "+ dto.ticketId+": "+dto.service.description, dto) );
        return mergedMap;
    }
}
