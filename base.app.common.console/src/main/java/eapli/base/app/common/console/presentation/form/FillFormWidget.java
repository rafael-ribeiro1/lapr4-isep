package eapli.base.app.common.console.presentation.form;

import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.framework.io.util.Console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FillFormWidget {

    public static Map<Attribute, String> fillForm(String title, List<Attribute> attributes) {
        if (!attributes.isEmpty())
            System.out.println(title);
        Map<Attribute,String> formResponses = new HashMap<>();
        for (Attribute attr : attributes) {
            String answer = Console.readLine(attr.label());
            while (!answer.matches(attr.regex())) {
                System.out.println("Invalid input");
                answer = Console.readLine(attr.label());
            }
            formResponses.put(attr, answer);
        }
        return formResponses;
    }

}
