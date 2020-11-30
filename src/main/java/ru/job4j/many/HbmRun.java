package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<BrandCar> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            addData(session);
            session.beginTransaction();

            list = session.createQuery("select distinct c from BrandCar c join fetch c.models").list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (ModelCar model : list.get(0).getModels()) {
            System.out.println(model.getName());
        }
    }

    private static void addData(Session session) {
        session.beginTransaction();
        BrandCar brand = BrandCar.of("Lada");

        ModelCar kalina = ModelCar.of("Kalina", brand);
        session.save(kalina);
        ModelCar oka = ModelCar.of("Oka", brand);
        session.save(oka);
        ModelCar priora = ModelCar.of("Priora", brand);
        session.save(priora);
        ModelCar granta = ModelCar.of("Granta", brand);
        session.save(granta);
        ModelCar niva = ModelCar.of("Niva", brand);
        session.save(niva);

        brand.addModel(session.load(ModelCar.class, 1));
        brand.addModel(session.load(ModelCar.class, 2));
        brand.addModel(session.load(ModelCar.class, 3));
        brand.addModel(session.load(ModelCar.class, 4));
        brand.addModel(session.load(ModelCar.class, 5));

        session.save(brand);
        session.getTransaction().commit();
    }
}
