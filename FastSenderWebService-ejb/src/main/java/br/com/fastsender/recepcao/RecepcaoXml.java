package br.com.fastsender.recepcao;

import br.com.fastsender.mongo.connection.MongoDBConnection;
import br.com.fastsender.mongo.facade.XmlFacade;
import br.com.fastsender.utils.EasyString;
import com.mongodb.DB;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lucas
 */
public class RecepcaoXml {
    
    private static final MongoDBConnection MONGODB_CONNECTION = new MongoDBConnection();
    private static final DB dbMongo = MONGODB_CONNECTION.getDB();
    // Galera, essa classe aqui foi só pra testes, mas se precisar 
    // tem uns métodos aqui que da pra usar
    private String xmlReceb;
    EasyString parse = new EasyString();
    
    private XmlFacade xmlFacade = new XmlFacade();
    public String recuperaXml() {
        return this.xmlReceb = parse.fileTxtToString("/home/lucas/Documents/FastSender/exemplo.xml");
    }
    
    public static void main(String[] args) {
        RecepcaoXml xml = new RecepcaoXml();
        xml.startXml4Mongo();
    }

    //inicia a gravação do xml no mongo DB, onde vai ter um método recursivo de teste
    public void startXml4Mongo() {
        boolean send = true;
        int count = 0;
        String xmlToSend = recuperaXml();
        while (send) {
            xmlFacade.getXmlBo().inserirXml(dbMongo, xmlToSend);
        }
        if (count == 100){
            send = false;
            count = 0;
        }
        System.out.println("Inserido com sucesso");
    }
}
