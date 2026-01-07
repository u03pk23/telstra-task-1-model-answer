package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.database.DatabaseRecordRepository;
import au.com.telstra.simcardactivator.database.databaseRecord;
import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimCardActivationRestController {

    private final SimCardActuationHandler simCardActuationHandler;

    public SimCardActivationRestController(SimCardActuationHandler simCardActuationHandler) {
        this.simCardActuationHandler = simCardActuationHandler;
    }

    @PostMapping(value = "/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard) {
        var actuationResult = simCardActuationHandler.actuate(simCard);
        System.out.println(actuationResult.getSuccess());

        String iccid = simCard.getIccid();
        String customerEmail = simCard.getCustomerEmail();

        databaseRecord record = new databaseRecord(iccid, customerEmail, actuationResult.getSuccess());

    }



}
