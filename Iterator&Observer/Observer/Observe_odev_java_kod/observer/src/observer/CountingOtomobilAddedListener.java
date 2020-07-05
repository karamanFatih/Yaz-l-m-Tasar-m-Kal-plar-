
package observer;


public class CountingOtomobilAddedListener implements OtomobilAddedListener {
    private static int otomobillerAddedCount = 0;
    @Override
    public void onOtomobilAdded (Otomobil otomobil) {
        // otomobil numarası artırır
        otomobillerAddedCount++;
        
        System.out.println("toplam eklenmiş otomobomiller: " + otomobillerAddedCount);
    }
    
}
