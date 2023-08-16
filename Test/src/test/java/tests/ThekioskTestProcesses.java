package tests;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class ThekioskTestProcesses {
    public static void main(String[] args) {
        Logger mariadbLogger = (Logger) LoggerFactory.getLogger("org.mariadb.jdbc");
        mariadbLogger.setLevel(Level.OFF);

        //          TYPE_PROCESSES_FOR_BILLING       TYPE_PROCESSES_FOR_ETL

        //          "subscribe"                      "subscription"
        //          "unsubscribe"                    "unsubscription"
        //          "renew"                          "renewal_finalized"
        //          "ident"                          "identification"                 

        ConstructorParams ThekioskTestProcesses = new ConstructorParams(
                3002,
                337,
                "2023-07-19 10:00",
                "2023-07-19 11:00",
                1,
                "subscribe",
                "subscription"
        );


        // We get the carrier code and billing ID from the ThekioskTest object
        int carrierCode = ThekioskTestProcesses.getBillingCarrierId();
        int billingId = ThekioskTestProcesses.getEtlCarrierOfferId();

        // We get maps for the corresponding data
        Map<Integer, String> billingCarrierOfferMap = CarriersOfferData.getTheKioskBillingCarrierOfferMap();
        Map<Integer, String> etlCarrierOfferMap = CarriersOfferData.getTheKioskETLCarrierOfferMap();

        // We get the names of the carriers according to the specified codes
        String billingCarrierName = billingCarrierOfferMap.get(carrierCode);
        String etlCarrierName = etlCarrierOfferMap.get(billingId);

        // Display the names next to the corresponding fields
        System.out.println("Carrier (Billing): " + billingCarrierName);
        System.out.println("Carrier (ETL): " + etlCarrierName);


        ThekioskProcessesCode thekiosk = new ThekioskProcessesCode(ThekioskTestProcesses) {
        };
        thekiosk.compareDatabases();


    }
}
