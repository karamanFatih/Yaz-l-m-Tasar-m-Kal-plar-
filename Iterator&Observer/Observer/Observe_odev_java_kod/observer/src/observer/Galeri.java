
package observer;
import java.util.ArrayList;
import java.util.List;

public class Galeri {
     private List<Otomobil> otomobiller = new ArrayList<>();
    private List<OtomobilAddedListener> listeners = new ArrayList<>();
    public void addOtomobil (Otomobil otomobil) {
        //  otomobiller listesine otomobil ekler 
        this.otomobiller.add(otomobil);
        // kayıtlı dinleyici listesine bildir
        this.notifyOtomobilAddedListeners(otomobil);
    }
    public void registerOtomobilAddedListener (OtomobilAddedListener listener) {
        // dinleyiciyi kayıtlı dinleyiciler listesine ekler
        this.listeners.add(listener);
    }
    public void unregisterOtomobilAddedListener (OtomobilAddedListener listener) {
        // dinleyiciyi kayıtlı dinleyiciler listesinden kaldırın
        this.listeners.remove(listener);
    }
    protected void notifyOtomobilAddedListeners (Otomobil otomobil) {
        // kayıtlı dinleyiciler listesindeki her dinleyiciyi bilgilendir
        this.listeners.forEach(listener -> listener.onOtomobilAdded(otomobil));
    }
    
}
