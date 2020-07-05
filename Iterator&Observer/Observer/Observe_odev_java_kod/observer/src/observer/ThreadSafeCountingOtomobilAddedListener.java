
package observer;

import java.util.concurrent.atomic.AtomicLong;


public class ThreadSafeCountingOtomobilAddedListener  implements OtomobilAddedListener {
     private static AtomicLong otomobillerAddedCount = new AtomicLong(0);
    @Override
    public void onOtomobilAdded (Otomobil otomobil) {
        // Otomobiller sayısını artırın
        otomobillerAddedCount.incrementAndGet();
       
        System.out.println("Toplam eklenen otomobil: " + otomobillerAddedCount);
    }
    
    
}
