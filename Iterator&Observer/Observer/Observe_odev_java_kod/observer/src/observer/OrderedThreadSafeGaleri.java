
package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class OrderedThreadSafeGaleri {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    protected final Lock readLock = readWriteLock.readLock();
    protected final Lock writeLock = readWriteLock.writeLock();
    private List<Otomobil> otomobiller = new ArrayList<>();
    private List<OtomobilAddedListener> listeners = new ArrayList<>();
    public void addOtomobil (Otomobil otomobil) {
        // otomobiller listesine otomobil ekle
        this.otomobiller.add(otomobil);
        // Kayıtlı dinleyici listesine bildir
        this.notifyOtomobilAddedListeners(otomobil);
    }
    public OtomobilAddedListener registerOtomobilAddedListener (OtomobilAddedListener listener) {
        // Yazmak için dinleyici listesini kilitle
        this.writeLock.lock();
        try {
            // Dinleyiciyi kayıtlı dinleyiciler listesine ekleyin
            this.listeners.add(listener);
        }
        finally {
            // Yazma kilidini açar
            this.writeLock.unlock();
        }
        return listener;
    }
    public void unregisterOtomobilAddedListener (OtomobilAddedListener listener) {
        // Yazmak için dinleyici listesini kilitle
        this.writeLock.lock();
        try {
            // Dinleyiciyi kayıtlı dinleyiciler listesinden kaldırın
            this.listeners.remove(listener);
        }
        finally {
            // Yazma kilidini açın
            this.writeLock.unlock();
        }
    }
    public void notifyOtomobilAddedListeners (Otomobil otomobil) {
        // Dinleyici listesini okumak için kilitleyin
        this.readLock.lock();
        try {
            // Kayıtlı dinleyiciler listesindeki her dinleyiciyi bilgilendir listeners
            this.listeners.forEach(listener -> listener.onOtomobilAdded(otomobil));
        }
        finally {
            // Okuyucu kilidini açın
            this.readLock.unlock();
        }
    }
    
}
