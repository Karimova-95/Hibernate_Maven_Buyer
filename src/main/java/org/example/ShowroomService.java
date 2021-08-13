package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class ShowroomService {
    private static SessionFactory factory;
    private Session session = null;

    public ShowroomService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Order2.class)
                .addAnnotatedClass(OrderKey.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

//        try{
//
//            CREATE
//            session = factory.getCurrentSession();
//            Buyer buyer1 = new Buyer("Fred");
//            Buyer buyer2 = new Buyer("Tom");
//            Buyer buyer3 = new Buyer("Bob");
//            session.beginTransaction();
//            session.save(buyer1);
//            session.save(buyer2);
//            session.save(buyer3);
//
//            Good milk = new Good("Milk", 45);
//            Good bread = new Good("Bread", 25);
//            Good apple = new Good("Apple", 7);
//            session.save(milk);
//            session.save(bread);
//            session.save(apple);
//            session.getTransaction().commit();
//
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Good good = session.get(Good.class, 2L);
//            Good good1 = session.get(Good.class, 2L);
//            Good good2 = session.get(Good.class, 3L);
//            Buyer buyer = session.get(Buyer.class, 1L);
//            Buyer buyer1 = session.get(Buyer.class, 2L);
//            Buyer buyer2 = session.get(Buyer.class, 3L);
//            buyer.getGoods().add(good);
//            buyer.getGoods().add(good1);
//            buyer.getGoods().add(good2);
//            buyer1.getGoods().add(good1);
//            buyer2.getGoods().add(good);
//            buyer2.getGoods().add(good2);
//            session.getTransaction().commit();
//        } finally {
//            factory.close();
//            session.close();
//        }
    }

    public void start() {

        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while (!exit) {
            System.out.print("Bведите команду: ");
            String next = in.nextLine();
            if (next.equals("exit")) {
                exit = true;
            } else {
                String[] commands = next.split(" ");
                try {
                    if (commands[0].equals("showProductsByPerson")) {
                        session = factory.getCurrentSession();
                        session.beginTransaction();

                        Buyer buyer = (Buyer) session.createQuery("from Buyer where name = :name")
                                .setParameter("name", commands[1]).getSingleResult();
                        List<Order> orders = buyer.getOrders();
//                        List<Order2> orders = buyer.getOrders();
                        orders.forEach(o -> System.out.println("Products for " + buyer.getName() + ": " +
                                o.getProduct().toString()));
                        System.out.println(orders);
                        session.getTransaction().commit();
                    } else if (commands[0].equals("findPersonsByProductTitle")) {
                        session = factory.getCurrentSession();
                        session.beginTransaction();

                        Product product = (Product) session.createQuery("from Product where name = :name")
                                .setParameter("name", commands[1]).getSingleResult();
                        List<Order> orders = product.getOrders();
//                        List<Order2> orders = product.getOrders();
                        orders.forEach(o -> System.out.println("Product " + product.getName() + " was bought by : " +
                                o.getBuyer().toString()));
                        System.out.println(orders);

                        session.getTransaction().commit();
                    } else if (commands[0].equals("removePerson")) {
                        session = factory.getCurrentSession();
                        session.beginTransaction();
                        int res = session.createQuery("delete from Buyer b where b.name = :name")
                                        .setParameter("name", commands[1]).executeUpdate();
                        if (res > 0) {
                            System.out.println("Buyer " + commands[1] + " was removed");
                        }
                        session.getTransaction().commit();
                    } else if (commands[0].equals("removeProduct")) {
                        session = factory.getCurrentSession();
                        session.beginTransaction();
                        int res = session.createQuery("delete from Product b where b.name = :name")
                                .setParameter("name", commands[1]).executeUpdate();
                        if (res > 0) {
                            System.out.println(commands[1] + " was removed");
                        }
                        session.getTransaction().commit();
                    } else if (commands[0].equals("buy")) {
                        session = factory.getCurrentSession();
                        session.beginTransaction();
                        Buyer buyer = (Buyer) session.createQuery("from Buyer where name = :name")
                                .setParameter("name", commands[1]).getSingleResult();
                        Product product = (Product)session.createQuery("from Product where name =: name")
                                        .setParameter("name", commands[2]).getSingleResult();
//                        Order2 order = new Order2(buyer, product, product.getPrice());
                        OrderKey orderKey = new OrderKey(buyer.getId(), product.getId());
                        Order order = new Order(orderKey, product.getPrice());

                        session.save(order);
                        System.out.println(order);
                        session.getTransaction().commit();
                    } else {

                    }
                } catch (Exception ex) {
                    System.out.println("Упс. Что-то пошло не так. Попробуйте заново.");
                    ex.printStackTrace();
                    break;
                }
            }
        }
    }

}
