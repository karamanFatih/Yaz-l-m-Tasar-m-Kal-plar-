
package observer;


public class Main {

    
    public static void main(String[] args) {
        Galeri galeri = new Galeri();
        // Bir otomobil eklendiğinde haberdar olmak için dinleyicileri kaydedin
        galeri.registerOtomobilAddedListener(new PrintNameOtomobilAddedListener());
        galeri.registerOtomobilAddedListener(new CountingOtomobilAddedListener());
        // Bir otomobil ekleyin kayıtlı dinleyicileri bilgilendirin
        galeri.addOtomobil(new Otomobil("wolkvogen"));
        galeri.addOtomobil(new Otomobil("audi"));
        galeri.addOtomobil(new Otomobil("mercedez"));
    }
    
}
