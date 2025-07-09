package lrn;

import org.jpos.iso.*;
import org.jpos.iso.packager.ISO87APackager;

/**
 * @author khatami ( khatami@caspco.ir)
 * @version 1.0 2024.0605
 * @createDate 7/9/2025
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) throws ISOException {
        try {
            // 1. ساخت یک پیام ISO با فرمت 1987
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(new ISO87APackager());

            // 2. تعیین نوع پیام
            isoMsg.setMTI("0200");

            // 3. ست کردن فیلدهای مهم
            isoMsg.set(2, "6037991234567890");      // شماره کارت
            isoMsg.set(3, "000000");                // کد عملیات - خرید
            isoMsg.set(4, "000000010000");          // مبلغ - 1000 تومان
            isoMsg.set(7, "0709123456");            // تاریخ/زمان انتقال
            isoMsg.set(11, "123456");               // STAN - شماره پیگیری
            isoMsg.set(41, "POS12345");             // شناسه دستگاه کارت‌خوان
            isoMsg.set(42, "MERCH1234567");         // شناسه پذیرنده
            isoMsg.set(49, "364");                  // کد ارز (ریال)

            // 4. تبدیل به پیام خام (packed)
            byte[] data = isoMsg.pack();

            // 5. نمایش پیام برای دیباگ
            System.out.println("🔹 MTI: " + isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.printf("Field %d: %s%n", i, isoMsg.getString(i));
                }
            }

            System.out.println("\n📦 Raw Message (hex):");
            System.out.println(ISOUtil.hexString(data));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




