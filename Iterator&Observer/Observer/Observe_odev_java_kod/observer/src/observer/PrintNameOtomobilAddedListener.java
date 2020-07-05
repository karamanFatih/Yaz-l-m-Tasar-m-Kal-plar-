
package observer;


public class PrintNameOtomobilAddedListener implements OtomobilAddedListener {
    
    @Override
    public void onOtomobilAdded (Otomobil otomobil) {
        // Yeni eklenen otomobilin adını yazdırır
        System.out.println("ismiyle yeni eklenen otomobil '" + otomobil.getName() + "'");
    }
}
