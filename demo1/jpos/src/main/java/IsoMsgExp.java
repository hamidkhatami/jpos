import org.jpos.iso.*;
import org.jpos.iso.packager.ISO87APackager;

public class IsoMsgExp {

    public static void main(String[] args) throws Exception {
        // انتخاب Packager (برای تعریف ساختار فیلدها)
        ISOBasePackager packager = new ISO87APackager();

        // ساخت پیام ISO
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);

        // ست کردن MTI و فیلدها
        isoMsg.setMTI("0200");            // Financial Request
        isoMsg.set(3, "000000");          // Processing Code
        isoMsg.set(4, "000000010000");    // Amount: 10000
        isoMsg.set(7, "0917223030");      // Transmission Date & Time
        isoMsg.set(11, "123456");         // STAN
        isoMsg.set(37, "654321123456");   // RRN
        isoMsg.set(39, "00");             // Response Code

        // چاپ به شکل خوانا
        System.out.println("ISOMsg محتوا:");
        for (int i = 0; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i)) {
                System.out.println("  Field " + i + " = " + isoMsg.getString(i));
            }
        }
    }


}
