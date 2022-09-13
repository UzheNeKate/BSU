package by.lab6;

import by.lab6.compositions.AbstractComposition;
import by.lab6.compositions.Compositions;
import by.lab6.compositions.cmp.Fields;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DiskRemote {
    String address = "localhost";

    interface DiskServerInterface extends Remote {
        int getDuration(String diskName) throws RemoteException;

        AbstractComposition search(String diskName, String name, Integer duration, String composer) throws RemoteException;

        boolean createDisk(String diskName) throws RemoteException;

        void recordComposition(String diskName, Compositions compositions, String name, int duration, String composer,
                               String performer, String tonality, String textWriter, String genre, String instrument)
                throws RemoteException;

        void sort(String diskName, Fields field) throws RemoteException;
    }
}
