package serivce;

import models.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClient();
    public List<Client> searcClient(String kw);
    void updateClient();
    void deleteClient(long id);
     void  createClinet();

}
