package com.datmt.tools.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {

        this.messageSource = messageSource;
    }


    @GetMapping
    public String hello(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);

        //Default message
//        return messageSource.getMessage("non.existent.key", null, "Default Message", locale);


        //Parameters
//        return messageSource.getMessage("welcome.user", new Object[]{"John"}, locale);
//        return messageSource.getMessage("order.status", new Object[]{"12345", "shipped"}, locale);


        //Multiple lines
//        return messageSource.getMessage("text.multiple.lines", null, locale);


        //date and number
        /*
        String greeting = messageSource.getMessage("greeting", null, locale);
        String dateFormatPattern = messageSource.getMessage("date.format", null, locale);
        String numberFormatPattern = messageSource.getMessage("number.format", null, locale);

        // Formatting date
        DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern, locale);
        String formattedDate = dateFormat.format(new Date());

        // Formatting number
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat numberFormat = new DecimalFormat(numberFormatPattern, symbols);

        String formattedNumber = numberFormat.format(12345.6789);
        return String.format("%s\nDate: %s\nNumber: %s", greeting, formattedDate, formattedNumber);
*/

    }
}
