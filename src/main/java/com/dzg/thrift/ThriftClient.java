package com.dzg.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import sun.rmi.transport.tcp.TCPTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-17 20:52
 **/
public class ThriftClient {
    public static void main(String[] args) {
        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            transport.open();
            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println("-----------");
            Person person1 = new Person();
            person1.setUsername("李白");
            client.savePerson(person1);

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);

        }finally {
            transport.close();
        }
    }
}
