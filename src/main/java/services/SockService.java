package services;

import model.Sock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SockService {

    private ArrayList<Sock> socksList = new ArrayList<Sock>();

    private void addNewSocks (Sock sock) {
        if (sock!=null) {
            socksList.add(sock);
        }
    }

    private void plusSocks (int id, Sock sock) {
        if ((sock != null)&&(socksList.get(id)!=null)) {
            socksList.get(id).setQuantity(socksList.get(id).getQuantity()+sock.getQuantity());
        }
    }

    public String addSocks (Sock socks) {
        if (socks == null) {return "Передан пустой запрос";}
        else {
            for (Sock s : socksList) {
                if ((s.getColor()==socks.getColor())&&(s.getCottonPart()==socks.getCottonPart())&&(s.getSize()==socks.getSize())) {
                    String str = "Партия носков: "+s+" пополнена на "+socks.getQuantity()+" шт.";
                    int id = socksList.indexOf(s);
                    plusSocks(id, socks);
                    return str+" Общее количество - "+socksList.get(id).getQuantity()+" шт.";
                }
            }
            addNewSocks(socks);
            return "Добавлена партия носков: "+socks;
        }

    }



}
