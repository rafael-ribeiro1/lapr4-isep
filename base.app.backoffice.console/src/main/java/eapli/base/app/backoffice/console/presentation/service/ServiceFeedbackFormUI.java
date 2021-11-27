package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ServiceFeedbackFormUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public ServiceFeedbackFormUI(final SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        int input;
        String menu=buildMenu ();
        do{
            input= Console.readInteger (menu);
            switch (input){
                case 0:
                    break;
                case 1:
                    facade.setFeedbackForm ();
                    input=0;
                    System.out.println ("Feedback form added");
                    break;
                case 2:
                    facade.removeFeedbackForm ();
                    System.out.println ("Feedback form removed");
                    input=0;
                    break;
                default://do nothing. didn't recognize input
            }
        }while (input!=0);
        return true;
    }

    @Override
    public String headline() {
        return "Feedback Form";
    }

    @Override
    public String toString() {
        return headline ();
    }

    private String buildMenu(){
        return "0.Return\n" +
                "1.Add feedback form\n" +
                "2.Remove feedback form\n";
    }
}
