package services;

import javax.annotation.PostConstruct;
import model.Sock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static model.Sock.Color.White;
import static model.Sock.Size.M;

@Service
public class SockService {

    private final ArrayList<Sock> socksList = new ArrayList<Sock>();

    private final FileService fileService;

    public SockService(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    void one() {
        Sock sock = new Sock(20, 100, Sock.Size.L, White);
        Sock sock2 = new Sock(20, 300, Sock.Size.L, White);
        Sock sock3 = new Sock(20, 100, M, White);
        Sock sock4 = new Sock(20, 40, M, White);
        System.out.println(addSocks(sock));
        System.out.println(addSocks(sock2));
        System.out.println(addSocks(sock3));
        System.out.println(addSocks(sock4));
    }

    private void addNewSocks(Sock sock) {
        if (sock != null) {
            socksList.add(sock);
        }
    }

    private void plusSocks(int id, Sock sock) {
        if ((sock != null) && (socksList.get(id) != null)) {
            socksList.get(id).setQuantity(socksList.get(id).getQuantity() + sock.getQuantity());
        }
    }

    public String addSocks(Sock socks) {
        if (socks == null) {
            return "Передан пустой запрос";
        } else {
            for (Sock s : socksList) {
                if ((s.getColor() == socks.getColor()) && (s.getCottonPart() == socks.getCottonPart()) && (s.getSize() == socks.getSize())) {
                    String str = "Пополнена партия носков: " + s + " пополнена на " + socks.getQuantity() + " шт.";
                    int id = socksList.indexOf(s);
                    plusSocks(id, socks);
                    return str + " Общее количество - " + socksList.get(id).getQuantity() + " шт.";
                }
            }
            addNewSocks(socks);
            return "Добавлена партия носков: " + socks;
        }

    }


}
