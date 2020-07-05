
package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadSafeGaleri {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    protected final Lock readLock = readWriteLock.readLock();
    protected final Lock writeLock = readWriteLock.writeLock();
    private List<Otomobil> otomobiller = new ArrayList<>();
    private List<OtomobilAddedListener> listeners = new ArrayList<>();
    public void addOtomobil (Otomobil otomobil) {
        // Otomobil'i otomobiller listesine ekler
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
            // Yazar kilidini açın
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
            // Yazar kilidini açın
            this.writeLock.unlock();
        }
    }
    public void notifyOtomobilAddedListeners (Otomobil otomobil) {
        // Dinleyici listesini okumak için kilitleyin
        this.readLock.lock();
        try {
            // Kayıtlı dinleyiciler listesindeki her dinleyiciyi bilgilendir
            this.listeners.forEach(listener -> listener.onOtomobilAdded(otomobil));
        }
        finally {
            // Okuyucu kilidini açın
            this.readLock.unlock();
        }
    }
    
}
